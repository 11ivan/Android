package com.example.icastillo.listviewpers;

import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Created by icastillo on 17/10/17.
 */

/*
   Propiedades Basicas:
        Nombre:
        Artista:
        Imagen:

   Propiedades Derivadas:


   Propiedades Agregadas:
        GregorianCalendar


 */

public class Disco {
    private String nombre;
    private String artista;
    private GregorianCalendar fecha;
    private String imagen;
    //private Arr

    //Constructores
    public Disco() {
        nombre = " ";
        artista = " ";
        fecha = new GregorianCalendar();
        imagen = " ";
    }

    public Disco(String nombre, String artista, GregorianCalendar fecha, String imagen) {
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = (GregorianCalendar) fecha.clone();
        this.imagen = imagen;
    }


    //Consultores
    public String getNombre() {
        return this.nombre;
    }

    public String getArtista() {
        return this.artista;
    }


    public GregorianCalendar getFecha() {
        return this.fecha;
    }

    public String getImagen() {
        return this.imagen;
    }

    //Modificadores
    public void setNombre(String nombre) {
        if (nombre != null && nombre.replaceAll(" ", "").length() > 0) {
            this.nombre = nombre;
        }
    }

    public void setArtista(String artista) {
        if (artista != null && artista.replaceAll(" ", "").length() > 0) {
            this.artista = artista;
        }
    }

    public void setFecha(GregorianCalendar fecha) {
        //if(fecha!=null && fecha.before(GregorianCalendar.getInstance())){
            this.fecha = fecha;
        //}
    }

    public void setImagen(String imagen) {
        if (imagen != null && imagen.replaceAll(" ", "").length() > 0) {
            this.imagen = imagen;
        }
    }

    public String toString(){
        String cadena;
            cadena=this.nombre+", "+this.artista+", "+fecha.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        return cadena;
    }

}