package com.example.icastillo.accesobasedatosnba;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by icastillo on 5/02/18.
 */

public class RepositorioEquipos {

    private Application application;

    public RepositorioEquipos(){
        application=new Application();
    }

    public RepositorioEquipos(Application application){
        this.application=application;

    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LiveData<Equipo[]> getListaEquipos() {
        LiveData<Equipo[]> liveDataEquipos=AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().getEquiposLiveData();
        return liveDataEquipos;
    }

}
