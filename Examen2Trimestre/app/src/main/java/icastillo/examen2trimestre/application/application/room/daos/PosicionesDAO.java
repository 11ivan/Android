package icastillo.examen2trimestre.application.application.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import icastillo.examen2trimestre.application.application.room.entities.Posicion;

/**
 * Created by icastillo on 21/02/18.
 */

@Dao
public interface PosicionesDAO {

    @Insert
    public void insertPosiciones(Posicion... posiciones);

    @Query("Select * From Posiciones")
    public LiveData<Posicion[]> getLiveDataPosiciones();

    @Query("Select NombrePosicion From Posiciones where ID=:id")
    public String getNombrePosicion(int id);


}
