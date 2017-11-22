package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 *
 *
 * Propiedades Básicas:
 *      Tallas: ArrayList de cadenas, consultable, modificable
 *
 *
 *
 *
 *
*/

public class ProductoModa extends Producto {

    //Propiedades
    private ArrayList<String> arrayTallas;


    public ProductoModa(ArrayList<String> arrayTallas) {
        this.arrayTallas = arrayTallas;
    }

    public ProductoModa(String nombre, String descripcion, String precio, ArrayList<Integer> listaIDImagenes, ArrayList<String> arrayColores, ArrayList<String> arrayTallas) {
        super(nombre, descripcion, precio, listaIDImagenes, arrayColores);
        this.arrayTallas = arrayTallas;
    }

    public ProductoModa(ProductoModa productoModa){
        super(productoModa.getNombre(), productoModa.getDescripcion(), productoModa.getPrecio(), productoModa.getListaIDImagenes(), productoModa.getArrayColores());
        this.arrayTallas=productoModa.getArrayTallas();
    }

    public ArrayList<String> getArrayTallas() {
        return arrayTallas;
    }

    public void setArrayTallas(ArrayList<String> arrayTallas) {
        this.arrayTallas = arrayTallas;
    }
}
