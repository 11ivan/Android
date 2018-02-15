package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.app.Dialog;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.clases.Maquina;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

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
    //PopupMenu popupMenu;

    //Boton Pause
    Button btnPause;

    //Dialog Pause
    Dialog dialog;
    Button btnContinuar;
    Button btnReiniciar;
    Button btnSalir;

    //Dialog Reiniciar Partida
    Dialog dialogReinicio;
    Button btnSi;
    Button btnNo;

    //Meter en un metodo para restablecer los valores al reiniciar la partida
    int[] contadores=new int[7];
    int[][] arrayParaleloTablero=new int[7][6];// 7 Columnas y 6 Filas
    int turno=0;//turno será 0 cuando le toque al jugador y 1 cuando le toque a la máquina
    Integer[] idImagenesFichas={R.drawable.ficharoja, R.drawable.fichaamarilla};
    Maquina maquina=new Maquina();
    int totalFichasPuestas=0;
    int[] ultimaFichaPuesta=new int[2];
    boolean hayGanador=false;
    boolean haEmpezado=false;

    //Datos del usuario
    String nombreUsuario="";
    Integer idImagenTablero=0;
    int idUsuario=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        col0.setOnClickListener(this);
        col1.setOnClickListener(this);
        col2.setOnClickListener(this);
        col3.setOnClickListener(this);
        col4.setOnClickListener(this);
        col5.setOnClickListener(this);
        col6.setOnClickListener(this);

        nombreUsuario=getIntent().getStringExtra(Keys.NOMBRE_USUARIO);
        idUsuario=getIntent().getIntExtra(Keys.ID_USUARIO, 0);
        idImagenTablero=getIntent().getIntExtra(Keys.TABLERO, 0);

        //Cargar el tablero del usuario
        cargaTableroJugador();

        //Mostrar nombre de Usuario**

        //No está en uso
        /*popupMenu=new PopupMenu(this, btnPause);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());*/

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

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Continuar partida
                //Cerrar cuadro dialogo
                dialog.dismiss();
                //Devolver valor al cronometro e iniciar si el valor era mayor que 0
                //chronometer.setBase(SystemClock.elapsedRealtime()-chronometer.getBase());
                //chronometer.start();
                if(haEmpezado) {
                    updateChronometer();
                }
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reiniciar Partida
                reiniciaPartida();
                dialog.dismiss();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salir al Menu Principal

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
            }
        });



        //Obtener ancho y alto el pixels para ajustar las imagenes
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        */
    }

    public void cargaTableroJugador(){
        switch (idImagenTablero){
            case R.drawable.tablero4enraya:
                imagenTablero.setImageResource(R.drawable.tablero4enraya);
            break;

            case R.drawable.tableroaluminio:
                imagenTablero.setImageResource(R.drawable.tableroaluminio);
            break;
        }
        //imagenTablero.setScaleX(1);
        //imagenTablero.setScaleY(1);
    }


    public void updateChronometer(){
        int stoppedMilliseconds = 0;
        String chronoText = chronometer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
        chronometer.start();
    }

    @Override
    public void onClick(View view) {
        if(turno==0) {
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
    public void insertarFicha(int columna){
        //Si la columna no está llena y no hay ganador
        if(contadores[columna]<6 && !hayGanador) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(idImagenesFichas[turno]);
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
        contadores[columna]+=1;

        //Aumentamos el total de fichas puestas
        totalFichasPuestas++;

        //Si es la primera ficha iniciamos el cronometro
        if(totalFichasPuestas==1){
            haEmpezado=true;
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }

        //Si la cantidad de fichas puestas es mayor o igual que 7
        if(totalFichasPuestas>=7) {
            //Comprobar si hay
            // ganador
            compruebaGanador();
        }
        //Si hay ganador
        if(hayGanador) {
            //Paramos el cronometro
            chronometer.stop();

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
        if(turno==1){
            turno=0;
        }else {
            turno = 1;
            //Poner la ficha de la Máquina
            ponerFichaMaquina();
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
                insertarFicha(maquina.ponFicha(arrayParaleloTablero));
            }
        },1500);
    }


    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    private void marcaArrayParaleloTablero(int columna){
        if(turno==0) {
            arrayParaleloTablero[columna][contadores[columna]]=1;
        }else {
            arrayParaleloTablero[columna][contadores[columna]] = 2;
        }
        ultimaFichaPuesta[0]=columna;//Columna de la ficha puesta
        ultimaFichaPuesta[1]=contadores[columna];//Fila de la ficha puesta
    }

    /*
    * Proposito: Cuando se pone una ficha en el tablero comprueba si ha ganado
    * Precondiciones: Se ha puesto una ficha en el tablero y el total de fichas puestas debe ser mayor o igual que 7
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Habrá un ganador o no.
    * */
    private void compruebaGanador(){
        //La comprobacion Horizontal siempre se va a hacer  (Por ahora)
        comprobacionHorizontal();

        //Las diagonales también se comprobarán
        if(!hayGanador && sePuedeComprobarDiagonaIzquierdaDerecha()){
            //Comprobar diagonal de izquierda a derecha desde abajo hacia arriba
            compruebaDiagonaIzquierdaDerecha();
        }

        if(!hayGanador && sePuedeComprobarDiagonaDerechaIzquierda()){
            //Comprobar diagonal de derecha a izquierda desde abajo hacia arriba
            compruebaDiagonaDerechaIzquierda();
        }

        //Si en la comprobacion horizontal no hubo ganador
        if(!hayGanador) {
            //Segun la fila que se ha colocado la ultima ficha
            switch (ultimaFichaPuesta[1]) {
                case 3://Si la fila es cualquiera de éstas se comprobará vertical desde la columna y fila en que se puso la ficha hasta abajo
                case 4:comprobacionVertical();
                case 5:
                    break;
            }
        }
    }

    /*
    * Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    *
    * Solo se hara esta comprobacion cuando la ficha no este en
    * */
    public boolean sePuedeComprobarDiagonaIzquierdaDerecha(){
        boolean sePuede=true;
        int columna=ultimaFichaPuesta[0];
        int fila=ultimaFichaPuesta[1];

        if(columna==0 && fila==5 || columna==0 && fila==4 || columna==0 && fila==3 || columna==1 && fila==4 || columna==2 && fila==5 || columna==1 && fila==5
                || columna==4 && fila==0 || columna==5 && fila==0 || columna==6 && fila==0 || columna==5 && fila==1 || columna==6 && fila==1 || columna==6 && fila==2){
            sePuede=false;
        }

        return sePuede;
    }

    /*
    * Proposito: Metodo para comprobar acierto en la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */
    public void compruebaDiagonaIzquierdaDerecha(){
        int aciertos=0;
        int columna=0;
        int fila=2;
        int iteraciones=2;
        int fichaActual=0;
        int siguienteFicha=0;
        boolean sal=false;

        //Primera diagonal
        for (int i=0;i<=iteraciones && !sal;i++){
            fichaActual=arrayParaleloTablero[i][fila];
            siguienteFicha=arrayParaleloTablero[i+1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            fila++;
        }
        if (aciertos==3){
            Toast.makeText(this, "Alguien ha ganado DIAGONAL1", Toast.LENGTH_SHORT).show();
            hayGanador=true;
        }

        if (!hayGanador){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[i][fila];
                siguienteFicha=arrayParaleloTablero[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL2", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }


        if (!hayGanador){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[i][fila];
                siguienteFicha=arrayParaleloTablero[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL3", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }


        if (!hayGanador){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL4", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }


        if (!hayGanador){ //Quinta diagonal  //ESTA NO DETECTA GANADOR
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=2;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL5", Toast.LENGTH_SHORT).show();

                hayGanador=true;
            }
        }


        if (!hayGanador){ //Sexta diagonal  //ESTA TAMPOCO NO DETECTA GANADOR
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL6", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

    }


    /*
    * Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de derecha a izquierda, desde abajo hacia arriba \
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */
    public boolean sePuedeComprobarDiagonaDerechaIzquierda(){
        boolean sePuede=true;
        int columna=ultimaFichaPuesta[0];
        int fila=ultimaFichaPuesta[1];

        if(columna==0 && fila==0 || columna==0 && fila==1 || columna==0 && fila==2 || columna==1 && fila==0 || columna==1 && fila==1 || columna==2 && fila==0
                || columna==4 && fila==5 || columna==5 && fila==5 || columna==5 && fila==4 || columna==6 && fila==5 || columna==6 && fila==4 || columna==6 && fila==3){
            sePuede=false;
        }

        return sePuede;
    }

    /*
    * Proposito: Metodo para acierto en la diagonal de derecha a izquierda, desde abajo hacia arriba \
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */
    public void compruebaDiagonaDerechaIzquierda(){
        int aciertos=0;
        int columna=6;
        int fila=2;
        int iteraciones=2;
        int fichaActual=0;
        int siguienteFicha=0;
        boolean sal=false;

        //Primera diagonal
        for (int i=0;i<=iteraciones && !sal;i++){
            fichaActual=arrayParaleloTablero[columna][fila];
            siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            columna--;
            fila++;
        }
        if (aciertos==3){
            Toast.makeText(this, "Alguien ha ganado DIAGONAL1 dchaIzq", Toast.LENGTH_SHORT).show();
            hayGanador=true;
        }

        if (!hayGanador){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL2 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL3 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=5;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL4 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Quinta diagonal
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=4;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL5 dchaIzq", Toast.LENGTH_SHORT).show();

                hayGanador=true;
            }
        }

        if (!hayGanador){ //Sexta diagonal
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=arrayParaleloTablero[columna][fila];
                siguienteFicha=arrayParaleloTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL6 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
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
    private void comprobacionVertical(){
        int aciertos=0;
        int columna=ultimaFichaPuesta[0];
        int fila=ultimaFichaPuesta[1];
        int fichaActual=0;
        int fichaSiguiente=0;
        int iteraciones=fila-3;//-3 porque solo necesitamos 3 iteraciones a partir de la fila de la ficha puesta
        boolean sigue=true;

        //Esteblecemos el indice an la fila de la ultima ficha puesta
        for(int i=fila;i>iteraciones && sigue;i--){
            fichaActual=arrayParaleloTablero[columna][i];
            fichaSiguiente=arrayParaleloTablero[columna][i-1];

            //Si la ficha es distinta de la siguiente no hay más que comprobar
            if(fichaActual!=fichaSiguiente){
                sigue=false;
            }else {
                aciertos++;
            }
        }
        if(aciertos==3){
            hayGanador=true;
            Toast.makeText(this, "Alguien ha ganado VERTICAL", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    private void comprobacionHorizontal(){
        int aciertos=0;
        int fila=ultimaFichaPuesta[1];
        boolean sal=false;
        int fichaActual=0;
        int siguienteFicha=0;

        //Si la ultima ficha puesta no tiene una igual a su izquierda o su derecha no hay mas que comprobar
        /*if(ultimaFichaPuesta[1]==arrayParaleloTablero[0][1]){

        }*/
        for(int i=0;i<arrayParaleloTablero.length-1 && aciertos<3;i++){
            fichaActual=arrayParaleloTablero[i][fila];
            siguienteFicha=arrayParaleloTablero[i+1][fila];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else if(aciertos>0){
                aciertos=0;
            }
        }
        if(aciertos==3){
            Toast.makeText(this, "Alguien ha ganado HORIZONTAL", Toast.LENGTH_SHORT).show();
            hayGanador=true;
        }
    }

    /*
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
        switch (contadores[column]){

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
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        //translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }

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
        switch (turno){
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

    /*
    * Proposito: Reinicia los valores de la partida
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void reiniciaPartida(){
        contadores=new int[7];
        arrayParaleloTablero=new int[7][6];// 7 Columnas y 6 Filas
        turno=0;//turno será 0 cuando le toque al jugador y 1 cuando le toque a la máquina      //Segun quien ganó la última partida iniciará un jugador u otro
        //Integer[] idImagenesFichas={R.drawable.ficha, R.drawable.fichaamarillabuena};
        //Maquina maquina=new Maquina();
        totalFichasPuestas=0;
        ultimaFichaPuesta=new int[2];
        hayGanador=false;
        haEmpezado=false;
        col0.removeAllViews();
        col1.removeAllViews();
        col2.removeAllViews();
        col3.removeAllViews();
        col4.removeAllViews();
        col5.removeAllViews();
        col6.removeAllViews();
        //chronometer=(Chronometer) findViewById(R.id.chronometer);
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());//Poner cronometro a 0
    }


    //Esto es para el PopUpMenu
    /*@Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.continuar:
                popupMenu.dismiss();
            break;

            case R.id.salir:
                finish();//Finalizamos la actividad
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
            break;
        }
        return false;
    }*/

}
