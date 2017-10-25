package com.example.icastillo.reproductor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 24/10/17.
 */

public class ViewHolder {

    private ImageView image;
    private TextView textNombre;
    private TextView textArtista;
    private TextView textFecha;


    public ViewHolder(View row, int idImage, int idNombre, int idArtista, int idFecha){
        image=(ImageView) row.findViewById(idImage);
        textNombre=(TextView) row.findViewById(idNombre);
        textArtista=(TextView) row.findViewById(idArtista);
        textFecha=(TextView) row.findViewById(idFecha);
    }


    //Consultores
    public ImageView getImage(){
        return image;
    }

    public TextView getTextNombre(){
        return textNombre;
    }

    public TextView getTextArtista(){
        return  textArtista;
    }

    public TextView getTextFecha() {
        return textFecha;
    }
}
