package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.app.Activity;
import android.app.ListActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.clases.ViewHolderPuntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.viewmodels.ViewModelPuntuacionesActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PuntuacionesActivity extends AppCompatActivity {

    Puntuacion[] arrayPuntuaciones;
    ViewModelPuntuacionesActivity viewModelPuntuacionesActivity;
    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        listView=(ListView)findViewById(R.id.listView);
        //adapter=new MyAdapter<Puntuacion>(this, R.id.txtResultado, arrayPuntuaciones);


        viewModelPuntuacionesActivity= ViewModelProviders.of(this).get(ViewModelPuntuacionesActivity.class);
        viewModelPuntuacionesActivity.getPuntuacionesLiveData().observe(this, new Observer<Puntuacion[]>() {
            @Override
            public void onChanged(@Nullable Puntuacion[] puntuacions) {
                arrayPuntuaciones=puntuacions;
                adapter=new MyAdapter<Puntuacion>(getApplicationContext(), R.layout.stylepuntuaciones, R.id.txtResultado, arrayPuntuaciones);
                listView.setAdapter(adapter);
            }
        });
        viewModelPuntuacionesActivity.cargaPuntuaciones();

    }



    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, T[] object){
            super(c, resourceId, textId, object);
        }

        @Override
        public int getViewTypeCount(){
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolderPuntuacion viewHolder=null;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();

                row=inflater.inflate(R.layout.stylepuntuaciones, parent, false);

                viewHolder=new ViewHolderPuntuacion(row);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolderPuntuacion) row.getTag();
            }

            GregorianCalendar gregorianCalendar=new GregorianCalendar();
            gregorianCalendar.setTime(arrayPuntuaciones[position].getFechaPartida());
            int dia=gregorianCalendar.get(Calendar.DAY_OF_MONTH);
            int mes =gregorianCalendar.get(Calendar.MONTH);
            mes+=1;
            int year=gregorianCalendar.get(Calendar.YEAR);

            viewHolder.getTextViewResultado().setText(String.valueOf(arrayPuntuaciones[position].getResultado()));
            viewHolder.getTextViewFechaPertida().setText(dia+"/"+mes+"/"+year);
            viewHolder.getTextViewTiempoPartida().setText(arrayPuntuaciones[position].getTiempoPartida());

            return (row);
        }

    }


}
