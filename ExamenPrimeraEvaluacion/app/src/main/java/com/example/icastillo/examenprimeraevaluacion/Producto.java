package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 *
 * Propiedades Basicas:
 *      Nombre: Cadena, Consultable, Mmodificable
 *      Descripcion: Cadena, Consultable, Modificable
 *      Precio: Cadena, Consultable, Modificable
 *
 *
 */

public abstract class Producto {

    //Propiedades
    private String nombre;
    private String descripcion;
    private String precio;
    private ArrayList<Integer> listaIDImagenes;
    private ArrayList<String> arrayColores;


     public Producto(){
         nombre="";
         descripcion="";
         precio="";
         listaIDImagenes=new ArrayList<Integer>();
         arrayColores=new ArrayList<String>();
     }

    public Producto(String nombre, String descripcion, String precio, ArrayList<Integer> listaIDImagenes, ArrayList<String> arrayColores) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.listaIDImagenes = listaIDImagenes;
        this.arrayColores = arrayColores;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public ArrayList<Integer> getListaIDImagenes() {
        return listaIDImagenes;
    }

    public void setListaIDImagenes(ArrayList<Integer> listaIDImagenes) {
        this.listaIDImagenes = listaIDImagenes;
    }

    public ArrayList<String> getArrayColores() {
        return arrayColores;
    }

    public void setArrayColores(ArrayList<String> arrayColores) {
        this.arrayColores = arrayColores;
    }

    public Integer getIdImagen(int position){
         Integer idImagen;
         idImagen=listaIDImagenes.get(position);
         return idImagen;
    }
}
