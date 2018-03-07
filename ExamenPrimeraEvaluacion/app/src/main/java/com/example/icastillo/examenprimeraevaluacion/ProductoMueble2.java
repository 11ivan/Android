package com.example.icastillo.examenprimeraevaluacion;

/**
 * Created by icastillo on 22/11/17.
 */

public class ProductoMueble2 extends Producto2 {
    public ProductoMueble2() {
        super();
    }

    public ProductoMueble2(String nombre, String descripcion, String precio, int idImagen, String categoria, String color) {
        super(nombre, descripcion, precio, idImagen, categoria, color);
    }


    public ProductoMueble2(ProductoMueble2 productoMueble2) {
        super(productoMueble2.getNombre(), productoMueble2.getDescripcion(), productoMueble2.getPrecio(), productoMueble2.getIdImagen(), productoMueble2.getCategoria(), productoMueble2.getColor());
    }
}
