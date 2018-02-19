package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;

/**
 * Created by icastillo on 16/02/2018.
 */

public class RepositorioPuntuaciones {

    private static Application application;

    public RepositorioPuntuaciones(Application application){
        this.application=application;
    }

    public void insertPuntuacion(Puntuacion puntuacion){
        new InsertPuntuacionAsync().execute(puntuacion);
    }

    //Necesario para el viewmodel de puntuaciones
    public LiveData<Puntuacion[]> getLiveDataPuntuaciones(){
        LiveData<Puntuacion[]> liveDataPuntuaciones=AppDatabase.getDatabase(application.getApplicationContext()).puntuacionesDAO().getPuntuaciones();
        return liveDataPuntuaciones;
    }

    private static class InsertPuntuacionAsync extends AsyncTask<Puntuacion, Void, Void>{
        @Override
        protected Void doInBackground(Puntuacion... puntuacions) {
            AppDatabase.getDatabase(application.getApplicationContext()).puntuacionesDAO().insertPuntuacion(puntuacions[0]);
            return null;
        }
    }



}
