package com.cuatroenraya.icastillo.cuatroenraya.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;
import android.support.annotation.ColorInt;

import com.cuatroenraya.icastillo.cuatroenraya.R;

/**
 * Created by icastillo on 07/02/2018.
 */

@Entity(tableName = "Configuraciones")
public class Configuracion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "IdUsuario")
    @ForeignKey(entity = Usuario.class, parentColumns = "ID", childColumns = "IdUsuario")
    private int idUsuario;

    /*@ColumnInfo(name = "Dificultad")
    private String dificultad;*/

    @ColumnInfo(name = "TipoTablero")
    private Integer tipoTablero;

    public Configuracion() {
        this.id = 0;
        this.idUsuario = 0;
        //this.dificultad = "";
        this.tipoTablero = R.drawable.tablero4enraya;
    }

    /*public Configuracion(int id, int idUsuario, String dificultad, int tipoTablero) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.dificultad = dificultad;
        this.tipoTablero = tipoTablero;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /*public String getDificultad() {
        return dificultad;
    }
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }*/

    public Integer getTipoTablero() {
        return tipoTablero;
    }

    public void setTipoTablero(Integer tipoTablero) {
        this.tipoTablero = tipoTablero;
    }
}
