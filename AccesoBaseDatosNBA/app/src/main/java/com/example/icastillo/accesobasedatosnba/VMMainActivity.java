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

    public final MutableLiveData<Equipo[]> equiposLiveData=new MutableLiveData<>();

    public VMMainActivity() {
        // trigger user load.
        //userLiveData = new LiveData<>();

    }

    public void cargaLista(Application application){
        this.equiposLiveData.setValue(AppDataBase.getDataBase(application.getApplicationContext()).equipoDAO().getEquipos());
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

}
