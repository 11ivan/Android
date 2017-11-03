package com.example.icastillo.spinner;

/**
 * Created by icastillo on 02/11/2017.
 *
 * Propiedades Básicas:
 *          - Nombre: Cadena, Consultable, Modificable
 *          - IdImagen: Entero, Consultable, Modificable
 */



public class Planeta {

    private String nombre;
    private int idImagen;


    //Constructors
    public Planeta(){
        nombre="";
        idImagen=0;
    }

    public Planeta(String nombre, int idImagen){
        this.nombre=nombre;
        this.idImagen=idImagen;
    }

    public Planeta(Planeta planeta){
        this.nombre=planeta.getNombre();
        this.idImagen=planeta.getIdImagen();
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
