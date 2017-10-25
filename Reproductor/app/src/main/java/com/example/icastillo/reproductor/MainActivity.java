package com.example.icastillo.reproductor;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends ListActivity {

    //ListView listView;
    //TextView textView;
    IconicAdapter<Disco> adapterDiscos;
    Disco cd1=new Disco("Esencia", "El Barrio", new GregorianCalendar(2016, 11, 6), R.drawable.bw);
    Disco cd2=new Disco("Blakanguai", "Varios artistas", new GregorianCalendar(2016, 10, 5), R.drawable.bw);


    Disco[] arrayDiscos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listView=(ListView) findViewById(R.id.list);
        adapterDiscos=new IconicAdapter<Disco>(this, R.layout.stylelist, R.id.txtNombre, arrayDiscos);
        setListAdapter(adapterDiscos);
    }



    //MiClase IconicAdapter
    class IconicAdapter<T> extends ArrayAdapter<T> {

        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder viewHolder;
            //ImageView imagen = (ImageView) row.findViewById(R.id.imagen);

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.stylelist, parent, false);
                viewHolder=new ViewHolder(row, R.id.imageView, R.id.txtNombre, R.id.txtArtista, R.id.txtFecha);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) row.getTag();
            }

            viewHolder.getImage().setImageResource(arrayDiscos[position].getImagen());
            viewHolder.getTextNombre().setText(arrayDiscos[position].getNombre());
            viewHolder.getTextArtista().setText(arrayDiscos[position].getArtista());
            viewHolder.getTextFecha().setText(arrayDiscos[position].getFecha().get(Calendar.DAY_OF_MONTH)+"/"+arrayDiscos[position].getFecha().get(Calendar.MONTH)+"/"+arrayDiscos[position].getFecha().get(Calendar.YEAR));

            return (row);
        }
    }//fin clase iconicAdapter








}
