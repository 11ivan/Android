package com.cuatroenraya.icastillo.cuatroenraya.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;

/**
 * Created by icastillo on 07/02/2018.
 */

@Dao
public interface DAOConfiguraciones {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insertConfiguracion(Configuracion configuracion);

    @Delete
    public void deleteConfiguracion(Configuracion configuracion);

    @Query("Delete from Configuraciones Where IdUsuario = :idUsuario")
    public void deleteConfiguracion(int idUsuario);

    @Update
    public void updateConfiguracion(Configuracion configuracion);

    @Query("Update Configuraciones set TipoTablero=:tipoTablero WHERE idUsuario=:idUsuario")
    public void updateConfiguracionUsuario(int tipoTablero, int idUsuario);

    @Query("Select * From Configuraciones")
    public LiveData<Configuracion[]> getConfiguraciones();

    @Query("Select * From Configuraciones Where IdUsuario=:idUsuario")
    public LiveData<Configuracion> getLiveDataConfiguracionUsuario(int idUsuario);

    @Query("Select * From Configuraciones Where IdUsuario=:idUsuario")
    public Configuracion getConfiguracionUsuario(int idUsuario);

}
