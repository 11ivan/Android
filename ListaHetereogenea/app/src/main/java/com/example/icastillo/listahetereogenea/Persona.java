package com.example.icastillo.listahetereogenea;

import java.util.GregorianCalendar;

/**
 * Created by icastillo on 30/10/17.
 */

public class Persona {
    private String nombre;
    private String apellido;
    private GregorianCalendar fechaNac;
    private int idFoto;



    public Persona(){
        nombre=" ";
        apellido=" ";
        fechaNac=new GregorianCalendar();
        idFoto=0;
    }

    public Persona(String nombre, String apellido, GregorianCalendar fechaNac, int idFoto){
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaNac= (GregorianCalendar) fechaNac.clone();
        this.idFoto=idFoto;
    }


    //Consultores
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public int getIdFoto() {
        return idFoto;
    }


    //Modificadores
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNac(GregorianCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }









}
