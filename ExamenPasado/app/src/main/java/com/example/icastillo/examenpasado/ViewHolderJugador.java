package com.example.icastillo.examenpasado;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 21/11/2017.
 */

/*
* Propiedades Agregadas:
*   imagen: ImageView, Consultable, Modificable
*   nombre: TextView, Consultable, Modificable
*   posición: TextView, Consultable, Modificable
*   altura: TextView, Consultable, Modificable
*   peso: TextView, Consultable, Modificable
*
* */

public class ViewHolderJugador {

    private ImageView imagen;
    private TextView nombre;
    private TextView posicion;
    private TextView altura;
    private TextView peso;



    public ViewHolderJugador(View row, int idImagen, int idNombre, int idPosicion, int idAltura, int idPeso){
        imagen=(ImageView) row.findViewById(idImagen);
        nombre=(TextView) row.findViewById(idNombre);
        posicion=(TextView) row.findViewById(idPosicion);
        altura=(TextView) row.findViewById(idAltura);
        peso=(TextView) row.findViewById(idPeso);

    }

    public ImageView getImagen() {
        return imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getPosicion() {
        return posicion;
    }

    public TextView getAltura() {
        return altura;
    }

    public TextView getPeso() {
        return peso;
    }


}
