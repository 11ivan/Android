package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioConfiguraciones;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioUsuarios;

/**
 * Created by icastillo on 11/02/2018.
 */

public class ViewModelMainActivity extends AndroidViewModel {

    RepositorioUsuarios repositorioUsuarios;
    RepositorioConfiguraciones repositorioConfiguraciones;
    private LiveData<Usuario> usuarioLiveData;
    //private LiveData<Configuracion> configuracionLivedata;
    //public Usuario[] arrayUsuarios;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        repositorioUsuarios=new RepositorioUsuarios(application);
        repositorioConfiguraciones=new RepositorioConfiguraciones(application);
        usuarioLiveData=repositorioUsuarios.getUsuarioLiveData();
        //configuracionLivedata=repositorioConfiguraciones.getConfiguracionLivedata();
    }

    public LiveData<Usuario> getUsuarioLiveData() {
        return usuarioLiveData;
    }

    public void setUsuarioLiveData(LiveData<Usuario> usuarioLiveData) {
        this.usuarioLiveData = usuarioLiveData;
    }

    /*public LiveData<Configuracion> getConfiguracionLivedata() {
        return configuracionLivedata;
    }

    public void setConfiguracionLivedata(LiveData<Configuracion> configuracionLivedata) {
        this.configuracionLivedata = configuracionLivedata;
    }*/

    @SuppressLint("StaticFieldLeak")
    public void cargaUsuario(){
        usuarioLiveData=repositorioUsuarios.getUsuarioLiveData();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertUsuario(Usuario usuario){
        repositorioUsuarios.insertUsuario(usuario);
    }

    public void updateUsuario(Usuario usuario){
        repositorioUsuarios.updateUsuario(usuario);
        //cargaUsuario();   //Para que entre en onChanged
    }


}
