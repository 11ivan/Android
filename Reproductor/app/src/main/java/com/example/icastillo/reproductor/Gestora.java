package com.example.icastillo.reproductor;

import java.util.ArrayList;

/**
 * Created by icastillo on 26/10/2017.
 */

public class Gestora {


    public Pista[] toArray(ArrayList<Pista> arrayListPista){
        Pista[] arrayPista= new Pista[arrayListPista.size()];

        for(int i=0;i<arrayListPista.size();i++){
            arrayPista[i]=arrayListPista.get(i);
        }

        return arrayPista;
    }

}
