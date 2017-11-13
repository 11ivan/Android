package com.example.icastillo.listanbaactivityforresult;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 12/11/2017.
 */

public class ViewHolder {
    private ImageView image;
    private TextView nombre;
    private TextView estadio;

    public ViewHolder(View row, int idImage, int idNombre, int idEstadio){
        image=(ImageView) row.findViewById(idImage);
        nombre=(TextView) row.findViewById(idNombre);
        estadio=(TextView) row.findViewById(idEstadio);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getEstadio() {
        return estadio;
    }
}
