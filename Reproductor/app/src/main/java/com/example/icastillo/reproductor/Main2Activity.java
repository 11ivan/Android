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

import java.util.Calendar;

public class Main2Activity extends ListActivity implements View.OnClickListener  {

    ImageButton buttonBack;
    Disco cd;

    Pistas[] pistasElBarrio={new Pista("No volveré", MediaPlayer.create(this, R.raw.novolvere)), };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonBack=(ImageButton) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(this);
        cd=(Disco) getIntent().getExtras().getParcelable("disco");
    }

    @Override
    public void onClick(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //Carga Pistas
    public void loadTracks(){
        cd.addPista(new Pista("No volveré", MediaPlayer.create(this, R.raw.novolvere)));
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

            /*if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.stylelist, parent, false);
                viewHolder=new ViewHolder(row, R.id.imageView, R.id.txtNombre, R.id.txtArtista, R.id.txtFecha);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) row.getTag();
            }*/


            return (row);
        }
    }//fin clase iconicAdapter

}
