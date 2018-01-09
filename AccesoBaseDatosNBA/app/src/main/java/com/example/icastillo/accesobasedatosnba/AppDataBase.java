package com.example.icastillo.accesobasedatosnba;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by icastillo on 09/01/2018.
 */

@Database(entities = {Equipo.class, Jugador.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DAOEquipos equipoDAO();
    public abstract Jugador jugadorDAO();


    private static AppDataBase INSTANCE;


    static AppDataBase getDataBase(final Context context){
        if(INSTANCE==null){
            synchronized (AppDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, "NBA.db").build();
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
