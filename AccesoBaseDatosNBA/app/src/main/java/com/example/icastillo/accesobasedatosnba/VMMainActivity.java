package com.example.icastillo.accesobasedatosnba;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by icastillo on 10/01/18.
 */

public class VMMainActivity extends AndroidViewModel{

    public  MutableLiveData<Equipo[]> equiposMLiveData=new MutableLiveData<Equipo[]>();
    //public  LiveData<Equipo[]> equiposMLiveData=new MutableLiveData<Equipo[]>();
    private RepositorioEquipos repositorioEquipos;

    public VMMainActivity(@NonNull Application application) {
        super(application);
        cargaRepositorio(application);
    }

   /* public VMMainActivity() {
        // trigger user load.
        //userLiveData = new LiveData<>();
    }*/

    public void cargaRepositorio(Application application){
        repositorioEquipos=new RepositorioEquipos(application);
        //repositorioEquipos.setApplication(application);
    }

    @SuppressLint("StaticFieldLeak")
    public void cargaLista(){
        //this.equiposMLiveData=repositorioEquipos.getLiveDataEquipos();

        new AsyncTask<Void,Void,Equipo[]>() {
            @Override
            protected Equipo[] doInBackground(Void... voids) {
                Equipo[] equipos = repositorioEquipos.getArrayEquipos();
                //Equipo[] equipos = repositorioEquipos.getLiveDataEquipos().getValue();//Asi NO
                return equipos;
                //return new Equipo[0];
            }
            @Override
            protected void onPostExecute(Equipo[] equipos) {
                equiposMLiveData.setValue(equipos);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertaEquipos(Equipo[] equipos){
        new AsyncTask<Equipo, Void, Void>(){
            @Override
            protected Void doInBackground(Equipo... equipos) {
                repositorioEquipos.insertaEquipos(equipos);
                return null;
            }
        }.execute(equipos);
        cargaLista();

        /*LiveData<Equipo[]> liveDataEquipos;
        MutableLiveData<Equipo[]> mutableLiveData=new MutableLiveData<Equipo[]>();
        mutableLiveData.setValue(equipos);
        liveDataEquipos=mutableLiveData;

        repositorioEquipos.insertaLiveDataEquipos(liveDataEquipos);
        cargaLista();*/
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

}
