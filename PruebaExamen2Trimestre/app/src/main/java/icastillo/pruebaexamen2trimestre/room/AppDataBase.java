package icastillo.pruebaexamen2trimestre.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import icastillo.pruebaexamen2trimestre.room.daos.CochesDAO;
import icastillo.pruebaexamen2trimestre.room.daos.PersonasDAO;

/**
 * Created by icastillo on 20/02/18.
 */
@Database(entities = {/*Usuario.class, Puntuacion.class, Configuracion.class, DatosGameActivity.class*/}, version = 1/*, exportSchema = false*/)
//@TypeConverters(value = Converters.class)
public abstract class AppDataBase extends RoomDatabase{

    private static AppDataBase INSTANCE;
    public abstract PersonasDAO personasDAO();
    public abstract CochesDAO cochesDAO();

    public static AppDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, "Concesionario.db").build();/*.allowMainThreadQueries()*/
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
