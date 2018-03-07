package com.example.icastillo.listanba;

/**
 * Created by icastillo on 11/11/2017.
 *
 * Propiedades Básicas:
 *      Nombre: Cadena, Consultable, Modificable
 *      Estadio: Cadena, Consultable, Modificable
 *      IDImagen: Entero, Consultable, Modificable
 *
 * */



public class Equipo {

    private String nombre;
    private String estadio;
    private Integer idImagen;

    public Equipo(){
        nombre="";
        estadio="";
        idImagen=0;
    }

    public Equipo(String nombre, String estadio, Integer idImagen){
        this.nombre=nombre;
        this.estadio=estadio;
        this.idImagen=idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String toString(){
        return (nombre+","+estadio);
    }



}
