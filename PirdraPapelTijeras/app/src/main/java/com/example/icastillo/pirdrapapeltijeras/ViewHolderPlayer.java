package com.example.icastillo.pirdrapapeltijeras;

import android.view.View;
import android.widget.TextView;

/**
 * Created by icastillo on 10/12/2017.
 */

public class ViewHolderPlayer {
    private TextView nombre;
    private TextView victorias;
    private TextView derrotas;
    private TextView empates;
    private TextView vecesPiedra;
    private TextView vecesPapel;
    private TextView vecesTijera;

    public ViewHolderPlayer(View row, int idNombre, int idVictorias, int idDerrotas, int idEmpates, int idVecesPiedra, int idVecesPapel, int idVecesTijera){
        nombre=(TextView) row.findViewById(idNombre);
        victorias=(TextView) row.findViewById(idVictorias);
        derrotas=(TextView)row.findViewById(idDerrotas);
        empates=(TextView)row.findViewById(idEmpates);
        vecesPiedra=(TextView)row.findViewById(idVecesPiedra);
        vecesPapel=(TextView)row.findViewById(idVecesPapel);
        vecesTijera=(TextView)row.findViewById(idVecesTijera);
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getVictorias() {
        return victorias;
    }

    public TextView getDerrotas() {
        return derrotas;
    }

    public TextView getEmpates() {
        return empates;
    }

    public TextView getVecesPiedra() {
        return vecesPiedra;
    }

    public TextView getVecesPapel() {
        return vecesPapel;
    }

    public TextView getVecesTijera() {
        return vecesTijera;
    }
}
