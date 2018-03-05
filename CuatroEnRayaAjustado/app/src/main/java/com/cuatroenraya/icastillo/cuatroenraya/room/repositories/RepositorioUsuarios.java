package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

/**
 * Created by icastillo on 07/02/2018.
 */

public class RepositorioUsuarios {

    private Application application;

    public RepositorioUsuarios(Application application){
        this.application=application;
    }

    public void insertUsuario(Usuario usuario){
        new InsertUsuarioAsyncTask(this.application).execute(usuario);
    }

    public LiveData<Usuario> getUsuarioLiveData(){
        LiveData<Usuario> liveData=AppDatabase.getDatabase(this.application.getApplicationContext()).usuariosDAO().getUsuarioLiveData();
        return liveData;
    }

    public void updateUsuario(Usuario usuario){
        new UpdateUsuarioAsync(this.application).execute(usuario);
    }

    //Clase para tarea asincrona de insertar persona
    private static class InsertUsuarioAsyncTask extends AsyncTask<Usuario, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        private Application application;

        InsertUsuarioAsyncTask(Application application) {
            this.application = application;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            AppDatabase.getDatabase(this.application.getApplicationContext()).usuariosDAO().insertUsuario(usuarios[0]);
            return null;
        }
    }

    //Clase asincrona para actualizar un Usuario
    private static class UpdateUsuarioAsync extends AsyncTask<Usuario, Void, Void>{

        private Application application;

        UpdateUsuarioAsync(Application application){
            this.application=application;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            AppDatabase.getDatabase(this.application.getApplicationContext()).usuariosDAO().updateUsuario(usuarios[0]);
            return null;
        }
    }

}
