package icastillo.pruebaexamen2trimestre.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import icastillo.pruebaexamen2trimestre.room.entities.Coche;

/**
 * Created by icastillo on 20/02/18.
 */
@Dao
public interface CochesDAO {

    @Insert
    public void insertCoche(Coche coche);

    @Update
    public void updateCoche(Coche coche);

    @Delete
    public void deleteCoche(Coche coche);

    @Query("Select * From Coches")
    public LiveData<Coche[]> getLiveDataCoches();

    @Query("Select * From Coches where ID=:idPersona")
    public LiveData<Coche[]> getCochesPersonaLiveData(int idPersona);

    @Query("Select * From Coches where ID=:idCoche")
    public LiveData<Coche> getCoche(int idCoche);

    @Query("Delete From Personas where ID=:idCoche")
    public void deleteCoche(int idCoche);

}
