package com.example.icastillo.listahetereogenea;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 30/10/17.
 */

public class ViewHolderAnimal {
    ImageView imagen;
    TextView nombre;
    TextView especie;

    public ViewHolderAnimal(View row, int idImagen, int idNombre, int idEspecie){
        imagen=(ImageView) row.findViewById(idImagen);
        nombre=(TextView) row.findViewById(idNombre);
        especie=(TextView) row.findViewById(idEspecie);
    }

    public ImageView getImagen() {
        return imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getEspecie() {
        return especie;
    }
}
