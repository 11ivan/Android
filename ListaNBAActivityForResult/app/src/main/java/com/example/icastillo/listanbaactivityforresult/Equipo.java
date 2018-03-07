package com.example.icastillo.listanbaactivityforresult;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by icastillo on 11/11/2017.
 *
 * Propiedades Básicas:
 *      Nombre: Cadena, Consultable, Modificable
 *      Estadio: Cadena, Consultable, Modificable
 *      IDImagen: Entero, Consultable, Modificable
 *
 * */



public class Equipo implements Parcelable {

    private String nombre;
    private String estadio;
    private Integer idImagen;

    public Equipo(){
        nombre="";
        estadio="";
        idImagen=0;
    }

    public Equipo(String nombre, String estadio, Integer idImagen){
        this.nombre=nombre;
        this.estadio=estadio;
        this.idImagen=idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String toString(){
        return (nombre+","+estadio);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.estadio);
        dest.writeValue(this.idImagen);
    }

    protected Equipo(Parcel in) {
        this.nombre = in.readString();
        this.estadio = in.readString();
        this.idImagen = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Equipo> CREATOR = new Parcelable.Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel source) {
            return new Equipo(source);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };
}
