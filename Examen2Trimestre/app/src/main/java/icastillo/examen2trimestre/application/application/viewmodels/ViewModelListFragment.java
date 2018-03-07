package icastillo.examen2trimestre.application.application.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioFutbolistas;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioPosiciones;

/**
 * Created by icastillo on 21/02/18.
 */

public class ViewModelListFragment extends AndroidViewModel {

    private RepositorioFutbolistas repositorioFutbolistas;
    private RepositorioPosiciones repositorioPosiciones;
    private LiveData<Futbolista[]> liveDataFutbolistas;
    private LiveData<Posicion[]> liveDataPosiciones;


    public ViewModelListFragment(@NonNull Application application) {
        super(application);

        repositorioFutbolistas=new RepositorioFutbolistas(application);
        repositorioPosiciones=new RepositorioPosiciones(application);
        liveDataFutbolistas=repositorioFutbolistas.getLiveDataFutbolistas();
        liveDataPosiciones=repositorioPosiciones.getLiveDataPosiciones();

    }

    public LiveData<Futbolista[]> getLiveDataFutbolistas() {
        return liveDataFutbolistas;
    }

    public LiveData<Posicion[]> getLiveDataPosiciones() {
        return liveDataPosiciones;
    }

    public void cargaFutbolistas(){
        liveDataFutbolistas=repositorioFutbolistas.getLiveDataFutbolistas();
    }

    public void cargaPosiciones(){
        liveDataPosiciones=repositorioPosiciones.getLiveDataPosiciones();
    }





}
