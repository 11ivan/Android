package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioPuntuaciones;

/**
 * Created by icastillo on 17/02/2018.
 */

public class ViewModelPuntuacionesActivity extends AndroidViewModel {

    private LiveData<Puntuacion[]> puntuacionesLiveData;
    private RepositorioPuntuaciones repositorioPuntuaciones;

    public ViewModelPuntuacionesActivity(@NonNull Application application) {
        super(application);
        repositorioPuntuaciones=new RepositorioPuntuaciones(application);
        puntuacionesLiveData=repositorioPuntuaciones.getLiveDataPuntuaciones();
    }

    public LiveData<Puntuacion[]> getPuntuacionesLiveData() {
        return puntuacionesLiveData;
    }

    public void setPuntuacionesLiveData(LiveData<Puntuacion[]> puntuacionesLiveData) {
        this.puntuacionesLiveData = puntuacionesLiveData;
    }

    public void cargaPuntuaciones(){
        puntuacionesLiveData=repositorioPuntuaciones.getLiveDataPuntuaciones();
    }




}
