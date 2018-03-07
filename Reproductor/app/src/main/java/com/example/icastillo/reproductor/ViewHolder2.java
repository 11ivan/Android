package com.example.icastillo.reproductor;

import android.view.View;
import android.widget.TextView;

/**
 * Created by icastillo on 25/10/2017.
 */

public class ViewHolder2 {

    private TextView nombrePista;
    private TextView duracionPista;

    public ViewHolder2(View row, int idtxtNombre, int idtxtDuracion){
        nombrePista=(TextView) row.findViewById(idtxtNombre);
        duracionPista=(TextView) row.findViewById(idtxtDuracion);
    }


    public TextView getNombrePista(){
        return nombrePista;
    }

    public TextView getDuracionPista(){
        return duracionPista;
    }


}
