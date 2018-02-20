package icastillo.pruebaexamen2trimestre.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import icastillo.pruebaexamen2trimestre.room.entities.Persona;

/**
 * Created by icastillo on 20/02/18.
 */
@Dao
public interface PersonasDAO{

    @Insert
    public void insertPersona(Persona persona);

    @Update
    public void updatePersona(Persona persona);

    @Delete
    public void deletePersona(Persona persona);

    @Query("Select * From Personas")
    public LiveData<Persona[]> getLiveDataPersonas();

    @Query("Select * From Personas where ID=:idPersona")
    public LiveData<Persona> getPersonaLiveData(int idPersona);

    @Query("Delete From Personas where ID=:idPersona")
    public void deletePersona(int idPersona);


}
