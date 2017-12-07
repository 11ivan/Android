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
*
*
*
* */

public class Player implements Serializable{

    private String nombre;
    private String password;

    public Player(){
        nombre="";
        password="";
    }

    public Player(String nombre, String password){
        this.nombre=nombre;
        this.password=password;
    }

    public Player(Player player){
        this.nombre=player.getNombre();
        this.password=player.getPassword();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
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
