package com.example.icastillo.accesobasedatosnba;

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
public interface DAOJugadores {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertJugadores(Jugador... jugadores);

    @Update
    public void updateJugadores(Jugador... jugadores);

    @Delete
    public void deleteJugadores(Jugador... jugadores);

    @Query("Select * From Equipos")
    public Equipo[] getEquipos();
}
