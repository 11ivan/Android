package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 *
 *
 * Propiedades:
 *      Talla: Cadena, Basica, Consultable, Modificable
 */


public class ProductoModa2 extends Producto2 {
    private String talla;

    public ProductoModa2(String talla) {
        super();
        this.talla = talla;
    }

    public ProductoModa2(String nombre, String descripcion, String precio, int idImagen, String categoria, String color, String talla) {
        super(nombre, descripcion, precio, idImagen, categoria, color);
        this.talla = talla;
    }

    public ProductoModa2(ProductoModa2 productoModa2) {
        super(productoModa2.getNombre(), productoModa2.getDescripcion(), productoModa2.getPrecio(), productoModa2.getIdImagen(), productoModa2.getCategoria(), productoModa2.getColor());
        this.talla = productoModa2.getTalla();
    }
    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
