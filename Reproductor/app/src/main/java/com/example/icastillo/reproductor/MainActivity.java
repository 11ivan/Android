package com.example.icastillo.reproductor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    IconicAdapter<Disco> adapterDiscos;
    Disco[] arrayDiscos={new Disco("Blakanguai", "Varios artistas", new GregorianCalendar(2016, 10, 5),String.valueOf(R.drawable.bw)),
            new Disco("Hay", "O no hay", new GregorianCalendar(2015, 1, 15),String.valueOf(R.drawable.bw)),
            new Disco("Pffff", "Quien tu quieras", new GregorianCalendar(2017, 5, 25), String.valueOf(R.drawable.bw))};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.list);
        textView
        adapterDiscos=new IconicAdapter<Disco>(this, R.layout.stylelist, R.id.txtView, arrayDiscos);
        listView.setAdapter(adapterDiscos);
    }






    //MiClase IconicAdapter
    class IconicAdapter<T> extends ArrayAdapter<T> {

        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //View row = super.getView(position, convertView, parent);
            //ImageView imagen = (ImageView) row.findViewById(R.id.imagen);

            //imagen.setImageResource(Integer.parseInt(arrayDiscos[position].getImagen()));

            //return (row);
        }

    }//fin clase iconicAdapter








}
