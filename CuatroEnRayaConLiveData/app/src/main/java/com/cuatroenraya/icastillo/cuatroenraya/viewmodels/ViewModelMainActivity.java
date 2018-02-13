package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioUsuarios;

/**
 * Created by icastillo on 11/02/2018.
 */

public class ViewModelMainActivity extends AndroidViewModel {

    RepositorioUsuarios repositorioUsuarios;
    private MutableLiveData<Usuario> usuarioLiveData;
    public Usuario[] arrayUsuarios;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        //liveDataArrayUsuarios=repositorioUsuarios.getUsuariosLiveData();
        repositorioUsuarios=new RepositorioUsuarios(application);
        usuarioLiveData=new MutableLiveData<Usuario>();
    }

    public MutableLiveData<Usuario> getUsuarioLiveData() {
        return usuarioLiveData;
    }

    public void setUsuarioLiveData(MutableLiveData<Usuario> usuarioLiveData) {
        this.usuarioLiveData = usuarioLiveData;
    }

    @SuppressLint("StaticFieldLeak")
    public void cargaUsuario(){
        //usuarioLiveData=repositorioUsuarios.getUsuario();
        new AsyncTask<Void, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(Void... voids) {
                Usuario usuario=repositorioUsuarios.getUsuario();
                return usuario;
            }
            @Override
            protected void onPostExecute(Usuario usuario) {
               usuarioLiveData.setValue(usuario);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertUsuarios(Usuario[] usuario){
        new AsyncTask<Usuario, Void, Void>() {
            @Override
            protected Void doInBackground(Usuario... usuario) {
                repositorioUsuarios.insertUsuarios(usuario);
                return null;
            }
        }.execute(usuario);
    }

    @SuppressLint("StaticFieldLeak")
    public void cargaArrayUsuarios(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                arrayUsuarios=repositorioUsuarios.getUsuarios();
                return null;
            }
        }.execute();
    }




}
