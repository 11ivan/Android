package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 */

public class ListaProductos {

    private final ArrayList<Object> listaProductos;

    public ListaProductos(){
        ArrayList<Integer> imagenesCamisetas=new ArrayList<Integer>();
        imagenesCamisetas.add(R.drawable.camisetamarron);
        imagenesCamisetas.add(R.drawable.camisetanegra);
        imagenesCamisetas.add(R.drawable.camisetaroja);
        ArrayList<String> coloresCamisetas=new ArrayList<String>();
        coloresCamisetas.add("Marrón");
        coloresCamisetas.add("Negra");
        coloresCamisetas.add("Roja");
        ArrayList<String> tallasCamisetas=new ArrayList<String>();
        tallasCamisetas.add("S");
        tallasCamisetas.add("M");
        tallasCamisetas.add("L");

        ArrayList<Integer> imagenesPantalones=new ArrayList<Integer>();
        imagenesPantalones.add(R.drawable.pantalonbeige);
        imagenesPantalones.add(R.drawable.pantaloncaqui);
        imagenesPantalones.add(R.drawable.pantalonnegro);
        ArrayList<String> coloresPantalones=new ArrayList<String>();
        coloresPantalones.add("Beige");
        coloresPantalones.add("Caqui");
        coloresPantalones.add("Negro");
        ArrayList<String> tallasPantalones=new ArrayList<String>();
        tallasPantalones.add("38");
        tallasPantalones.add("42");
        tallasPantalones.add("44");

        ArrayList<Integer> imagenesZapatos=new ArrayList<Integer>();
        imagenesZapatos.add(R.drawable.zapatogris);
        imagenesZapatos.add(R.drawable.zapatomarron);
        imagenesZapatos.add(R.drawable.zapatonegro);
        ArrayList<String> coloresZapatos=new ArrayList<String>();
        coloresZapatos.add("Gris");
        coloresZapatos.add("Marrón");
        coloresZapatos.add("Negro");
        ArrayList<String> tallasZapatos=new ArrayList<String>();
        tallasZapatos.add("38");
        tallasZapatos.add("42");
        tallasZapatos.add("44");

        ProductoModa productoCamiseta=new ProductoModa("Camiseta", "Camiseta mangas cortas", "15.95", imagenesCamisetas, coloresCamisetas, tallasCamisetas);
        ProductoModa productoPantalon=new ProductoModa("Pantalón", "Pantalón largo", "25.20", imagenesPantalones, coloresPantalones, tallasPantalones);
        ProductoModa productoZapato=new ProductoModa("Zapato", "Zapato muy bonito", "25.95", imagenesZapatos, coloresZapatos, tallasZapatos);

        ArrayList<Integer> imagenesMesas=new ArrayList<Integer>();
        imagenesMesas.add(R.drawable.mesabeige);
        imagenesMesas.add(R.drawable.mesarojiza);
        ArrayList<String> coloresMesas=new ArrayList<String>();
        coloresMesas.add("Beige");
        coloresMesas.add("Rojiza");
        ProductoMueble productoMueble=new ProductoMueble("Mesa", "Mesita para programar", "10.95", imagenesMesas, coloresMesas);


        listaProductos=new ArrayList<Object>();
        listaProductos.add(productoCamiseta);
        listaProductos.add(productoPantalon);
        listaProductos.add(productoZapato);
        listaProductos.add(productoMueble);
    }

    public ArrayList<Object> getListaProductos() {
        return listaProductos;
    }


    /*
    * Proposito: Busca el la lista de productos el producto con el nombre dado
    * Precondiciones: No hay
    * Entradas: Una cadena
    * Salidas: Un object
    * Postcondiciones: El objeto será el producto de la lista que tenga el nombre dado
    * */
    public Object getProduct(String nombre){
        Object objeto=null;
        ProductoModa productoModa;
        ProductoMueble productoMueble;

        for(int i=0;i<listaProductos.size();i++){
            /*if(listaProductos.get(i) instanceof ProductoModa){
                productoModa=new ProductoModa((ProductoModa) listaProductos.get(i));
                objeto=productoModa;
            }else{
                productoMueble=new ProductoMueble((ProductoMueble)listaProductos.get(i));
                objeto=productoMueble;
            }*/
            if(((Producto)listaProductos.get(i)).getNombre().equals(nombre)){
                objeto=listaProductos.get(i);
            }
        }

        return objeto;
    }


}
