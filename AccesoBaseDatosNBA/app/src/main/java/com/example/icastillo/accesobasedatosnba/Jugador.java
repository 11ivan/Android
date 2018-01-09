package com.example.icastillo.accesobasedatosnba;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by icastillo on 9/01/18.
 */

@Entity(tableName = "Jugadores")
public class Jugador {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    private int _id;

    @ColumnInfo(name = "Nombre")
    private String _nombre;

    @ColumnInfo(name = "FechaNacimiento")
    private Date _fechaNac;

    @ForeignKey(entity = Equipo.class, parentColumns = "ID", childColumns = "IdEquipo")
    @ColumnInfo(name = "IdEquipo")
    private int _idEquipo;

    public Jugador(){
        _id=0;
        _nombre="";
        _fechaNac=new Date();
        _idEquipo=0;
    }

    public int getID() {
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

    public int getIdEquipo() {
        return _idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this._idEquipo = idEquipo;
    }

    public Date getFechaNac() {
        return _fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this._fechaNac = fechaNac;
    }

}
