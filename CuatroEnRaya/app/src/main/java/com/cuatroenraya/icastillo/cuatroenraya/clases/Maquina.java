package com.cuatroenraya.icastillo.cuatroenraya.clases;

import java.util.Random;

/**
 * Created by icastillo on 30/01/2018.
 */

public class Maquina {



    public int ponFicha(int[][] arrayParaleloTablero){
        int columna=-1;
        Random aleatorio=new Random();

        //Comprobar si la columna generada está llena
        do {
            columna = aleatorio.nextInt(7);
        }while (columnaLlena(arrayParaleloTablero, columna));

        return columna;
    }

    /*
    * Proposito: Comprueba si una columna del tablero ya está llena
    * Precondiciones: No hay
    * Entradas: Un array de dos dimernsiones que es el array paralelo al tablero, y un entero que es la columna a comprobar
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si la columna está llena y false sino
    * */
    private boolean columnaLlena(int[][] arrayParaleloTablero, int columna){
        boolean llena=false;

        if(arrayParaleloTablero[columna][5]!=0){
            llena=true;
        }
        return llena;
    }

}
