package com.example.icastillo.spinner;

/**
 * Created by icastillo on 02/11/2017.
 *
 *     Propiedades Basicas
             - Nombre: Cadena, Consultable, Modificable
             - idImagen: Entero, Consultable, Modificable

 *
 */

public class Supernova {

    private String nombre;
    private int idImagen;

    public Supernova(){
        nombre="";
        idImagen=0;
    }

    public Supernova(String nombre, int idImagen){
        this.nombre=nombre;
        this.idImagen=idImagen;
    }

    public Supernova(Supernova supernova){
        this.nombre=supernova.getNombre();
        this.idImagen=supernova.getIdImagen();
    }



    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }



}
