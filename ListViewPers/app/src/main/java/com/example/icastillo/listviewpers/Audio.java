package com.example.icastillo.listviewpers;

/**
 * Created by icastillo on 23/10/17.
 */

/*
    Propiedades Básicas:
        Titulo: Cadena, Consultable, Modificable
        Duración: Decimal, Consultable, Modificable



* */

public class Audio {

    private String titulo;
    private Double duracion;


    //Constructores

    public Audio(){
        titulo="";
        duracion=0.0;
    }

    public Audio(String titulo, Double duracion){
        this.titulo=titulo;
        this.duracion=duracion;
    }

    public Audio(Audio audio){

    }



}
