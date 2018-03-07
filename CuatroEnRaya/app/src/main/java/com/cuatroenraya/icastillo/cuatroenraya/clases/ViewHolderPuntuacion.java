package com.cuatroenraya.icastillo.cuatroenraya.clases;

import android.view.View;
import android.widget.TextView;

import com.cuatroenraya.icastillo.cuatroenraya.R;

import java.util.concurrent.TimeoutException;

/**
 * Created by icastillo on 17/02/2018.
 */

public class ViewHolderPuntuacion {

    private TextView textViewResultado;
    private TextView textViewFechaPertida;
    private TextView textViewTiempoPartida;

    public ViewHolderPuntuacion(View view){
        textViewResultado=(TextView) view.findViewById(R.id.txtResultado);
        textViewFechaPertida=(TextView) view.findViewById(R.id.txtFechaPartida);
        textViewTiempoPartida=(TextView) view.findViewById(R.id.txtTiempoPartida);
    }

    public TextView getTextViewResultado() {
        return textViewResultado;
    }

    public void setTextViewResultado(TextView textViewResultado) {
        this.textViewResultado = textViewResultado;
    }

    public TextView getTextViewFechaPertida() {
        return textViewFechaPertida;
    }

    public void setTextViewFechaPertida(TextView textViewFechaPertida) {
        this.textViewFechaPertida = textViewFechaPertida;
    }

    public TextView getTextViewTiempoPartida() {
        return textViewTiempoPartida;
    }

    public void setTextViewTiempoPartida(TextView textViewTiempoPartida) {
        this.textViewTiempoPartida = textViewTiempoPartida;
    }
}
