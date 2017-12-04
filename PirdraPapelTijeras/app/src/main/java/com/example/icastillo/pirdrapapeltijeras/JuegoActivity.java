package com.example.icastillo.pirdrapapeltijeras;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnPiedra;
    ImageButton btnPapel;
    ImageButton btnTijeras;
    ImageView imagenMiEleccion;
    ImageView imagenEleccionMaquina;
    boolean haPulsado=false;
    Integer[] arrayImagenes={R.drawable.piedra, R.drawable.ocb, R.drawable.tijeras};
    int posLevantadaMaquina;
    int posLevantadaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        btnPiedra=(ImageButton) findViewById(R.id.btnPiedra);
        btnPapel=(ImageButton)findViewById(R.id.btnPapel);
        btnTijeras=(ImageButton)findViewById(R.id.btnTijeras);
        imagenMiEleccion=(ImageView) findViewById(R.id.miEleccion);
        imagenEleccionMaquina=(ImageView) findViewById(R.id.eleccionMaquina);

        btnPiedra.setOnClickListener(this);
        btnPapel.setOnClickListener(this);
        btnTijeras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        haPulsado=!haPulsado;
        if(!haPulsado) {
            switch (v.getId()) {

                case R.id.btnPiedra:
                    imagenMiEleccion.setImageResource(arrayImagenes[0]);
                    posLevantadaUsuario=0;
                    break;

                case R.id.btnPapel:
                    imagenMiEleccion.setImageResource(arrayImagenes[1]);
                    posLevantadaUsuario=1;
                    break;

                case R.id.btnTijeras:
                    imagenMiEleccion.setImageResource(arrayImagenes[2]);
                    posLevantadaUsuario=2;
                    break;
            }
        }
    }

    /*
    * Proposito: Genera una imagen al azar entre piedra, papel o tijeras
    * Precondiciones: El Usuario ha pulsado algún botón
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha cambiado la imagen por la generada de forma aleatoria
    * */
    public void generaImagen(){
        Random aleatorio=new Random();
        int generado=aleatorio.nextInt(3);
        switch (generado){
            case 0:
                    imagenEleccionMaquina.setImageResource(arrayImagenes[0]);
                    posLevantadaMaquina=0;
                break;

            case 1:
                    imagenEleccionMaquina.setImageResource(arrayImagenes[1]);
                    posLevantadaMaquina=1;
                break;

            case 2:
                    imagenEleccionMaquina.setImageResource(arrayImagenes[2]);
                    posLevantadaMaquina=2;
                break;
        }
    }

/*
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImagescartasLevantadas[0].setImageResource(R.drawable.reverso);
                ImagescartasLevantadas[1].setImageResource(R.drawable.reverso);
                desmarcaCartasLevantada(posicionLevantadas);
            }
        },1500);
*/


    /*
    * Proposito: Comprueba el ganador
    * Precondiciones: Hay dos imagenes generadas para comparar
    * Entradas: No hay
    * Salidas: Un entero
    * Postcondiciones: El entero será 1 si gana el Usuario, 0 si empatan y -1 si gana la maquina
    * */
    public int compruebaGanador(){
        int ganador=0;

        if(posLevantadaMaquina){

        }

        return ganador;
    }



}
