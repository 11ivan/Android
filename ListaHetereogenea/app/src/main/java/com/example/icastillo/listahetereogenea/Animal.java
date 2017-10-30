package com.example.icastillo.listahetereogenea;

/**
 * Created by icastillo on 30/10/17.
 */

public class Animal {
    private String especie;
    private String nombre;
    private int idFoto;


    public Animal(){
        especie=" ";
        nombre=" ";
        idFoto=0;
    }

    public Animal(String especie, String nombre, int idFoto){
        this.especie=especie;
        this.nombre=nombre;
        this.idFoto=idFoto;
    }


    //Consultores
    public String getEspecie() {
        return especie;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdFoto() {
        return idFoto;
    }

    //Modificadores
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
