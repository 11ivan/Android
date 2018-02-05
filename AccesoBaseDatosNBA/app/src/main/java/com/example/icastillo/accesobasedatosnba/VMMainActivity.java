package com.example.icastillo.accesobasedatosnba;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

/**
 * Created by icastillo on 10/01/18.
 */

public class VMMainActivity extends ViewModel {

    public LiveData<Equipo[]> equiposLiveData;
    private RepositorioEquipos repositorioEquipos;

    public VMMainActivity() {
        // trigger user load.
        //userLiveData = new LiveData<>();
        //repositorioEquipos=new RepositorioEquipos();
    }

    public RepositorioEquipos getRepositorioEquipos() {
        return repositorioEquipos;
    }

    public void cargaRepositorio(Application application){
        repositorioEquipos=new RepositorioEquipos(application);
        //repositorioEquipos.setApplication(application);
    }

    public void cargaLista(){
        this.equiposLiveData=repositorioEquipos.getListaEquipos();
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

}
