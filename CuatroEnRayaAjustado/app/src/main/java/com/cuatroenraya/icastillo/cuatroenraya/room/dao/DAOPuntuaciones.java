package com.cuatroenraya.icastillo.cuatroenraya.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

/**
 * Created by icastillo on 07/02/2018.
 */
@Dao
public interface DAOPuntuaciones {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insertPuntuaciones(Puntuacion[] puntuaciones);

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insertPuntuacion(Puntuacion puntuacion);

    @Delete
    public void deletePuntuacion(Puntuacion puntuacion);

    @Query("Delete from Puntuaciones Where IdUsuario = :idUsuario")
    public void deletePuntuacionesUsuario(int idUsuario);

    @Update
    public void updatePuntuacion(Puntuacion puntuacion);

    @Query("Select * From Puntuaciones")
    public LiveData<Puntuacion[]> getPuntuaciones();

    @Query("Select * From Puntuaciones Where IdUsuario=:idUsuario")
    public LiveData<Puntuacion> getPuntuacionesUsuario(int idUsuario);

}
