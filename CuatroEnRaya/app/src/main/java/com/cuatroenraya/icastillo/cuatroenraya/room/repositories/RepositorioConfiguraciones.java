package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;

/**
 * Created by icastillo on 12/02/2018.
 */

public class RepositorioConfiguraciones {

    private Application application;

    public RepositorioConfiguraciones(Application application){
        this.application=application;
    }

    @SuppressLint("StaticFieldLeak")
    public void insertConfiguracion(Configuracion configuracion){
        new AsyncTask<Configuracion, Void, Void>() {
            @Override
            protected Void doInBackground(Configuracion... configuracions) {
                AppDatabase.getDatabase(application.getApplicationContext()).configuracionesDAO().insertConfiguracion(configuracions[0]);
                return null;
            }
        }.execute(configuracion);
    }

    @SuppressLint("StaticFieldLeak")
    public Configuracion getConfiguracionUsuario(int idUsuario){
        final Configuracion[] configuracion = {new Configuracion()};
        new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                configuracion[0] = AppDatabase.getDatabase(application.getApplicationContext()).configuracionesDAO().getConfiguracionUsuario(integers[0]);
                return null;
            }
        }.execute(idUsuario);
        return configuracion[0];
    }

}
