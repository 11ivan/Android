package icastillo.examen2trimestre.application.application.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by icastillo on 21/02/18.
 */
@Entity(tableName = "Futbolistas")
public class Futbolista {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "Nombre")
    private String nombre;

    @ColumnInfo(name = "Apellidos")
    private String apellidos;

    @ColumnInfo(name = "IdPosiciones")
    @ForeignKey(entity = Posicion.class, parentColumns = "ID", childColumns = "IdPosiciones")
    private ArrayList<Integer> idPosiciones;

    public Futbolista(){
        id=0;
        nombre="";
        apellidos="";
        idPosiciones=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Integer> getIdPosiciones() {
        return idPosiciones;
    }

    public void setIdPosiciones(ArrayList<Integer> idPosiciones) {
        this.idPosiciones = idPosiciones;
    }
}
