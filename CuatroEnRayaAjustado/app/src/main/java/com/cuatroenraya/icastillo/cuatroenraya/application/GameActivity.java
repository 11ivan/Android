package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.clases.Juego;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.DatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.clases.Maquina;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Puntuacion;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioDatosGameActivity;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioPuntuaciones;
import com.cuatroenraya.icastillo.cuatroenraya.viewmodels.ViewModelGameActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //Columnas, Relative Principal, Cronometro e Imagen del tablero
    RelativeLayout col0;
    RelativeLayout col1;
    RelativeLayout col2;
    RelativeLayout col3;
    RelativeLayout col4;
    RelativeLayout col5;
    RelativeLayout col6;
    RelativeLayout relativeLayout;
    Chronometer chronometer;
    ImageView imagenTablero;

    //Boton Pause
    Button btnPause;

    //TextView Turno
    TextView textViewTurno;

    //Dialog Pause
    Dialog dialog;
    Button btnContinuar;
    Button btnReiniciar;
    Button btnSalir;

    //Dialog Reiniciar Partida
    Dialog dialogReinicio;
    Button btnSi;
    Button btnNo;

    Integer[] idImagenesFichas={R.drawable.ficharoja, R.drawable.fichaamarilla};
    Maquina maquina=new Maquina();

    //Datos del usuario llegados desde mainactivity
    String nombreUsuario="";
    Integer idImagenTablero=0;
    int idUsuario=0;
    int modoDeJuego=0;//Modo 1 un jugador, modo 2 dos jugadores ...

    Puntuacion puntuacion=new Puntuacion();
    ViewModelGameActivity viewModelGameActivity;
    //DatosGameActivity datosGameActivity=new DatosGameActivity();
    RepositorioPuntuaciones repositorioPuntuaciones;
    RepositorioDatosGameActivity repositorioDatosGameActivity;
    Juego juego=new Juego();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        repositorioPuntuaciones=new RepositorioPuntuaciones(getApplication());
        repositorioDatosGameActivity=new RepositorioDatosGameActivity(getApplication());

        relativeLayout=(RelativeLayout) findViewById(R.id.relative);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        col0=(RelativeLayout) findViewById(R.id.col0);
        col1=(RelativeLayout) findViewById(R.id.col1);
        col2=(RelativeLayout) findViewById(R.id.col2);
        col3=(RelativeLayout) findViewById(R.id.col3);
        col4=(RelativeLayout) findViewById(R.id.col4);
        col5=(RelativeLayout) findViewById(R.id.col5);
        col6=(RelativeLayout) findViewById(R.id.col6);
        btnPause=(Button) findViewById(R.id.btnPause);
        imagenTablero=(ImageView) findViewById(R.id.imageTablero);
        textViewTurno=(TextView) findViewById(R.id.textViewTurno);

        col0.setOnClickListener(this);
        col1.setOnClickListener(this);
        col2.setOnClickListener(this);
        col3.setOnClickListener(this);
        col4.setOnClickListener(this);
        col5.setOnClickListener(this);
        col6.setOnClickListener(this);

        modoDeJuego=getIntent().getIntExtra(Keys.MODOJUEGO, 1);
        nombreUsuario=getIntent().getStringExtra(Keys.NOMBRE_USUARIO);
        idUsuario=getIntent().getIntExtra(Keys.ID_USUARIO, 0);
        idImagenTablero=getIntent().getIntExtra(Keys.TABLERO, 0);

        if(modoDeJuego==1){
            textViewTurno.setVisibility(View.INVISIBLE);
        }else {
            textViewTurno.setText("Turno de "+nombreUsuario);
        }

        //Cargar el tablero del usuario
        cargaTableroJugador();
        //Mostrar nombre de Usuario**

        //Boton Pause
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pausar cronometro
                chronometer.stop();
                //Mostrar Menu Pause
                dialog.show();
            }
        });
        //Dialog Pause. Se muestra al pulsar el boton pause o atras en el dispositivo
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.customdialogpause);
        dialog.setTitle("Pause");
        dialog.setCanceledOnTouchOutside(false);

        //Dar valor a los buttons del Dialog
        btnContinuar=(Button)dialog.findViewById(R.id.btnContinuar);
        btnReiniciar=(Button) dialog.findViewById(R.id.btnReiniciar);
        btnSalir=(Button) dialog.findViewById(R.id.btnSalir);
        //Boton Continuar
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Continuar partida
                //Cerrar cuadro dialogo
                dialog.dismiss();
                if(juego.getDatosGameActivity().isHaEmpezado()) {
                    updateChronometer(0);//NO Hay datos cargados
                }
            }
        });
        //Boton Reiniciar
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reiniciar Partida
                reiniciaPartida();
                dialog.dismiss();
            }
        });
        //Boton Salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salir al Menu Principal
                goToMainActivity();
            }
        });
        //Dialog Reinicio. Se muestra cuando gana o pierde partida
        dialogReinicio=new Dialog(this);
        dialogReinicio.setContentView(R.layout.dialogreiniciarpartida);
        dialogReinicio.setCanceledOnTouchOutside(false);
        btnSi=(Button)dialogReinicio.findViewById(R.id.btnSi);
        btnNo=(Button) dialogReinicio.findViewById(R.id.btnNo);

        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciaPartida();
                dialogReinicio.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Enviar a Menu Principal
                goToMainActivity();
            }
        });

        //ViewModel
        viewModelGameActivity= ViewModelProviders.of(this).get(ViewModelGameActivity.class);
        viewModelGameActivity.getDatosGameActivityLiveData().observe(this, new Observer<DatosGameActivity>() {
            @Override
            public void onChanged(@Nullable DatosGameActivity datosGameActivityAct) {
                if(datosGameActivityAct!=null) {
                    //Cargamos los datos de la partida
                    juego.setDatosGameActivity(datosGameActivityAct);

                    if (!juego.getDatosGameActivity().isHayGanador()) {
                        //Colocar las fichas en el tablero
                        cargaFichasEnTablero();
                        //Dar valor al cronometro
                        updateChronometer(1);//Hay datos cargados

                        //Comprobar si el turno lo tiene la maquina para insertar la ficha
                        if (juego.getDatosGameActivity().getTurno() == 1) {
                            //Si el turno lo tiene la maquina iniciamos el cronometro e insertamos la ficha
                            //updateChronometer();
                            if(modoDeJuego==2){
                                dialog.show();
                                textViewTurno.setText("Turno de Invitado");
                            }else {
                                chronometer.start();
                                maquina.ponFicha(juego.getDatosGameActivity().getArrayParaleloTablero(), juego.getDatosGameActivity().getUltimaFichaPuesta());
                            }
                        } else {//Sino mostramos el dialog de pause porque el turno lo tenia el Usuario
                            if (/*!juego.getDatosGameActivity().isHayGanador() && */juego.getDatosGameActivity().isHaEmpezado()) {
                                dialog.show();
                                if(modoDeJuego==2){
                                    textViewTurno.setText("Turno de "+nombreUsuario);
                                }
                            } /*else {
                                reiniciaPartida();
                            }*/
                        }

                        //Eliminamos los datos que habia en la base de datos para cuando volvamos a guardar
                        repositorioDatosGameActivity.deleteDatosGameActivity(juego.getDatosGameActivity());
                    }else {
                        //Eliminamos los datos que habia en la base de datos para cuando volvamos a guardar
                        repositorioDatosGameActivity.deleteDatosGameActivity(juego.getDatosGameActivity());
                        reiniciaPartida();
                    }
                }
            }
        });

        viewModelGameActivity.cargaDatosGameActivity();

        //Obtener ancho y alto el pixels para ajustar las imagenes
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        */
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Si ha empezado a jugar guardamos los datos de la partida
        if(juego.getDatosGameActivity().isHaEmpezado()) {
            guardarDatosDePartida();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Restauramos los datos de la partida       //Entraria en onchanged
        //viewModelGameActivity.cargaDatosGameActivity();
    }

    /**
    *
    *
    *
    * */
    public void guardarDatosDePartida(){
        //Paramos el Timer
        chronometer.stop();
        //Obtenemos el tiempo de partida que lleva
        juego.getDatosGameActivity().setTiempoPartida(chronometer.getText().toString());
        //Guardamos los datos de la partida
        repositorioDatosGameActivity.insertDatosGameActivity(juego.getDatosGameActivity());
    }

    /**
    *
    * */
    public void goToMainActivity(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
    *
    * */
    public void cargaTableroJugador(){
        switch (idImagenTablero){
            case R.drawable.tablero4enraya:
                imagenTablero.setImageResource(R.drawable.tablero4enraya);
            break;

            case R.drawable.tableroaluminio:
                imagenTablero.setImageResource(R.drawable.tableroaluminio);
            break;
        }
    }

    /**
    *
    *
    * */
    public void updateChronometer(int typeUpdate){
        int stoppedMilliseconds = 0;
        String chronoText="";

        if(typeUpdate==0) {
            chronoText = chronometer.getText().toString();
        }else {
            chronoText = juego.getDatosGameActivity().getTiempoPartida();
        }

        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
        if(typeUpdate==0) {
            chronometer.start();
        }
    }

    /**
    *
    *
    * */
    /*public void updateChronometerLoadGame(){
        int stoppedMilliseconds = 0;
        String chronoText = juego.getDatosGameActivity().getTiempoPartida();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
        //chronometer.start();
    }*/

    @Override
    public void onClick(View view) {
        if(modoDeJuego==2 || juego.getDatosGameActivity().getTurno()==0) {
            switch (view.getId()) {

                case R.id.col0:
                    //Insertar ficha en la columna
                    insertarFicha(0);
                    break;

                case R.id.col1:
                    insertarFicha(1);
                    break;

                case R.id.col2:
                    insertarFicha(2);
                    break;

                case R.id.col3:
                    insertarFicha(3);
                    break;

                case R.id.col4:
                    insertarFicha(4);
                    break;

                case R.id.col5:
                    insertarFicha(5);
                    break;

                case R.id.col6:
                    insertarFicha(6);
                    break;
            }
        }
    }

     /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
     public void cargaFichasEnTablero(){
         boolean sal=false;
         for(int i=0;i<juego.getDatosGameActivity().getArrayParaleloTablero().length;){

             if(juego.getDatosGameActivity().getArrayParaleloTablero()[i][0]!=0){//Si no hay ficha en la primera posicion aumentamos la columna
                sal=false;
                for (int j=0;j<juego.getDatosGameActivity().getArrayParaleloTablero()[0].length && !sal;j++){
                    if(juego.getDatosGameActivity().getArrayParaleloTablero()[i][j]==0){
                        sal=true;
                        i++;
                    }else {
                        insertarFichaCargaPartida(i, j, juego.getDatosGameActivity().getArrayParaleloTablero()[i][j]);
                    }
                }
             }else {
                 i++;
             }
         }
     }

    /*
   * Proposito:
   * Precondiciones:
   * Entradas:
   * Salidas:
   * Postcondiciones:
   * */
    public void insertarFichaCargaPartida(int columna, int row, int ficha){
        ImageView imageView = new ImageView(this);
        int indexImagenFicha=0;
        if(ficha==2){
            indexImagenFicha=1;
        }/*else if(ficha==2){
            indexImagenFicha=1;
        }*/
        imageView.setImageResource(idImagenesFichas[indexImagenFicha]);
        imageView.setScaleX(15);
        imageView.setScaleY(15);

        //Comprobamos la cantidad de fichas que tiene la columna para definir la animacion
        TranslateAnimation translateAnimation=getCurrentAnimationCargaPartida(row);
        //TranslateAnimation translateAnimation=getCurrentAnimation(row);
        switch (columna) {
            case 0:
                col0.addView(imageView);
                break;
            case 1:
                col1.addView(imageView);
                break;
            case 2:
                col2.addView(imageView);
                break;
            case 3:
                col3.addView(imageView);
                break;
            case 4:
                col4.addView(imageView);
                break;
            case 5:
                col5.addView(imageView);
                break;
            case 6:
                col6.addView(imageView);
                break;
        }
        imageView.startAnimation(translateAnimation);
        //Gestionamos la jugada
        //gestionaJugada(columna);
    }

    /*
    * Proposito: Recibe el indice de la columna y devuelve la animacion con los parametros
    *            apropiados para esa columna.
    * Precondiciones: La columna no está llena
    * Entradas: Un entero que es el indice de la columna
    * Salidas: Un objeto TranslateAnimation
    * Postcondiciones: EL objeto TranslateAnimation está construido con los parámetros adecuados para la columna
    * */
    private TranslateAnimation getCurrentAnimationCargaPartida(int row){
        TranslateAnimation translateAnimation=null;
        //Según la cantidad de fichas que tenga la columna
        switch (row){

            case 0:
                translateAnimation=new TranslateAnimation(0, 0, -350, 275);
                break;
            case 1:
                translateAnimation=new TranslateAnimation(0, 0, -350, 178);
                break;
            case 2:
                translateAnimation=new TranslateAnimation(0, 0, -350, 80);
                break;
            case 3:
                translateAnimation=new TranslateAnimation(0, 0, -350, -17);
                break;
            case 4:
                translateAnimation=new TranslateAnimation(0, 0, -350, -113);
                break;
            case 5:
                translateAnimation=new TranslateAnimation(0, 0, -350, -209);
                break;
        }
        translateAnimation.setDuration(800);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void insertarFicha(int columna){
        //Si la columna no está llena y no hay ganador
        if(juego.getDatosGameActivity().getContadoresColumnas()[columna]<6 && !juego.getDatosGameActivity().isHayGanador()) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(idImagenesFichas[juego.getDatosGameActivity().getTurno()]);
            imageView.setScaleX(15);
            imageView.setScaleY(15);

            //Comprobamos la cantidad de fichas que tiene la columna para definir la animacion
            TranslateAnimation translateAnimation=getCurrentAnimation(columna);

            switch (columna) {

                case 0:
                    col0.addView(imageView);
                    break;
                case 1:
                    col1.addView(imageView);
                    break;
                case 2:
                    col2.addView(imageView);
                    break;
                case 3:
                    col3.addView(imageView);
                    break;
                case 4:
                    col4.addView(imageView);
                    break;
                case 5:
                    col5.addView(imageView);
                    break;
                case 6:
                    col6.addView(imageView);
                    break;
            }
            imageView.startAnimation(translateAnimation);

            //Gestionamos la jugada
            gestionaJugada(columna);
        }//fin si
    }

    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void preguntaVolverAJugar(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogReinicio.show();
            }
        },1000);
    }

    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void gestionaJugada(int columna){
        //Ponemos marca en el array paralelo al tablero,
        marcaArrayParaleloTablero(columna);

        //Aumentamos el contador de la columna
        juego.getDatosGameActivity().incrementarContadorColumna(columna);

        //Aumentamos el total de fichas puestas
        juego.getDatosGameActivity().setTotalFichasPuestas(juego.getDatosGameActivity().getTotalFichasPuestas()+1);

        //Si es la primera ficha iniciamos el cronometro
        if(juego.getDatosGameActivity().getTotalFichasPuestas()==1){
            juego.getDatosGameActivity().setHaEmpezado(true);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }

        //Si la cantidad de fichas puestas es mayor o igual que 7
        if(juego.getDatosGameActivity().getTotalFichasPuestas()>=7) {
            //Comprobar si hay ganador
            juego.compruebaGanador();
        }
        //Si hay ganador
        if(juego.getDatosGameActivity().isHayGanador()) {
            //Paramos el cronometro
            chronometer.stop();

            //Guardamos la puntuacion en la base de datos
            puntuacion.setIdUsuario(idUsuario);
            //puntuacion.setFechaPartida();Cuando empiece la partida
            puntuacion.setTiempoPartida(chronometer.getText().toString());
            String resultado=juego.getDatosGameActivity().getTurno()==0 ? "Victoria" : "Derrota";
            puntuacion.setResultado(resultado);
            repositorioPuntuaciones.insertPuntuacion(puntuacion);

            //Mostramos mensaje de ganador/Perdedor
            mostrarMensajeVictoriaDerrota();

            //Preguntamos si vuelve a jugar
            preguntaVolverAJugar();

        }else {
            //Cambiamos el turno
            cambiaTurno();
        }
    }

    /*
    * Proposito:
    * Precondiciones: No debe haber ganador
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    private void cambiaTurno(){
        //juego.getDatosGameActivity().setTurno(juego.getDatosGameActivity().getTurno()==1 ? 0 : 1);
        if(juego.getDatosGameActivity().getTurno()==1){
            juego.getDatosGameActivity().setTurno(0);
            if(modoDeJuego==2) {
                textViewTurno.setText("Turno de " + nombreUsuario);
            }
        }else {
            juego.getDatosGameActivity().setTurno(1);
            //Poner la ficha de la Máquina si está en modo un jugador
            if(modoDeJuego==1) {
                ponerFichaMaquina();
            }else {
                textViewTurno.setText("Turno de Invitado");
            }
        }
    }

    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    private void ponerFichaMaquina(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                insertarFicha(maquina.ponFicha(juego.getDatosGameActivity().getArrayParaleloTablero(), juego.getDatosGameActivity().getUltimaFichaPuesta()));
            }
        },1500);
    }

    /**
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    private void marcaArrayParaleloTablero(int columna){
        if(juego.getDatosGameActivity().getTurno()==0) {//Si es el turno del usuario
            juego.getDatosGameActivity().getArrayParaleloTablero()[columna][juego.getDatosGameActivity().getContadoresColumnas()[columna]]=1;
        }else {
            juego.getDatosGameActivity().getArrayParaleloTablero()[columna][juego.getDatosGameActivity().getContadoresColumnas()[columna]] = 2;
        }
        juego.getDatosGameActivity().setUltimaFichaPuesta(0, columna);//Columna de la ficha puesta
        juego.getDatosGameActivity().setUltimaFichaPuesta(1, juego.getDatosGameActivity().getContadoresColumnas()[columna]);//Fila de la ficha puesta
    }

    /**
    * Proposito: Recibe el indice de la columna y devuelve la animacion con los parametros
    *            apropiados para esa columna.
    * Precondiciones: La columna no está llena
    * Entradas: Un entero que es el indice de la columna
    * Salidas: Un objeto TranslateAnimation
    * Postcondiciones: EL objeto TranslateAnimation está construido con los parámetros adecuados para la columna
    * */
    private TranslateAnimation getCurrentAnimation(int column){
        TranslateAnimation translateAnimation=null;
        //Según la cantidad de fichas que tenga la columna
        switch (juego.getDatosGameActivity().getContadoresColumnas()[column]){

            case 0:
                translateAnimation=new TranslateAnimation(0, 0, -350, 275);
                break;
            case 1:
                translateAnimation=new TranslateAnimation(0, 0, -350, 178);
                break;
            case 2:
                translateAnimation=new TranslateAnimation(0, 0, -350, 80);
                break;
            case 3:
                translateAnimation=new TranslateAnimation(0, 0, -350, -17);
                break;
            case 4:
                translateAnimation=new TranslateAnimation(0, 0, -350, -113);
                break;
            case 5:
                translateAnimation=new TranslateAnimation(0, 0, -350, -209);
                break;
        }
        translateAnimation.setDuration(800);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setFillAfter(true);
        //translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }


     /**
     * Proposito:
     * Precondiciones:
     * Entradas:
     * Salidas:
     * Postcondiciones:
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean pulsado=false;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            pulsado=true;
            //Mostrar PopUpMenu
            //popupMenu.show();
            chronometer.stop();
            dialog.show();
        }
        //return super.onKeyDown(keyCode, event);
        return pulsado;
    }

    public void mostrarMensajeVictoriaDerrota(){
        String mensaje="";
        switch (juego.getDatosGameActivity().getTurno()){
            case 0:
                //Gana el Usuario
                mensaje="Bien hecho";
            break;

            case 1:
                //Gana la Maquina
                mensaje="Paquete!";
            break;
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        //REINICIAMOS AQUI POR AHORA
        //reiniciaPartida();
    }

    /**
    * Proposito: Reinicia los valores de la partida
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void reiniciaPartida(){
        juego=new Juego();
        textViewTurno.setText("Turno de "+nombreUsuario);
        col0.removeAllViews();
        col1.removeAllViews();
        col2.removeAllViews();
        col3.removeAllViews();
        col4.removeAllViews();
        col5.removeAllViews();
        col6.removeAllViews();

        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());//Poner cronometro a 0
    }




}
