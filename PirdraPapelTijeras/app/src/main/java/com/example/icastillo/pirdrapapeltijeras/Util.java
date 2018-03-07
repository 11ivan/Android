package com.example.icastillo.pirdrapapeltijeras;

/**
 * Created by icastillo on 06/12/2017.
 */

public class Util {

    /*
    * Proposito: Comprueba que una cadena no esté vacía
    * Precondiciones: No hay
    * Entradas: Una cadena
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si la cadena no está vacía y false si sí lo está
    */
    public boolean compruebaCadena(String cadena){
        boolean vale=false;

        if(cadena!=null && cadena.replaceAll(" ", "").length()>0) {
            vale = true;
        }
        return vale;
    }





}
