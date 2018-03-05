package com.cuatroenraya.icastillo.cuatroenraya.room.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.cuatroenraya.icastillo.cuatroenraya.room.AppDatabase;
import com.cuatroenraya.icastillo.cuatroenraya.room.dao.DAODatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.DatosGameActivity;

/**
 * Created by icastillo on 18/02/2018.
 */

public class RepositorioDatosGameActivity {

    private static Application application;

    public RepositorioDatosGameActivity(Application application){
        this.application=application;
    }

    public LiveData<DatosGameActivity> getDatosGameActivityLiveData(){
        LiveData<DatosGameActivity> datosGameActivityLiveData= AppDatabase.getDatabase(application.getApplicationContext()).daoDatosGameActivityDAO().getDatosGameActivity();
        return datosGameActivityLiveData;
    }

    public void deleteDatosGameActivity(DatosGameActivity datosGameActivity){
        new DeleteDatosGameActivityAsync().execute(datosGameActivity);
    }

    public void insertDatosGameActivity(DatosGameActivity datosGameActivity){
        new InsertDatosGameActivityAsync().execute(datosGameActivity);
    }


    public static class InsertDatosGameActivityAsync extends AsyncTask<DatosGameActivity, Void, Void>{
        @Override
        protected Void doInBackground(DatosGameActivity... datosGameActivities) {
            AppDatabase.getDatabase(application.getApplicationContext()).daoDatosGameActivityDAO().insertDatosGameActivity(datosGameActivities[0]);
            return null;
        }
    }

    public static class DeleteDatosGameActivityAsync extends AsyncTask<DatosGameActivity, Void, Void>{
        @Override
        protected Void doInBackground(DatosGameActivity... datosGameActivities) {
            AppDatabase.getDatabase(application.getApplicationContext()).daoDatosGameActivityDAO().deleteGatosGameActivity(datosGameActivities[0]);
            return null;
        }
    }

}
