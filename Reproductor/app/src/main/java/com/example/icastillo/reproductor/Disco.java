package com.example.icastillo.reproductor;

import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by icastillo on 17/10/17.
 */

/*
   Propiedades Basicas:
        Nombre:
        Artista:
        Imagen:

   Propiedades Derivadas:


   Propiedades Agregadas:
        GregorianCalendar
        ArrayList<MediaPlayer>


 */

public class Disco implements Parcelable {
    private String nombre;
    private String artista;
    private GregorianCalendar fecha;
    private int imagen;
    private ArrayList<Pista> pistas;

    //Constructores
    public Disco() {
        nombre = " ";
        artista = " ";
        fecha = new GregorianCalendar();
        imagen = 0;
    }

    public Disco(String nombre, String artista, GregorianCalendar fecha, int imagen) {
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = (GregorianCalendar) fecha.clone();
        this.imagen = imagen;
        pistas=new ArrayList<Pista>();
    }


    //Consultores
    public String getNombre() {
        return this.nombre;
    }

    public String getArtista() {
        return this.artista;
    }


    public GregorianCalendar getFecha() {
        return this.fecha;
    }

    public int getImagen() {
        return this.imagen;
    }

    public Pista getAudio(int pos){
        return pistas.get(pos);
    }

    //Modificadores
    public void setNombre(String nombre) {
        if (nombre != null && nombre.replaceAll(" ", "").length() > 0) {
            this.nombre = nombre;
        }
    }

    public void setArtista(String artista) {
        if (artista != null && artista.replaceAll(" ", "").length() > 0) {
            this.artista = artista;
        }
    }

    public void setFecha(GregorianCalendar fecha) {
        //if(fecha!=null && fecha.before(GregorianCalendar.getInstance())){
            this.fecha = fecha;
        //}
    }

    public void setImagen(int imagen) {
        //if (imagen != null && imagen.replaceAll(" ", "").length() > 0) {
            this.imagen = imagen;
        //}
    }


    public void addPista(Pista pista){
        if(!pistas.contains(pista)){
            pistas.add(pista);
        }

    }

    public void dropPista(int pos){
        if(pos<=pistas.size()-1 && pos>=0){
            pistas.remove(pos);
        }
    }

    public ArrayList<Pista> getPistas(){
        return pistas;
    }

    public String toString(){
        String cadena;
            cadena=this.nombre+", "+this.artista+", "+fecha.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        return cadena;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.artista);
        dest.writeSerializable(this.fecha);
        dest.writeInt(this.imagen);
        dest.writeList(this.pistas);
    }

    protected Disco(Parcel in) {
        this.nombre = in.readString();
        this.artista = in.readString();
        this.fecha = (GregorianCalendar) in.readSerializable();
        this.imagen = in.readInt();
        this.pistas = new ArrayList<Pista>();
        in.readList(this.pistas, Pista.class.getClassLoader());
    }

    public static final Parcelable.Creator<Disco> CREATOR = new Parcelable.Creator<Disco>() {
        @Override
        public Disco createFromParcel(Parcel source) {
            return new Disco(source);
        }

        @Override
        public Disco[] newArray(int size) {
            return new Disco[size];
        }
    };
}