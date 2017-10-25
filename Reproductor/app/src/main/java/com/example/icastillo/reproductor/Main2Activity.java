package com.example.icastillo.reproductor;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends ListActivity implements View.OnClickListener  {

    //IconicAdapter<Pista> adapter;
    ImageButton buttonBack;

    Pista[] pistasElBarrio={new Pista("No volvere", MediaPlayer.create(this,  R.raw.novolvere)),
                            new Pista("He vuelto", MediaPlayer.create(this, R.raw.hevuelto)),
                            new Pista("Toreando el destino", MediaPlayer.create(this, R.raw.toreandoeldestino))
                            };

    Pista[] pistasOtras={new Pista("No volveré", MediaPlayer.create(this, R.raw.novolvere)),
                        new Pista("He vuelto", MediaPlayer.create(this, R.raw.hevuelto)),
                        new Pista("Toreando el destino", MediaPlayer.create(this, R.raw.toreandoeldestino))
                         };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonBack=(ImageButton) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(this);
        //cdElegido=(Disco) getIntent().getExtras().getParcelable("disco");//Obtengo el objeto Disco que llega desde MainActivity

        //adapter=loadTracks(cdElegido);// __<<<__Metodo para asignar el Array que convenga al iconic adapter
        //adapter=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, pistas);
        //setListAdapter(adapter);
    }

    @Override
    public void onClick(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    //Carga Pistas segun el disco elegido
   /* public IconicAdapter<Pista> loadTracks(Disco cd){
        IconicAdapter<Pista> adapter2=null;
        switch (cd.getNombre()) {

            case "Esencia":
                adapter2=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, pistasElBarrio);
                break;

            case "Blackanwai":
                adapter2=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, pistasOtras);
                break;

        }
        return adapter2;
    }*/



    //MiClase IconicAdapter
    /*class IconicAdapter<T> extends ArrayAdapter<T> {

        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder2 viewHolder;
            //ImageView imagen = (ImageView) row.findViewById(R.id.imagen);

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();

                //Comprobar pares e impares para inflar un layout u otro
                if(getViewTypeCount()%2==0){
                    row=inflater.inflate(R.layout.stylelistpistas1, parent, false);
                }else{
                    row=inflater.inflate(R.layout.stylelistpistas2, parent, false);
                }

                viewHolder=new ViewHolder2(row, R.id.txtNombrePista, R.id.txtDuracion);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder2) row.getTag();
            }

            //Los cambios a convenir
            viewHolder.getNombrePista().setText(pistasElBarrio[position].getTitulo());
            viewHolder.getDuracionPista().setText(pistasElBarrio[position].getMediaPlayer().getDuration());
            return (row);
        }
    }*///fin clase iconicAdapter

}
