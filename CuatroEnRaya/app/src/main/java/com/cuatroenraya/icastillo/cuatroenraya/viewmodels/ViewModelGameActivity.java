package com.cuatroenraya.icastillo.cuatroenraya.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.DatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioDatosGameActivity;

/**
 * Created by icastillo on 16/02/2018.
 */

public class ViewModelGameActivity extends AndroidViewModel {

    private LiveData<DatosGameActivity> datosGameActivityLiveData;
    private RepositorioDatosGameActivity repositorioDatosGameActivity;

    public ViewModelGameActivity(@NonNull Application application) {
        super(application);
        repositorioDatosGameActivity=new RepositorioDatosGameActivity(application);
        datosGameActivityLiveData=repositorioDatosGameActivity.getDatosGameActivityLiveData();
    }

    public LiveData<DatosGameActivity> getDatosGameActivityLiveData() {
        return datosGameActivityLiveData;
    }

    public void cargaDatosGameActivity(){
        datosGameActivityLiveData=repositorioDatosGameActivity.getDatosGameActivityLiveData();
    }




}
