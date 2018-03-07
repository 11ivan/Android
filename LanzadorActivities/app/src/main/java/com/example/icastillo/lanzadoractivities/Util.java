package com.example.icastillo.lanzadoractivities;

/**
 * Created by icastillo on 05/10/2017.
 */

public class Util {


    /*
    * Proposito: Suma dos números
    * Prototipo: Double suma(Double n1, Double n2)
    * Precondiciones: No hay
    * Entradas: Dos Doubles
    * Salidas: Un Double
    * Postcondiciones:
    * */
    public Double suma(Double n1, Double n2){
        Double resultado;
        return resultado=n1+n2;
    }

//-------------------------------------------------------------------------------------
    /*
    * Proposito: Resta dos números
    * Prototipo: Double resta(Double n1, Double n2)
    * Precondiciones: No hay
    * Entradas: Dos Doubles
    * Salidas: Un Double
    * Postcondiciones:
    * */
    public Double resta(Double n1, Double n2){
        Double resultado;
        return resultado=n1-n2;
}

//-------------------------------------------------------------------------------------
    /*
    * Proposito: Multiplica dos números
    * Prototipo: Double multiplica(Double n1, Double n2)
    * Precondiciones: No hay
    * Entradas: Dos Doubles
    * Salidas: Un Double
    * Postcondiciones:
    * */
public Double multiplica(Double n1, Double n2){
    Double resultado;
    return resultado=n1*n2;
}

//-------------------------------------------------------------------------------------
    /*
    * Proposito: Divide dos números
    * Prototipo: Double divide(Double n1, Double n2)
    * Precondiciones: No hay
    * Entradas: Dos Doubles
    * Salidas: Un Double
    * Postcondiciones:
    * */
    public Double divide(Double n1, Double n2){
        Double resultado=-1.0;
        if(n2>0) {
            resultado = n1 / n2;
        }
        return resultado;
    }

//-------------------------------------------------------------------------------------


}
