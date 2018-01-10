package com.example.icastillo.accesobasedatosnba;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by icastillo on 10/01/18.
 */

public class VMMainActivity extends ViewModel {

    public final MutableLiveData<Equipo> userLiveData = new MutableLiveData<>();

    public VMMainActivity() {
        // trigger user load.
        //userLiveData = new LiveData<>();
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

}
