package icastillo.examen2trimestre.application.application;

import android.view.View;
import android.widget.TextView;

import icastillo.examen2trimestre.R;

/**
 * Created by icastillo on 21/02/18.
 */

public class ViewHolderFubolista {

    private TextView textViewNombre;
    private TextView textViewApellidos;
    private TextView textViewPosiciones;

    public ViewHolderFubolista(View view){
        textViewNombre=(TextView) view.findViewById(R.id.nombrePersona);
        textViewApellidos=(TextView) view.findViewById(R.id.apellidosPersona);
        textViewPosiciones=(TextView) view.findViewById(R.id.posiciones);
    }

    public TextView getTextViewNombre() {
        return textViewNombre;
    }

    public void setTextViewNombre(TextView textViewNombre) {
        this.textViewNombre = textViewNombre;
    }

    public TextView getTextViewApellidos() {
        return textViewApellidos;
    }

    public void setTextViewApellidos(TextView textViewApellidos) {
        this.textViewApellidos = textViewApellidos;
    }

    public TextView getTextViewPosiciones() {
        return textViewPosiciones;
    }

    public void setTextViewPosiciones(TextView textViewPosiciones) {
        this.textViewPosiciones = textViewPosiciones;
    }
}
