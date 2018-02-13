package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

import java.util.concurrent.ExecutionException;

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
       new InsertConfiguracionAsyncTask(this.application).execute(configuracion);
    }

    @SuppressLint("StaticFieldLeak")
    public Configuracion getConfiguracionUsuario(Integer idUsuario){
        Configuracion configuracion= null;
        try {
            configuracion = new GetConfiguracionUsuarioAsynctask(this.application).execute(idUsuario).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return configuracion;
    }

    public void updateConfiguracion(Configuracion configuracion){
        new UpdateConfiguracionAsyncTask(this.application).execute(configuracion);
    }

    public void updateConfiguracionUsuario(Configuracion configuracion){
        new UpdateConfiguracionUsuarioAsyncTask(this.application).execute(configuracion);
    }

    //Clase para tarea asincrona de obtener la configuracion de un usuario
    private static class GetConfiguracionUsuarioAsynctask extends AsyncTask<Integer, Void, Configuracion>{
        @SuppressLint("StaticFieldLeak")
        private Application application;

        GetConfiguracionUsuarioAsynctask(Application application){
            this.application=application;
        }

        @Override
        protected Configuracion doInBackground(Integer... integers) {
            Configuracion configuracion=AppDatabase.getDatabase(this.application.getApplicationContext()).configuracionesDAO().getConfiguracionUsuario(integers[0]);
            return configuracion;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Clase para tarea asincrona de actualizar la Configuración del Usuario
    private static class UpdateConfiguracionUsuarioAsyncTask extends AsyncTask<Configuracion, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        private Application application;

        UpdateConfiguracionUsuarioAsyncTask(Application application) {
            this.application = application;
        }

        @Override
        protected Void doInBackground(Configuracion... configuracion) {
            AppDatabase.getDatabase(this.application.getApplicationContext()).configuracionesDAO().updateConfiguracionUsuario(configuracion[0].getIdUsuario(), configuracion[0].getTipoTablero());
            return null;
        }
    }

    //
    private static class UpdateConfiguracionAsyncTask extends AsyncTask<Configuracion, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        private Application application;

        UpdateConfiguracionAsyncTask(Application application) {
            this.application = application;
        }

        @Override
        protected Void doInBackground(Configuracion... configuracion) {
            AppDatabase.getDatabase(this.application.getApplicationContext()).configuracionesDAO().updateConfiguracion(configuracion[0]);
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Clase para tarea asincrona de insertar la Configuración
    private static class InsertConfiguracionAsyncTask extends AsyncTask<Configuracion, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        private Application application;

        InsertConfiguracionAsyncTask(Application application) {
            this.application = application;
        }

        @Override
        protected Void doInBackground(Configuracion... configuracion) {
            AppDatabase.getDatabase(this.application.getApplicationContext()).configuracionesDAO().insertConfiguracion(configuracion[0]);
            return null;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
