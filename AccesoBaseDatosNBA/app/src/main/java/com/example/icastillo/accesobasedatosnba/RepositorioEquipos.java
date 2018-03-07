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

    public LiveData<Equipo[]> getLiveDataEquipos() {
        LiveData<Equipo[]> liveDataEquipos=AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().getEquiposLiveData();
        return liveDataEquipos;
    }

    public Equipo[] getArrayEquipos() {
        Equipo[] equipos=AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().getEquiposArray();
        return equipos;
    }

    public Equipo[] getArrayEquiposDesdeLiveData() {
        Equipo[] equipos=AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().getEquiposLiveData().getValue();
        return equipos;
    }

    public void insertaEquipos(Equipo[] equipos){
        AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().insertEquipos(equipos);
    }

    public void insertaLiveDataEquipos(LiveData<Equipo[]> equipos){
        //AppDataBase.getDataBase(this.application.getApplicationContext()).equipoDAO().insertEquiposLiveData(equipos);
    }

}
