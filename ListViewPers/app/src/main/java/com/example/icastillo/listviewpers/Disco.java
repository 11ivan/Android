package com.example.icastillo.listviewpers;

import android.widget.ImageView;

import java.util.Date;

/**
 * Created by icastillo on 17/10/17.
 */

/*
   Propiedades Basicas:
        Nombre:
        Artista:

   Propiedades Derivadas:


   Propiedades Agregadas:
        Date
        ImageView

 */

public class Disco {
    private String Nombre;
    private String Artista;
    private Date fecha;
    private ImageView imagen;

    public Disco(){

        Nombre=" ";
        Artista=" ";
        fecha=new Date();
        imagen=new ImageView();
    }


}
