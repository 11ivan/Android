package com.example.icastillo.spinner;

/**
 * Created by icastillo on 02/11/2017.


    Propiedades Basicas
        - Nombre: Cadena, Consultable, Modificable
        - idImagen: Entero, Consultable, Modificable

 */

public class Estrella {

    private String nombre;
    private int idImagen;

    public Estrella(){
    nombre="";
    idImagen=0;
}

    public Estrella(String nombre, int idImagen){
        this.nombre=nombre;
        this.idImagen=idImagen;
    }

    public Estrella(Estrella estrella){
        this.nombre=estrella.getNombre();
        this.idImagen=estrella.getIdImagen();
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
