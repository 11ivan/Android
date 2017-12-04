package com.example.icastillo.pirdrapapeltijeras;

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

public class Player {

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
}
