package com.example.icastillo.spinner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icastillo on 02/11/2017.

 Propiedades Agregadas:
    ImageView: Consultable, Modificable
    TextView: Consultable, Modificable

 */



public class ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public ViewHolder(View row, int idImagen, int idTexto){
        imageView=(ImageView) row.findViewById(idImagen);
        textView=(TextView) row.findViewById(idTexto);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
