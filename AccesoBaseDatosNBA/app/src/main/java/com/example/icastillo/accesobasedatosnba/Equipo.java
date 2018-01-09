package com.example.icastillo.accesobasedatosnba;

import android.arch.persistence.room.*;

import java.util.Date;
//import android.arch.persistence.room.PrimaryKey;

/**
 * Created by icastillo on 9/01/18.
 */

@Entity(tableName = "Equipos")
public class Equipo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int _id;

    @ColumnInfo(name="NombreEquipo")
    private String _nombre;

    @ColumnInfo(name = "Estadio")
    private String _estadio;

    public Equipo(){
        _id=0;
        _nombre="";
        _estadio="";
    }

    public Equipo(String nombre, String estadio){
        this._nombre=nombre;
        this._estadio=estadio;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getEstadio() {
        return _estadio;
    }

    public void setEstadio(String estadio) {
        this._estadio = estadio;
    }



}
