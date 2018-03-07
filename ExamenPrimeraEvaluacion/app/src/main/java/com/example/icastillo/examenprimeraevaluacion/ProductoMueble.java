package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 *
 * Propiedades Basicas:
 *      Colores: ArrayList de cadena, consultable, modificable
 *
 *
 */

public class ProductoMueble extends Producto{


    public ProductoMueble() {
        super();
    }

    public ProductoMueble(String nombre, String descripcion, String precio, ArrayList<Integer> listaIDImagenes, ArrayList<String> arrayColores) {
        super(nombre, descripcion, precio, listaIDImagenes, arrayColores);
    }

    public ProductoMueble(ProductoMueble productoMueble){
        super(productoMueble.getNombre(), productoMueble.getDescripcion(), productoMueble.getPrecio(), productoMueble.getListaIDImagenes(), productoMueble.getArrayColores());

    }

}
