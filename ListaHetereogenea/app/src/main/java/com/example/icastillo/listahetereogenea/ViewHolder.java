package com.example.icastillo.listahetereogenea;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by icastillo on 30/10/17.
 */

public class ViewHolder {
    ImageView imagen;
    TextView nombre;
    TextView apellido;
    TextView fechaNac;

    public ViewHolder(View row, int idImagen, int idNombre, int idApellido, int idFechaNac){
        imagen=(ImageView) row.findViewById(idImagen);
        nombre=(TextView) row.findViewById(idNombre);
        apellido=(TextView) row.findViewById(idApellido);
        fechaNac=(TextView) row.findViewById(idFechaNac);
    }

    public ImageView getImagen() {
        return imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getApellido() {
        return apellido;
    }

    public TextView getFechaNac() {
        return fechaNac;
    }
}
