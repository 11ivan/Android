package com.example.icastillo.pirdrapapeltijeras;

import java.io.Serializable;

/**
 * Created by icastillo on 04/12/2017.
 */

/*
* Propiedades básicas:
*
*       Nombre: Cadena, Consultable, Modificable
*       Password: Cadena, Consultable, Modificable
        Victorias: Entero, Consultable, Modificable
        Derrotas: Entero, Consultable, Modificable
        Empates: Entero, Consultable, Modificable
        Veces Piedra: Entero, Consultable, Modificable
        Veces Papel: Entero, Consultable, Modificable
        Veces Tijera: Entero, Consultable, Modificable

*
*
* */

public class Player implements Serializable{

    private String nombre;
    private String password;
    private int victorias;
    private int derrotas;
    private int empates;
    private int vecesPiedra;
    private int vecesPapel;
    private int vecesTijera;


    public Player(){
        nombre="";
        password="";
        victorias=0;
        derrotas=0;
        empates=0;
        vecesPiedra=0;
        vecesPapel=0;
        vecesTijera=0;
    }

    public Player(String nombre, String password, int victorias, int derrotas, int empates, int vecesPiedra, int vecesPapel, int vecesTijera){
        this.nombre=nombre;
        this.password=password;
        this.victorias=victorias;
        this.derrotas=derrotas;
        this.empates=empates;
        this.vecesPiedra=vecesPiedra;
        this.vecesPapel=vecesPapel;
        this.vecesTijera=vecesTijera;
    }

    public Player(Player player){
        this.nombre=player.getNombre();
        this.password=player.getPassword();
        this.victorias=player.getVictorias();
        this.derrotas=player.getDerrotas();
        this.empates=player.getEmpates();
        this.vecesPiedra=player.getVecesPiedra();
        this.vecesPapel=player.getVecesPapel();
        this.vecesTijera=player.getVecesTijera();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getVecesPiedra() {
        return vecesPiedra;
    }

    public int getVecesPapel() {
        return vecesPapel;
    }

    public int getVecesTijera() {
        return vecesTijera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public void setVecesPiedra(int vecesPiedra) {
        this.vecesPiedra = vecesPiedra;
    }

    public void setVecesPapel(int vecesPapel) {
        this.vecesPapel = vecesPapel;
    }

    public void setVecesTijera(int vecesTijera) {
        this.vecesTijera = vecesTijera;
    }

    public String toString(){
        String cadena=nombre+","+password;
        return cadena;
    }

    //Dos Player son iguales si tienen el mismo nombre y el mismo password
    @Override
    public boolean equals(Object obj){
        boolean iguales=false;
        Player playerComp=null;

        if(obj instanceof Player){
            playerComp=(Player) obj;
            if(playerComp.getNombre().equals(this.nombre) && playerComp.getPassword().equals(this.password)){
                iguales=true;
            }
        }
        return iguales;
    }


}
