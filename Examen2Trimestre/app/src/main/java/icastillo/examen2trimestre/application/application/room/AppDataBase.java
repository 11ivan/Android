package icastillo.examen2trimestre.application.application.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import icastillo.examen2trimestre.application.application.room.converters.Converters;
import icastillo.examen2trimestre.application.application.room.daos.FutbolistasDAO;
import icastillo.examen2trimestre.application.application.room.daos.PosicionesDAO;
import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;

/**
 * Created by icastillo on 21/02/18.
 */
@Database(entities = {Futbolista.class, Posicion.class}, version = 1)
@TypeConverters(value = Converters.class)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    public abstract FutbolistasDAO futbolistasDAO();
    public abstract PosicionesDAO posicionesDAO();

    public static AppDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, "Futbolistas.db").build();/*.allowMainThreadQueries()*/
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }


}
