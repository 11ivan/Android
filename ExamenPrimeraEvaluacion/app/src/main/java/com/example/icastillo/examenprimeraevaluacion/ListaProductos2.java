package com.example.icastillo.examenprimeraevaluacion;

import java.util.ArrayList;

/**
 * Created by icastillo on 22/11/17.
 */

public class ListaProductos2 {

    private final ArrayList<Object> listaProductos;

    public ListaProductos2(){
        ProductoModa2 camiseta1=new ProductoModa2("Camiseta", "Camisetita Rojita", "12.95", R.drawable.camisetaroja, "Moda", "Rojo", "L");
        ProductoModa2 camiseta2=new ProductoModa2("Camiseta", "Camisetita Negra", "15.95", R.drawable.camisetanegra, "Moda", "Negro", "S");
        ProductoModa2 camiseta3=new ProductoModa2("Camiseta", "Camisetita Marron", "18.95", R.drawable.camisetamarron, "Moda", "Marron", "XL");
        ProductoModa2 pantalon1=new ProductoModa2("Pantalon", "Pantalon Beige", "12.95", R.drawable.pantalonbeige, "Moda", "Beige", "42");
        ProductoModa2 pantalon2=new ProductoModa2("Pantalon", "Pantalon Caqui", "14.95", R.drawable.pantaloncaqui, "Moda", "Caqui", "41");
        ProductoModa2 pantalon3=new ProductoModa2("Pantalon", "Pantalon Negro", "11.95", R.drawable.pantalonnegro, "Moda", "Negro", "40");
        ProductoModa2 zapato1=new ProductoModa2("Zapato", "Zapato Gris", "15.95", R.drawable.zapatogris, "Moda", "Gris", "40");
        ProductoModa2 zapato2=new ProductoModa2("Zapato", "Zapato Marron", "20.95", R.drawable.zapatomarron, "Moda", "Marron", "42");
        ProductoModa2 zapato3=new ProductoModa2("Zapato", "Zapato Negro", "23.95", R.drawable.zapatonegro, "Moda", "Negro", "41");

        ProductoMueble2 mesita=new ProductoMueble2("Mesa", "Mesa Beige", "50.95", R.drawable.mesabeige, "Muebles", "Beige");
        ProductoMueble2 mesit2=new ProductoMueble2("Mesa", "Mesa Roja", "89.95", R.drawable.mesarojiza, "Muebles", "Rojo");


        listaProductos=new ArrayList<Object>();
        listaProductos.add(camiseta1);
        listaProductos.add(camiseta2);
        listaProductos.add(camiseta3);
        listaProductos.add(pantalon1);
        listaProductos.add(pantalon2);
        listaProductos.add(pantalon3);
        listaProductos.add(zapato1);
        listaProductos.add(zapato2);
        listaProductos.add(zapato3);
        listaProductos.add(mesita);
        listaProductos.add(mesit2);
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
        Boolean vale=false;

        for(int i=0;i<listaProductos.size() && !vale;i++){
            /*if(listaProductos.get(i) instanceof ProductoModa){
                productoModa=new ProductoModa((ProductoModa) listaProductos.get(i));
                objeto=productoModa;
            }else{
                productoMueble=new ProductoMueble((ProductoMueble)listaProductos.get(i));
                objeto=productoMueble;
            }*/
            if(((Producto2)listaProductos.get(i)).getNombre().equals(nombre)){
                objeto=listaProductos.get(i);
                vale=true;
            }
        }

        return objeto;
    }

    /*
    * Proposito: Busca el la lista de productos el producto con el color dado
    * Precondiciones: No hay
    * Entradas: Una cadena
    * Salidas: Un entero
    * Postcondiciones: El entero será el id de la imagen del elemento en lista que tenga el color dado
    * */
    public int getProductIdImage(String color, String nombre){
        ProductoModa productoModa;
        ProductoMueble productoMueble;
        Boolean vale=false;
        int idImagen=0;

        for(int i=0;i<listaProductos.size() && !vale;i++){
            if(((Producto2)listaProductos.get(i)).getColor().equals(color) && ((Producto2)listaProductos.get(i)).getNombre().equals(nombre)){
                idImagen=((Producto2)listaProductos.get(i)).getIdImagen();
                vale=true;
            }
        }

        return idImagen;
    }


    /*
    * Proposito: Devuelve aun arrayList con los nombres de los productos
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: Un arraylist de cadenas
    * Postcondiciones: El arrayList contiene los nombres de todos los productos
    * */
    public ArrayList<String> getNombresProductos(){
        ArrayList<String> listaNombres=new ArrayList<String>();
        for (int i=0;i<listaProductos.size();i++){
            listaNombres.add(((Producto2)listaProductos.get(i)).getNombre());
        }
        return listaNombres;
    }

    /*
    * Proposito: Recibe un nombre de producto y devuelve un arrayList con los colores disponibles de ese producto
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: Un arraylist de cadenas
    * Postcondiciones: El arrayList contiene los nombres de todos los productos
    * */
    public ArrayList<String> getColoresProducto(String nombreProducto){
        ArrayList<String> listaColores=new ArrayList<String>();
        for (int i=0;i<listaProductos.size();i++){
            if(((Producto2)listaProductos.get(i)).getNombre().equals(nombreProducto)){
                listaColores.add(((Producto2)listaProductos.get(i)).getColor());
            }
        }
        return listaColores;
    }

    /*
    * Proposito: Recibe un nombre de producto y devuelve un arrayList con los colores disponibles de ese producto
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: Un arraylist de cadenas
    * Postcondiciones: El arrayList contiene los nombres de todos los productos
    * */
    public ArrayList<String> getTallasProducto(String nombreProducto){
        ArrayList<String> listaTallas=new ArrayList<String>();
        for (int i=0;i<listaProductos.size();i++){
            if(((Producto2)listaProductos.get(i)).getNombre().equals(nombreProducto)){
                listaTallas.add(((ProductoModa2)listaProductos.get(i)).getTalla());
            }
        }
        return listaTallas;
    }



}
