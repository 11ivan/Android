package com.example.icastillo.reproductor;

import android.media.MediaPlayer;

/**
 * Created by icastillo on 23/10/17.
 */

/*
    Propiedades Básicas:
        Titulo: Cadena, Consultable, Modificable
        Duración: Decimal, Consultable, Modificable



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

    public Pista(Pista pista){

    }



}
