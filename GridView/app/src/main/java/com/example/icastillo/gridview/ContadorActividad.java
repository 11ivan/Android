package com.example.icastillo.gridview;

/**
 * Created by icastillo on 08/11/2017.
 */

public class ContadorActividad {
    private static int contador=0;

    public ContadorActividad(){
        contador++;
    }

    public int getContador(){
        return contador;
    }

}
