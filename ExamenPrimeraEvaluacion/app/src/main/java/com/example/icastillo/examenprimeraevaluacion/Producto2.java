package com.example.icastillo.examenprimeraevaluacion;

/**
 * Created by icastillo on 22/11/17.
 *      Nombre: Cadena, Consultable, Mmodificable
 *      Descripcion: Cadena, Consultable, Modificable
 *      Precio: Cadena, Consultable, Modificableç
 *      IDImagen: Entero, Consultable, Mmodificable
 *      Categoria: Cadena, Consultable, Mmodificable
 *      Color: Cadena, Consultable, Mmodificable
 *
 *
 */

import java.util.ArrayList;


public abstract class Producto2 {

    //Propiedades
    private String nombre;
    private String descripcion;
    private String precio;
    private int idImagen;
    private String categoria;
    private String color;


    public Producto2() {
        nombre = "";
        descripcion = "";
        precio = "";
        idImagen = 0;
        categoria = "";
        color = "";

    }

    public Producto2(String nombre, String descripcion, String precio, int idImagen, String categoria, String color) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idImagen = idImagen;
        this.categoria = categoria;
        this.color = color;
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


    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
