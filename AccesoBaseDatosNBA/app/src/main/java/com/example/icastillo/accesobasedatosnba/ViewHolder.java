package com.example.icastillo.accesobasedatosnba;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 12/11/2017.
 */

public class ViewHolder {

    private TextView nombre;
    private TextView estadio;
    private TextView id;

    public ViewHolder(View row, int idNombre, int idEstadio, int idIdEquipo){
        nombre=(TextView) row.findViewById(idNombre);
        estadio=(TextView) row.findViewById(idEstadio);
        id=(TextView) row.findViewById(idIdEquipo);
    }

    public TextView getId() {
        return id;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getEstadio() {
        return estadio;
    }
}
