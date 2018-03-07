package icastillo.examen2trimestre.application.application.room.repositories;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import icastillo.examen2trimestre.application.application.room.AppDataBase;
import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;

/**
 * Created by icastillo on 21/02/18.
 */

public class RepositorioFutbolistas {

    private static Application application;

    public RepositorioFutbolistas(Application application){
        this.application=application;
    }

    public LiveData<Futbolista[]> getLiveDataFutbolistas(){
        LiveData<Futbolista[]> liveData= AppDataBase.getDatabase(application.getApplicationContext()).futbolistasDAO().getLiveDataFutbolistas();
        return liveData;
    }

    public LiveData<Posicion[]> getLiveDataPosicionesFutbolista(Futbolista futbolista){
        LiveData<Posicion[]> liveDataPosiciones=AppDataBase.getDatabase(application.getApplicationContext())
                                                    .futbolistasDAO().getLiveDataPosicionesFutbolista(futbolista.getId());
        return liveDataPosiciones;
    }

    public Posicion[] getArrayPosicionesFutbolista(Futbolista futbolista){
        Posicion[] posicions=null;
        try {
            posicions = new GetArrayPosicionesFutbolista().execute(futbolista).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return posicions;
    }

    /*@SuppressLint("StaticFieldLeak")
    public Posicion[] getPosicionesFutbolista(Futbolista futbolista){
        Posicion[] posicions=null;
        new AsyncTask<Futbolista,Void,Posicion[]>() {
            @Override
            protected Posicion[] doInBackground(Futbolista... futbolistas) {
                Posicion[] posicions1= AppDataBase.getDatabase(application.getApplicationContext())
                                            .futbolistasDAO().getArrayPosicionesFutbolista(futbolistas[0].getId());
                return posicions1;
            }
            @Override
            protected void onPostExecute(Posicion[] posicions1) {
                posicions=posicions1;
            }
        }.execute(futbolista);
        return posicions;
    }*/

    public void insertFutbolista(Futbolista futbolista){
        new InsertFutbolistaAsync().execute(futbolista);
    }

    public void insertFutbolistas(Futbolista[] futbolistas){
        new InsertFutbolistasAsync().execute(futbolistas);
    }


    private class InsertFutbolistaAsync extends AsyncTask<Futbolista, Void, Void>{
        @Override
        protected Void doInBackground(Futbolista... futbolistas) {
            AppDataBase.getDatabase(application.getApplicationContext()).futbolistasDAO().insertFutbolista(futbolistas[0]);
            return null;
        }
    }

    private class InsertFutbolistasAsync extends AsyncTask<Futbolista, Void, Void>{
        @Override
        protected Void doInBackground(Futbolista... futbolistas) {
            AppDataBase.getDatabase(application.getApplicationContext()).futbolistasDAO().insertFutbolistas(futbolistas);
            return null;
        }
    }

    private class GetArrayPosicionesFutbolista extends AsyncTask<Futbolista, Void, Posicion[]>{
        @Override
        protected Posicion[] doInBackground(Futbolista... futbolistas) {
            Posicion[] posicions = AppDataBase.getDatabase(application.getApplicationContext())
                                        .futbolistasDAO().getArrayPosicionesFutbolista(futbolistas[0].getId());
            return posicions;
        }


    }


}
