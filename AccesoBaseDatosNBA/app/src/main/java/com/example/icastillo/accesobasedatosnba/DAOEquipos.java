package com.example.icastillo.accesobasedatosnba;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by icastillo on 09/01/2018.
 */

@Dao
public interface DAOEquipos {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertEquipos(Equipo... equipos);

    @Update
    public void updateEquipos(Equipo... equipos);

    @Delete
    public void deleteEquipos(Equipo... equipos);

    @Query("Select * From Equipos")
    public Equipo[] getEquiposArray();

    @Query("Select * From Equipos")
    public LiveData<Equipo[]> getEquiposLiveData();
}
