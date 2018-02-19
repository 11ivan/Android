package com.cuatroenraya.icastillo.cuatroenraya.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.cuatroenraya.icastillo.cuatroenraya.room.converters.Converters;
import com.cuatroenraya.icastillo.cuatroenraya.room.dao.DAOConfiguraciones;
import com.cuatroenraya.icastillo.cuatroenraya.room.dao.DAODatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.room.dao.DAOPuntuaciones;
import com.cuatroenraya.icastillo.cuatroenraya.room.dao.DAOUsuarios;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.DatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

/**
 * Created by icastillo on 07/02/2018.
 */

@Database(entities = {Usuario.class, Puntuacion.class, Configuracion.class, DatosGameActivity.class}, version = 1/*, exportSchema = false*/)
@TypeConverters(value = Converters.class)
public abstract class AppDatabase  extends RoomDatabase {

    public abstract DAOUsuarios usuariosDAO();
    public abstract DAOPuntuaciones puntuacionesDAO();
    public abstract DAOConfiguraciones configuracionesDAO();
    public abstract DAODatosGameActivity daoDatosGameActivityDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "CuatroEnRaya.db").build();/*.allowMainThreadQueries()*/
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
