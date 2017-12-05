package com.example.icastillo.pirdrapapeltijeras;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
        if(!haPulsado) {
            haPulsado=true;
            switch (v.getId()) {
                case R.id.btnPiedra:
                    posLevantadaUsuario = 0;
                    break;

                case R.id.btnPapel:
                    posLevantadaUsuario = 1;
                    break;

                case R.id.btnTijeras:
                    posLevantadaUsuario = 2;
                    break;
            }
            changeImageUsuario(posLevantadaUsuario);
            generaImagenMaquina();
            mensajeGanador(compruebaGanador());
            imagenesPorDefecto();
        }
    }


    /*
    * Proposito: Según el ganador muestra mensaje de ganador, empate o perdedor
    * Precondiciones: ha habido algún ganador o empate
    * Entradas: un entero qe será 1 si ha ganado el usuario 0 si hay empate y -1 si gana la maquina
    * Salidas: No hay
    * Postcondiciones: Se ha lanzado un mensaje según el ganador
    * */
    public void mensajeGanador(int resultado){
        String mensaje="";
        switch (resultado){
            case 0:
                    mensaje="Hay empate";
                break;

            case 1:
                    mensaje="Ha ganado el jugador";
                break;

            case -1:
                    mensaje="Ha ganado la máquina";
                break;
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    /*
    * Proposito: Cambia la imagen para el usuario según la que haya elegido
    * Precondiciones: El Usuario ha pulsado algún botón
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha cambiado la imagen por la elegida por el usuario
    * */
    public void changeImageUsuario(int posicion){
        if(haPulsado) {
            //haPulsado=true;
            imagenMiEleccion.setImageResource(arrayImagenes[posicion]);
        }
    }


    /*
    * Proposito: Genera una imagen al azar entre piedra, papel o tijeras
    * Precondiciones: El Usuario ha pulsado algún botón
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha cambiado la imagen por la generada de forma aleatoria
    * */
    public void generaImagenMaquina(){
        Random aleatorio=new Random();
        int generado=aleatorio.nextInt(3);
        if(haPulsado) {
            switch (generado) {
                case 0:
                    posLevantadaMaquina = 0;
                    break;

                case 1:
                    posLevantadaMaquina = 1;
                    break;

                case 2:
                    posLevantadaMaquina = 2;
                    break;
            }
            imagenEleccionMaquina.setImageResource(arrayImagenes[posLevantadaMaquina]);
        }
    }


        /*
    * Proposito: Pone la imagen por defecto en las elecciones
    * Precondiciones: hay dos imagenes elegidas
    * Entradas: no hay
    * Salidas: No hay
    * Postcondiciones: Se ha lanzado un mensaje según el ganador
    * */
    public void imagenesPorDefecto(){
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imagenEleccionMaquina.setImageResource(R.drawable.interrogante);
                imagenMiEleccion.setImageResource(R.drawable.interrogante);
                haPulsado=false;
            }
        },1500);
        //imagenEleccionMaquina.setImageResource(R.drawable.interrogante);
        //imagenMiEleccion.setImageResource(R.drawable.interrogante);
    }


    /*
    * Proposito: Comprueba el ganador
    * Precondiciones: Hay dos imagenes generadas para comparar
    * Entradas: No hay
    * Salidas: Un entero
    * Postcondiciones: El entero será 1 si gana el Usuario, 0 si empatan y -1 si gana la maquina
    * */
    public int compruebaGanador(){
        int ganador=0;

        if(haPulsado) {
            //El usuario gana
            if (posLevantadaMaquina == 0 && posLevantadaUsuario == 1 || posLevantadaMaquina == 1 && posLevantadaUsuario == 2 || posLevantadaMaquina == 2 && posLevantadaUsuario == 0) {
                ganador = 1;
                //La maquina gana
            } else if (posLevantadaUsuario == 0 && posLevantadaMaquina == 1 || posLevantadaUsuario == 1 && posLevantadaMaquina == 2 || posLevantadaUsuario == 2 && posLevantadaMaquina == 0) {
                ganador = -1;
            } else {
                ganador = 0;
            }
            //haPulsado=false;
        }
        return ganador;
    }



}
