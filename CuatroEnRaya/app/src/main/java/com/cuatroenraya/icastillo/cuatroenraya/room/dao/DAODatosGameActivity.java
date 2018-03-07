package com.cuatroenraya.icastillo.cuatroenraya.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.widget.ListView;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.DatosGameActivity;

/**
 * Created by icastillo on 18/02/2018.
 */

@Dao
public interface DAODatosGameActivity {

    @Insert
    public void insertDatosGameActivity(DatosGameActivity datosGameActivity);

    @Delete
    public void deleteGatosGameActivity(DatosGameActivity datosGameActivity);

    @Query("Select * From PartidaGuardada")
    public LiveData<DatosGameActivity> getDatosGameActivity();

}
