package com.example.icastillo.examenpasado;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.Permission;

/**
 * Created by icastillo on 21/11/2017.
 */

/*
* Propiedades Básicas:
*       Nombre: Cadena, consultable, modificable
*       Apellido: Cadena, consultable, modificable
*       Peso: Cadena, consultable, modificable
*       Altura: Cadena, consultable, modificable
*       Posición: Cadena, consultable, modificable
*       ID Imagen: Entero, consultable, modificable
*
*
* */


public class Jugador implements Parcelable {

    //Propiedades
    private String nombre;
    private String apellido;
    private String peso;
    private String altura;
    private String posicion;
    private int idImagen;


    //Constructores
    public Jugador(){
        nombre="";
        apellido="";
        peso="";
        altura="";
        posicion="";
        idImagen=0;
    }

    public Jugador(String nombre, String apellido, String peso, String altura, String posicion, int idImagen){
        this.nombre=nombre;
        this.apellido=apellido;
        this.peso=peso;
        this.altura=altura;
        this.posicion=posicion;
        this.idImagen=idImagen;
    }

    public Jugador(Jugador jugador){
        this.nombre=jugador.getNombre();
        this.apellido=jugador.getApellido();
        this.peso=jugador.getPeso();
        this.altura=jugador.getAltura();
        this.posicion=jugador.getPosicion();
        this.idImagen=jugador.getIdImagen();
    }


    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.peso);
        dest.writeString(this.altura);
        dest.writeInt(this.idImagen);
    }

    protected Jugador(Parcel in) {
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.peso = in.readString();
        this.altura = in.readString();
        this.idImagen = in.readInt();
    }

    public static final Parcelable.Creator<Jugador> CREATOR = new Parcelable.Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel source) {
            return new Jugador(source);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}
