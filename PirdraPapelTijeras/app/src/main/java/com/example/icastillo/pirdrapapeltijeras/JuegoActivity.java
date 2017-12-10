package com.example.icastillo.pirdrapapeltijeras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    ImageButton btnPiedra;
    ImageButton btnPapel;
    ImageButton btnTijeras;
    ImageView imagenMiEleccion;
    ImageView imagenEleccionMaquina;
    boolean haPulsado=false;
    Integer[] arrayImagenes={R.drawable.piedra, R.drawable.ocb, R.drawable.manostijeras};
    int posLevantadaMaquina;
    int posLevantadaUsuario;
    String nombreUsuario="";
    ImageButton btnPause;
    PopupMenu popupMenu;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int vecesPiedra=0;
    int vecesPapel=0;
    int vecesTijera=0;
    int victorias=0;
    int derrotas=0;
    int empates=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        btnPiedra=(ImageButton) findViewById(R.id.btnPiedra);
        btnPapel=(ImageButton)findViewById(R.id.btnPapel);
        btnTijeras=(ImageButton)findViewById(R.id.btnTijeras);
        btnPause=(ImageButton)findViewById(R.id.btnPause);
        imagenMiEleccion=(ImageView) findViewById(R.id.miEleccion);
        imagenEleccionMaquina=(ImageView) findViewById(R.id.eleccionMaquina);
        popupMenu=new PopupMenu(this, btnPause);

        btnPiedra.setOnClickListener(this);
        btnPapel.setOnClickListener(this);
        btnTijeras.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());
        nombreUsuario=getIntent().getStringExtra("NombreUsuario");

        sharedPreferences=getSharedPreferences(getString(R.string.file_estadisticas), Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean pulsado=false;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            pulsado=true;
            //Mostrar PopUpMenu
            muestraPopUpMenu();
        }
        //return super.onKeyDown(keyCode, event);
        return pulsado;
    }

    @Override
    public void onClick(View v) {
        if(!haPulsado) {
            //haPulsado=true;
            switch (v.getId()) {
                case R.id.btnPiedra:
                    posLevantadaUsuario = 0;
                    vecesPiedra++;
                    break;

                case R.id.btnPapel:
                    posLevantadaUsuario = 1;
                    vecesPapel++;
                    break;

                case R.id.btnTijeras:
                    posLevantadaUsuario = 2;
                    vecesTijera++;
                    break;

                case R.id.btnPause:
                        //Mostrar PopUp Menu
                        muestraPopUpMenu();
                    break;
            }
            if(v.getId()!=R.id.btnPause) {
                haPulsado=true;
                changeImageUsuario(posLevantadaUsuario);
                generaImagenMaquina();
                mensajeGanador(compruebaGanador());
                imagenesPorDefecto();
            }
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
        int numGenerado=aleatorio.nextInt(3);
        if(haPulsado) {
            switch (numGenerado) {
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
        },2500);
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
                victorias++;
                //La maquina gana
            } else if (posLevantadaUsuario == 0 && posLevantadaMaquina == 1 || posLevantadaUsuario == 1 && posLevantadaMaquina == 2 || posLevantadaUsuario == 2 && posLevantadaMaquina == 0) {
                ganador = -1;
                derrotas++;
            } else {
                ganador = 0;
                empates++;
            }
            //haPulsado=false;
        }
        return ganador;
    }

    /*
    * Proposito: Muestra el PopUP Menu de Pause
    * Precondiciones: El Usuario ha pulsado el boton de pause o el boton Back
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha mostrado el PopUP Menu de Pause
    * */
    public void muestraPopUpMenu(){
        popupMenu.show();
    }

    //Realiza las acciones necesarias cuando se pulsa un boton del PopUpMenu
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        //Continuar partida o salir de la Activity
        switch (item.getItemId()){
            case R.id.continuar:
                    popupMenu.dismiss();
                break;

            case R.id.salir:
                    //popupMenu.dismiss();
                    guardaEstadisticas();
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                break;
        }
        return false;
    }

    /*
    * Proposito: Guarda las estadísticas de juego en el fichero sharedPreferences
    * Precondiciones: Se ha pulsado el item salir del PopUp Menu
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Se ha guardado en el fichero sharedPreferences las estadísticas de juego
    * */
    public void guardaEstadisticas(){//QUIZAS PODRIA AHORRAR EL IF DEBIDO A LAS ENTRADAS DE LOS METODOS GET DE SHAREDPREFERERNCES
        if(sharedPreferences.contains(nombreUsuario+getString(R.string.victorias))){//si ya se han guardado algunas estadisticas sobre este jugador se suman a las que ya tenia
            editor.putInt(nombreUsuario + getString(R.string.veces_piedra), vecesPiedra + sharedPreferences.getInt(getString(R.string.veces_piedra), 0) );//las entradas de cualquier metodo get de
            editor.putInt(nombreUsuario + getString(R.string.veces_papel), vecesPapel + sharedPreferences.getInt(getString(R.string.veces_papel), 0));    //sharedPreferences exige un valor por defecto
            editor.putInt(nombreUsuario + getString(R.string.veces_tijera), vecesTijera + sharedPreferences.getInt(getString(R.string.veces_tijera), 0)); //en caso de no encntrar la clave buscada
            editor.putInt(nombreUsuario + getString(R.string.victorias), victorias + sharedPreferences.getInt(getString(R.string.victorias), 0));
            editor.putInt(nombreUsuario + getString(R.string.derrotas), derrotas + sharedPreferences.getInt(getString(R.string.derrotas), 0));
            editor.putInt(nombreUsuario + getString(R.string.empates), empates + sharedPreferences.getInt(getString(R.string.empates), 0));
        }else {//sino se guardaran como nuevas
            editor.putInt(nombreUsuario + getString(R.string.veces_piedra), vecesPiedra);
            editor.putInt(nombreUsuario + getString(R.string.veces_papel), vecesPapel);
            editor.putInt(nombreUsuario + getString(R.string.veces_tijera), vecesTijera);
            editor.putInt(nombreUsuario + getString(R.string.victorias), victorias);
            editor.putInt(nombreUsuario + getString(R.string.derrotas), derrotas);
            editor.putInt(nombreUsuario + getString(R.string.empates), empates);
        }
        editor.commit();
    }


}
