package com.example.icastillo.pirdrapapeltijeras;

/**
 * Created by icastillo on 09/12/2017.


 Propiedades:
    Nombre Jugador: Cadena, Consultable, Modificable
    Victorias: Entero, Consultable, Modificable
    Derrotas: Entero, Consultable, Modificable
    Empates: Entero, Consultable, Modificable
    Veces Piedra: Entero, Consultable, Modificable
    Veces Papel: Entero, Consultable, Modificable
    Veces Tijera: Entero, Consultable, Modificable


 */




public class NombreJugadorConEstadisticas {

    private String nombre;
    private int victorias;
    private int derrotas;
    private int empates;
    private int vecesPiedra;
    private int vecesPapel;
    private int vecesTijera;

    public NombreJugadorConEstadisticas(){
        nombre="";
        victorias=0;
        derrotas=0;
        empates=0;
        vecesPiedra=0;
        vecesPapel=0;
        vecesTijera=0;
    }




}
