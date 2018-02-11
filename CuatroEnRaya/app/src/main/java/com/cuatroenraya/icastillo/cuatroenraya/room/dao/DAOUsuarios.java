package com.cuatroenraya.icastillo.cuatroenraya.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;

/**
 * Created by icastillo on 07/02/2018.
 */
@Dao
public interface DAOUsuarios {

    //Para trabajar con LiveData todas las operaciones deben devolver un Objeto del mismo para la actualizacion automatica

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insertUsuarios(Usuario[] usuarios);

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public void insertUsuario(Usuario usuario);

    @Delete
    public void deleteUsuario(Usuario usuario);

    @Query("Delete from Usuarios Where ID = :id")
    public void deleteUsuario(int id);

    @Update
    public void updateUsuario(Usuario usuario);

    @Query("Update Usuarios set Nombre=:nombre Where ID=:id")
    public void updateNombreUsuario(String nombre, int id);

    @Query("Select * From Usuarios")
    public LiveData<Usuario[]> getUsuariosLiveData();

    @Query("Select * From Usuarios")
    public Usuario[] getUsuarios();

    @Query("Select * From Usuarios Where ID=:id")
    public LiveData<Usuario> getUsuario(int id);

    @Query("Select * From Usuarios LIMIT 1")
    public LiveData<Usuario> getUsuario();

}
