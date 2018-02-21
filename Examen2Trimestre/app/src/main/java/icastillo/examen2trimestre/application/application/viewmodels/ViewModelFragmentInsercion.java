package icastillo.examen2trimestre.application.application.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import icastillo.examen2trimestre.application.application.room.entities.Posicion;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioPosiciones;

/**
 * Created by icastillo on 21/02/18.
 */

public class ViewModelFragmentInsercion extends AndroidViewModel {

    private RepositorioPosiciones repositorioPosiciones;
    private LiveData<Posicion[]> liveDataPosiciones;

    public ViewModelFragmentInsercion(@NonNull Application application) {
        super(application);

        repositorioPosiciones=new RepositorioPosiciones(application);
        liveDataPosiciones=repositorioPosiciones.getLiveDataPosiciones();
    }

    public LiveData<Posicion[]> getLiveDataPosiciones() {
        return liveDataPosiciones;
    }

    public void cargaPosiciones(){
        liveDataPosiciones=repositorioPosiciones.getLiveDataPosiciones();
    }


}
