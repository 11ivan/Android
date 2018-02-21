package icastillo.examen2trimestre.application.application.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;

/**
 * Created by icastillo on 21/02/18.
 */

@Dao
public interface FutbolistasDAO {

    @Insert
    public void insertFutbolista(Futbolista futbolista);

    @Insert
    public void insertFutbolistas(Futbolista... futbolista);

    @Update
    public void updateFutbolista(Futbolista futbolista);

    @Delete
    public void deleteFutbolista(Futbolista futbolista);

    @Query("Select * From Futbolistas")
    public LiveData<Futbolista[]> getLiveDataFutbolistas();

    @Query("Select * From Futbolistas Where ID=:idFutbolista")
    public LiveData<Futbolista> getLiveDataFutbolista(int idFutbolista);

    @Query("Select P.ID, P.NombrePosicion From Futbolistas as F inner Join Posiciones as P on F.IdPosiciones=P.ID where F.ID=:idFutbolista")
    public LiveData<Posicion[]> getLiveDataPosicionesFutbolista(int idFutbolista);

    @Query("Select P.ID, P.NombrePosicion From Posiciones as P inner Join Futbolistas as F on F.IdPosiciones=P.ID where F.ID=:idFutbolista")
    public Posicion[] getArrayPosicionesFutbolista(int idFutbolista);

}
