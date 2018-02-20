package icastillo.pruebaexamen2trimestre.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import icastillo.pruebaexamen2trimestre.room.entities.Persona;
import icastillo.pruebaexamen2trimestre.room.repositorios.RepositorioPersonas;

/**
 * Created by icastillo on 20/02/2018.
 */

public class ViewModel extends AndroidViewModel {

    private LiveData<Persona[]> liveDataPersonas;
    RepositorioPersonas repositorioPersonas;

    public ViewModel(@NonNull Application application) {
        super(application);

        repositorioPersonas=new RepositorioPersonas(application);
        liveDataPersonas=repositorioPersonas.getLiveDataPersonas();
    }

    public LiveData<Persona[]> getLiveDataPersonas() {
        return liveDataPersonas;
    }

    public void cargaPersonas(){
        liveDataPersonas=repositorioPersonas.getLiveDataPersonas();
    }




}
