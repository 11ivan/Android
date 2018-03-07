package icastillo.examen2trimestre.application.application.room.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import icastillo.examen2trimestre.application.application.room.AppDataBase;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;

/**
 * Created by icastillo on 21/02/18.
 */

public class RepositorioPosiciones {

    private static Application application;

    public RepositorioPosiciones(Application application){
        this.application=application;
    }

    public LiveData<Posicion[]> getLiveDataPosiciones(){
        LiveData<Posicion[]> liveData= AppDataBase.getDatabase(application.getApplicationContext()).posicionesDAO().getLiveDataPosiciones();
        return liveData;
    }

    public String getNombrePosicion(int id){
        String nombre="";
        try {
            nombre=new GetNombrePosicion().execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return nombre;
    }

    public void insertPosiciones(Posicion[] posiciones){
        new InsertPosicionAsync().execute(posiciones);
    }


    private class InsertPosicionAsync extends AsyncTask<Posicion, Void, Void> {
        @Override
        protected Void doInBackground(Posicion... posicions) {
            AppDataBase.getDatabase(application.getApplicationContext()).posicionesDAO().insertPosiciones(posicions);
            return null;
        }
    }

    private class GetNombrePosicion extends AsyncTask<Integer, Void, String>{
        @Override
        protected String doInBackground(Integer... ids) {
            String posicion=AppDataBase.getDatabase(application.getApplicationContext()).posicionesDAO().getNombrePosicion(ids[0]);
            return posicion;
        }
    }


}
