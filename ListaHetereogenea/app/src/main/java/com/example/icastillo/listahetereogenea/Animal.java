package com.example.icastillo.listahetereogenea;

/**
 * Created by icastillo on 30/10/17.
 */

public class Animal {
    private String nombreCient;
    private String nombre;
    private int idFoto;


    public Animal(){
        nombreCient=" ";
        nombre=" ";
        idFoto=0;
    }

    public Animal(String nombreCient, String nombre, int idFoto){
        this.nombreCient=nombreCient;
        this.nombre=nombre;
        this.idFoto=idFoto;
    }

    public Animal(Animal animal){
        this.nombreCient=animal.getNombreCient();
        this.nombre=animal.getNombre();
        this.idFoto=animal.getIdFoto();
    }


    //Consultores
    public String getNombreCient() {
        return nombreCient;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdFoto() {
        return idFoto;
    }

    //Modificadores
    public void setNombreCient(String nombreCient) {
        this.nombreCient = nombreCient;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
