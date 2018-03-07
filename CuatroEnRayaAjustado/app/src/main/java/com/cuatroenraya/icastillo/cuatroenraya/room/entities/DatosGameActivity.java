package com.cuatroenraya.icastillo.cuatroenraya.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;

/**
 * Created by icastillo on 16/02/2018.
 */
@Entity(tableName = "PartidaGuardada")
public class DatosGameActivity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "ContadoresColumnas")
    private int[] contadoresColumnas;
    @ColumnInfo(name = "ArrayParaleloTablero")
    private int[][] arrayParaleloTablero;// 7 Columnas y 6 Filas
    @ColumnInfo(name = "Turno")
    private int turno;//turno será 0 cuando le toque al jugador y 1 cuando le toque a la máquina
    @ColumnInfo(name = "TotalFichasPuestas")
    private int totalFichasPuestas;
    @ColumnInfo(name = "UltimaFichaPuesta")
    private int[] ultimaFichaPuesta;
    @ColumnInfo(name = "HayGanador")
    private boolean hayGanador;
    @ColumnInfo(name = "HaEmpezado")
    private boolean haEmpezado;
    @ColumnInfo(name = "TiempoPartida")
    private String tiempoPartida;
    @ColumnInfo(name = "Rival")
    private int rival;//1 si es la máquina y 2 si es un invitado

    public DatosGameActivity(){
         id=0;
         contadoresColumnas=new int[7];
         arrayParaleloTablero=new int[7][6];// 7 Columnas y 6 Filas
         turno=0;//turno será 0 cuando le toque al jugador y 1 cuando le toque a la máquina
         totalFichasPuestas=0;
         ultimaFichaPuesta=new int[2];
         hayGanador=false;
         haEmpezado=false;
         tiempoPartida="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getContadoresColumnas() {
        return contadoresColumnas;
    }

    public void setContadoresColumnas(int[] contadores) {
        this.contadoresColumnas = contadores;
    }

    public void incrementarContadorColumna(int columna){
        contadoresColumnas[columna]=contadoresColumnas[columna]+1;
    }

    public int[][] getArrayParaleloTablero() {
        return arrayParaleloTablero;
    }

    public void setArrayParaleloTablero(int[][] arrayParaleloTablero) {
        this.arrayParaleloTablero = arrayParaleloTablero;
    }
    public void setArrayParaleloTablero(int column, int row, int value) {
        this.arrayParaleloTablero[column][row] = value;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getTotalFichasPuestas() {
        return totalFichasPuestas;
    }

    public void setTotalFichasPuestas(int totalFichasPuestas) {
        this.totalFichasPuestas = totalFichasPuestas;
    }

    public int[] getUltimaFichaPuesta() {
        return ultimaFichaPuesta;
    }

    public void setUltimaFichaPuesta(int[] ultimaFichaPuesta) {
        this.ultimaFichaPuesta = ultimaFichaPuesta;
    }

    public void setUltimaFichaPuesta(int indice, int valor) {
        this.ultimaFichaPuesta[indice] = valor;
    }

    public boolean isHayGanador() {
        return hayGanador;
    }

    public void setHayGanador(boolean hayGanador) {
        this.hayGanador = hayGanador;
    }

    public boolean isHaEmpezado() {
        return haEmpezado;
    }

    public void setHaEmpezado(boolean haEmpezado) {
        this.haEmpezado = haEmpezado;
    }

    public String getTiempoPartida() {
        return tiempoPartida;
    }

    public void setTiempoPartida(String tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }

    public int getRival() {
        return rival;
    }

    public void setRival(int rival) {
        this.rival = rival;
    }
}
