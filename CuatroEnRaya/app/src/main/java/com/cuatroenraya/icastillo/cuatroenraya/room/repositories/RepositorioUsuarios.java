package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

/**
 * Created by icastillo on 07/02/2018.
 */

public class RepositorioUsuarios {

    //Equipo[] equipos=AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().getEquiposArray();
    private Application application;

    public RepositorioUsuarios(Application application){
        this.application=application;
    }

    public LiveData<Usuario[]> getUsuarios(){
        LiveData<Usuario[]> liveData;
        liveData= AppDatabase.getDatabase(this.application.getApplicationContext()).usuariosDAO().getUsuarios();
        return liveData;
    }

    public void insertUsuario(Usuario usuario){
        AppDatabase.getDatabase(this.application.getApplicationContext()).usuariosDAO().insertUsuario(usuario);
    }



}