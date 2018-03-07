package icastillo.pruebaexamen2trimestre.room.repositorios;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.os.AsyncTask;

import icastillo.pruebaexamen2trimestre.room.AppDataBase;
import icastillo.pruebaexamen2trimestre.room.entities.Coche;

/**
 * Created by icastillo on 20/02/18.
 */

public class RepositorioCoches {

    private static Application application;

    public RepositorioCoches(Application application){
        this.application=application;
    }

    public LiveData<Coche[]> getLiveDataCoches(){
        LiveData<Coche[]> liveData= AppDataBase.getDatabase(application.getApplicationContext()).cochesDAO().getLiveDataCoches();
        return liveData;
    }

    public void insertCoche(Coche coche){
        new InsertCocheAsync().execute(coche);
    }

    public void insertCoches(Coche[] coches){ new InsertCochesAsync().execute(coches); }

    public void updateCoche(Coche coche){
        new UpdateCocheAsync().execute(coche);
    }

    ///
    private class InsertCocheAsync extends AsyncTask<Coche, Void, Void> {
        @Override
        protected Void doInBackground(Coche... coches) {
            AppDataBase.getDatabase(application.getApplicationContext()).cochesDAO().insertCoche(coches[0]);
            return null;
        }
    }

    private class InsertCochesAsync extends AsyncTask<Coche, Void, Void>{
        @Override
        protected Void doInBackground(Coche... coches) {
            AppDataBase.getDatabase(application.getApplicationContext()).cochesDAO().insertCoches(coches);
            return null;
        }
    }

    private class UpdateCocheAsync extends AsyncTask<Coche, Void, Void>{
        @Override
        protected Void doInBackground(Coche... coches) {
            AppDataBase.getDatabase(application.getApplicationContext()).cochesDAO().updateCoche(coches[0]);
            return null;
        }
    }










}
