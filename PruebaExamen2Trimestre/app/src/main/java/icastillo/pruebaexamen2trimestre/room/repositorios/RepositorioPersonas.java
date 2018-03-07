package icastillo.pruebaexamen2trimestre.room.repositorios;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import icastillo.pruebaexamen2trimestre.room.AppDataBase;
import icastillo.pruebaexamen2trimestre.room.entities.Persona;

/**
 * Created by icastillo on 20/02/18.
 */

public class RepositorioPersonas {

    private static Application application;

    public RepositorioPersonas(Application application){
        this.application=application;
    }


    public LiveData<Persona[]> getLiveDataPersonas(){
        LiveData<Persona[]> liveData= AppDataBase.getDatabase(application.getApplicationContext()).personasDAO().getLiveDataPersonas();
        return liveData;
    }

    public void insertPersona(Persona persona){
        new InsertPersonaAsync().execute(persona);
    }

    public void insertPersonas(Persona[] personas){
        new InsertPersonasAsync().execute(personas);
    }

    public void updatePersona(Persona persona){
        new UpdatePersonaAsync().execute(persona);
    }





    ///
    private static class InsertPersonaAsync extends AsyncTask<Persona, Void, Void>{
        @Override
        protected Void doInBackground(Persona... personas) {
            AppDataBase.getDatabase(application.getApplicationContext()).personasDAO().insertPersona(personas[0]);
            return null;
        }
    }

    private static class InsertPersonasAsync extends AsyncTask<Persona, Void, Void>{
        @Override
        protected Void doInBackground(Persona... personas) {
            AppDataBase.getDatabase(application.getApplicationContext()).personasDAO().insertPersonas(personas);
            return null;
        }
    }

    private class UpdatePersonaAsync extends AsyncTask<Persona, Void, Void>{
        @Override
        protected Void doInBackground(Persona... personas) {
            AppDataBase.getDatabase(application.getApplicationContext()).personasDAO().updatePersona(personas[0]);
            return null;
        }
    }

}
