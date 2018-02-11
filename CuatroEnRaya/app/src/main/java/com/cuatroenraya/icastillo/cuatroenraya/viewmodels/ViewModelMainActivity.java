package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

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

    private LiveData<Usuario> usuarioLiveData;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
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


    public Usuario[] getUsuarios(){
        final RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        Usuario[] usuarios=null;
        /*new AsyncTask<Void, Void, Usuario[]>() {
            @Override
            protected Usuario[] doInBackground(Void... voids) {
                Usuario[] usuariosRecogidos=repositorioUsuarios.getUsuarios();
                return usuariosRecogidos;
            }
            @Override
            protected void onPostExecute(Usuario[] usuarios1) {
                usuarios[0] =usuarios1;
            }
        }.execute();*/

        return usuarios;
    }

    public LiveData<Usuario[]> getUsuariosLiveData(){
        RepositorioUsuarios repositorioUsuarios=new RepositorioUsuarios(this.getApplication());
        LiveData<Usuario[]> usuarios=repositorioUsuarios.getUsuariosLiveData();
        return usuarios;
    }


}
