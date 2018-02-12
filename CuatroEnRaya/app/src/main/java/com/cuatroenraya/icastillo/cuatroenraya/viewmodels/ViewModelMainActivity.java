package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioUsuarios;

/**
 * Created by icastillo on 11/02/2018.
 */

public class ViewModelMainActivity extends AndroidViewModel {

    RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
    private LiveData<Usuario> usuarioLiveData;
    public LiveData<Usuario[]> liveDataArrayUsuarios/*=repositorioUsuarios.getUsuariosLiveData()*/;
    public Usuario[] arrayUsuarios;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        //liveDataArrayUsuarios=repositorioUsuarios.getUsuariosLiveData();
    }

    public LiveData<Usuario> getUsuarioLiveData() {
        return usuarioLiveData;
    }

    public void setUsuarioLiveData(LiveData<Usuario> usuarioLiveData) {
        this.usuarioLiveData = usuarioLiveData;
    }

    public void cargaUsuario(){
        RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        usuarioLiveData=repositorioUsuarios.getUsuario();
    }

    public void insertUsuario(final Usuario usuario){
        final RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        new AsyncTask<Usuario, Void, Void>() {
            @Override
            protected Void doInBackground(Usuario... usuarios) {
                repositorioUsuarios.insertUsuario(usuario);
                return null;
            }
        }.execute(usuario);
    }

    /*public LiveData<Usuario[]> getLiveDataArrayUsuarios() {
        return liveDataArrayUsuarios;
    }

    public void setLiveDataArrayUsuarios(LiveData<Usuario[]> liveDataArrayUsuarios) {
        this.liveDataArrayUsuarios = liveDataArrayUsuarios;
    }*/

/*@SuppressLint("StaticFieldLeak")
    public Usuario[] getUsuarios(){
        final RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        Usuario[] usuarios;
        new AsyncTask<Void, Void, Usuario[]>() {
            @Override
            protected Usuario[] doInBackground(Void... voids) {
                Usuario[] usuariosRecogidos=repositorioUsuarios.getUsuarios();
                return usuariosRecogidos;
            }
            @Override
            protected void onPostExecute(Usuario[] usuarios1) {
                usuarios=usuarios1;
            }
        }.execute();

        return usuarios;
    }*/

    @SuppressLint("StaticFieldLeak")
    public void cargaArrayUsuarios(){
        final RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        new AsyncTask<Void, Void, Usuario[]>() {
            @Override
            protected Usuario[] doInBackground(Void... voids) {
                Usuario[] usuariosRecogidos=repositorioUsuarios.getUsuarios();
                return usuariosRecogidos;
            }
            @Override
            protected void onPostExecute(Usuario[] usuarios1) {
                arrayUsuarios=usuarios1;
            }
        }.execute();
    }

    //@SuppressLint("StaticFieldLeak")
    public void cargaUsuariosLiveData(){
        RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        /*new AsyncTask<Void, Void, LiveData<Usuario[]>>() {
            @Override
            protected LiveData<Usuario[]> doInBackground(Void... voids) {
                LiveData<Usuario[]> usuariosRecogidos=repositorioUsuarios.getUsuariosLiveData();
                return usuariosRecogidos;
            }
            @Override
            protected void onPostExecute(LiveData<Usuario[]> usuarios1) {
                liveDataArrayUsuarios =usuarios1;
            }
        }.execute();*/
        liveDataArrayUsuarios=repositorioUsuarios.getUsuariosLiveData();
    }


}
