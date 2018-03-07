package icastillo.pruebaexamen2trimestre.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by icastillo on 20/02/18.
 */
@Entity(tableName = "Coches")
public class Coche {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Persona.class, parentColumns = "ID", childColumns = "IdPersona")
    private int idPersona;

    @ColumnInfo(name = "Marca")
    private String marca;

    @ColumnInfo(name = "Modelo")
    private String modelo;

    public Coche(){
        id=0;
        idPersona=0;
        marca="";
        modelo="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
