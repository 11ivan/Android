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

    public Date get_fechaNac() {
        return _fechaNac;
    }

    public void set_fechaNac(Date _fechaNac) {
        this._fechaNac = _fechaNac;
    }

    public int get_idEquipo() {
        return _idEquipo;
    }

    public void set_idEquipo(int _idEquipo) {
        this._idEquipo = _idEquipo;
    }
}
