package com.cuatroenraya.icastillo.cuatroenraya.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by icastillo on 07/02/2018.
 */

//@Database(entities = {Equipo.class, Jugador.class}, version = 1)
//@TypeConverters(value = Converters.class)
public abstract class AppDatabase  extends RoomDatabase {

    /*public abstract DAOEquipos equipoDAO();
    public abstract DAOJugadores jugadorDAO();*/


    private static AppDatabase INSTANCE;


    static AppDatabase AppDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    //INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "NBA.db").build();/*.allowMainThreadQueries()*/
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
