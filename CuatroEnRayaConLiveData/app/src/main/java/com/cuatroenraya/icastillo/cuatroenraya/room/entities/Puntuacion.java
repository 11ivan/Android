package com.cuatroenraya.icastillo.cuatroenraya.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.cuatroenraya.icastillo.cuatroenraya.room.converters.Converters;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by icastillo on 07/02/2018.
 */

/*
* Propiedades
*   Resultado: Cadena, Consultable, Modificable, Tomará el valor Victoria, Derrota o Empate
*
*
* */

@Entity(tableName = "Puntuaciones")
public class Puntuacion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ForeignKey(entity = Usuario.class, parentColumns = "ID", childColumns = "IdUsuario")
    @ColumnInfo(name = "IdUsuario")
    private int idUsuario;

    @ColumnInfo(name = "FechaPartida")
    private Date fechaPartida;

    @ColumnInfo(name = "TiempoPartida")
    private String tiempoPartida;

    @ColumnInfo(name="Resultado")
    private String resultado;

    public Puntuacion() {
        this.id = 0;
        this.idUsuario = 0;
        this.fechaPartida = new Date();
        this.tiempoPartida = "";
        this.resultado = "";
    }

    /*public Puntuacion(int id, int idUsuario, Date fechaPartida, Timestamp tiempoPartida, String resultado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaPartida = fechaPartida;
        this.tiempoPartida = tiempoPartida;
        this.resultado = resultado;
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

    public Date getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(Date fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public String getTiempoPartida() {
        return tiempoPartida;
    }

    public void setTiempoPartida(String tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
