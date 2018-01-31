package com.cuatroenraya.icastillo.cuatroenraya;

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

    private boolean columnaLlena(int[][] arrayParaleloTablero, int columna){
        boolean llena=false;

        if(arrayParaleloTablero[columna][5]!=0){
            llena=true;
        }
        return llena;
    }


}
