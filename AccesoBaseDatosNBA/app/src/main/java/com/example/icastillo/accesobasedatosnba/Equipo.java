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


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_estadio() {
        return _estadio;
    }

    public void set_estadio(String _estadio) {
        this._estadio = _estadio;
    }
}
