package com.example.icastillo.examenpasado;

import android.content.Intent;

/**
 * Created by icastillo on 21/11/2017.
 */

public class GestoraAddEditJugador {



    public String[] fillArrayPeso(){
        String[] arrayPeso=new String[60];
        int pesoBase=60;

        for(int i=0;i<arrayPeso.length;i++){
            arrayPeso[i]=String.valueOf(pesoBase+i);
        }

        return arrayPeso;
    }

    public Integer[] fillArrayAltura(){
        Integer[] arrayAltura=new Integer[60];
        int acumulador=150;

        for(int i=0;i<arrayAltura.length;i++){
            acumulador=acumulador+1;
            //acumulador=(double)Math.round(acumulador)/10;
            arrayAltura[i]=acumulador;
        }

        return arrayAltura;
    }


}
