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
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends ListActivity implements View.OnClickListener  {
    Gestora gestora=new Gestora();
    IconicAdapter<Pista> adapter;
    ImageButton buttonBack;
    Disco cdElegido;
    MediaPlayer mediaPlayer;
    /*Pista[] pistasElBarrio={new Pista("No volvere", MediaPlayer.create(this, R.raw.novolvere)),
                            new Pista("He vuelto", MediaPlayer.create(this, R.raw.hevuelto)),
                            new Pista("Toreando el destino", MediaPlayer.create(this, R.raw.toreandoeldestino))
                            };*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mediaPlayer=new MediaPlayer();
        buttonBack=(ImageButton) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(this);
        cdElegido=(Disco) getIntent().getExtras().getParcelable("disco");//Obtengo el objeto Disco que llega desde MainActivity
        loadTracks(cdElegido);
        //adapter=loadTracks(cdElegido);// __<<<__Metodo para asignar el Array que convenga al iconic adapter
        adapter=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, gestora.toArray(cdElegido.getPistas()));
        setListAdapter(adapter);
    }

    @Override
    public void onClick(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onListItemClick(ListView l, View v, int position, long id){
        //mediaPlayer=cdElegido.getAudio(position).getMediaPlayer();
        //Toast.makeText(this, String.valueOf(mediaPlayer.getAudioSessionId()), Toast.LENGTH_LONG).show();
        int idCurrentSesion=mediaPlayer.getAudioSessionId();

        if(mediaPlayer.getAudioSessionId()!=cdElegido.getAudio(position).getMediaPlayer().getAudioSessionId() && mediaPlayer.isPlaying()){//si la pista que llega es distinta de la actual y la actual está
            mediaPlayer.stop();   //paro la actual                                                                                                        //sonando
            mediaPlayer=cdElegido.getAudio(position).getMediaPlayer();//asigno a mediaplayer el mediaplayer de la pista que llega
            mediaPlayer.start();    //Comienza la reproduccion
        }else if(mediaPlayer.getAudioSessionId()==cdElegido.getAudio(position).getMediaPlayer().getAudioSessionId()){//sino si son iguales es que está sonando
                mediaPlayer.pause();
            }else {//sino es que no son iguales por tanto no está sonando
                    mediaPlayer=cdElegido.getAudio(position).getMediaPlayer();
                    mediaPlayer.start();
                }

       /* if(idCurrentSesion!=cdElegido.getAudio(position).getMediaPlayer().getAudioSessionId()){
            cdElegido.getAudio(position).getMediaPlayer().stop();

        }else {
            cdElegido.getAudio(position).getMediaPlayer().start();
        }*/

    }

    //Obtener pista en curso, -1 en caso de no haber ninguna
    /*public int getPistaActual(){
        int actual=-1;


    }*/

    //Carga Pistas segun el disco elegido
    public void loadTracks(Disco cd){
        //IconicAdapter<Pista> adapter2=null;
        switch (cd.getNombre()) {

            case "Esencia":
                //adapter2=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, pistasElBarrio);
                cdElegido.addPista(new Pista("No volveré", MediaPlayer.create(this, R.raw.novolvere)));
                cdElegido.addPista(new Pista("He vuelto", MediaPlayer.create(this, R.raw.hevuelto)));
                cdElegido.addPista(new Pista("Toreando el destino", MediaPlayer.create(this, R.raw.amaraslanoche)));
                break;

            case "Blackanwai":
                //adapter2=new IconicAdapter<Pista>(this, R.layout.stylelistpistas1, R.id.txtNombrePista, pistasOtras);
                break;

        }
    }



    //MiClase IconicAdapter
    class IconicAdapter<T> extends ArrayAdapter<T> {

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
                if(position%getViewTypeCount()==0){
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
            viewHolder.getNombrePista().setText(cdElegido.getAudio(position).getTitulo());
            viewHolder.getDuracionPista().setText(String.valueOf(cdElegido.getAudio(position).getMediaPlayer().getDuration()));
            return (row);
        }
    }//fin clase iconicAdapter

}
