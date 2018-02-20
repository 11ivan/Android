package icastillo.pruebaexamen2trimestre.viewholders;

import android.view.View;
import android.widget.TextView;

import icastillo.pruebaexamen2trimestre.R;

/**
 * Created by icastillo on 20/02/2018.
 */

public class ViewHolderPersona {

    private TextView textViewNombre;
    private TextView textViewApellidos;

    public ViewHolderPersona(View view){
        textViewNombre=(TextView) view.findViewById(R.id.nombrePersona);
        textViewApellidos=(TextView) view.findViewById(R.id.apellidosPersona);
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
}
