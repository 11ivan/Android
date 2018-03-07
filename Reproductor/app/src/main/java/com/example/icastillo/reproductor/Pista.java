package com.example.icastillo.reproductor;

import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by icastillo on 23/10/17.
 */

/*
    Propiedades Básicas:
        Titulo: Cadena, Consultable, Modificable

    Propiedade Agregadas:
        MediaPlayer

* */

public class Pista {

    private String titulo;
    private MediaPlayer mediaPlayer;

    //Constructores
    public Pista(){
        titulo="";
        mediaPlayer=new MediaPlayer();
    }

    public Pista(String titulo, MediaPlayer mediaPlayer){
        this.titulo=titulo;
        this.mediaPlayer=mediaPlayer;
    }

    /*public Pista(Pista pista){

    }*/


    //Consultores
    public String getTitulo(){
        return titulo;
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }


    //Modificadores
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer=mediaPlayer;
    }




    //Metodos de Parceable


}
