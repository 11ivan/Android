package com.cuatroenraya.icastillo.cuatroenraya;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout col0;
    RelativeLayout col1;
    RelativeLayout col2;
    RelativeLayout col3;
    RelativeLayout col4;
    RelativeLayout col5;
    RelativeLayout col6;
    RelativeLayout relativeLayout;
    int[] contadores={0,0,0,0,0,0,0};
    int[][] arrayParaleloTablero=new int[7][6];// 7 Columnas y 6 Filas
    int turno=0;//turno será 0 cuando le toque al jugador y 1 cuando le toque a la máquina
    Integer[] idImagenesFichas={R.drawable.ficha, R.drawable.fichaamarillabuena};
    Maquina maquina=new Maquina();
    int totalFichasPuestas=0;
    int[] ultimaFichaPuesta=new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout=(RelativeLayout) findViewById(R.id.relative);

        col0=(RelativeLayout) findViewById(R.id.col0);
        col1=(RelativeLayout) findViewById(R.id.col1);
        col2=(RelativeLayout) findViewById(R.id.col2);
        col3=(RelativeLayout) findViewById(R.id.col3);
        col4=(RelativeLayout) findViewById(R.id.col4);
        col5=(RelativeLayout) findViewById(R.id.col5);
        col6=(RelativeLayout) findViewById(R.id.col6);

        col0.setOnClickListener(this);
        col1.setOnClickListener(this);
        col2.setOnClickListener(this);
        col3.setOnClickListener(this);
        col4.setOnClickListener(this);
        col5.setOnClickListener(this);
        col6.setOnClickListener(this);


        /*  //Obtener ancho y alto el pixels para ajustar las imagenes
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels; // ancho absoluto en pixels
            int height = metrics.heightPixels; // alto absoluto en pixels
        */
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

        //Si la columna no está llena
        if(contadores[columna]<6) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(idImagenesFichas[turno]);
            imageView.setScaleX(13);
            imageView.setScaleY(11);

            //Comprobar cantidad de fichas que tiene la columna para definir la animacion
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

            //Ponemos marca en el array paralelo al tablero,
            marcaArrayParaleloTablero(columna);

            //Aumentamos el contador de la columna
            contadores[columna]+=1;

            //Aumentamos el total de fichas puestas
            totalFichasPuestas++;

            //Comprobar ganador
            compruebaGanador();

            //Cambiamos el turno
            cambiaTurno();
        }//fin si
    }


    /*
    * Proposito:
    * Precondiciones:
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
    * Precondiciones: Se ha puesto una ficha en el tablero
    * Entradas: No hay
    * Salidas: Un entero
    * Postcondiciones: El entero será ...
    * Restricciones: El total de fichas puestas debe ser mayor o igual que 7
    * */
    private int compruebaGanador(){
        int ganador=-1;

        if(totalFichasPuestas>=7){

            //Tomar como referencia la ultima ficha puesta para hacer el barrido

            //Metodo para contar las fichas que hay en una columna/fila/diagonal

            //Comprobar si la última ficha puesta está en la cuarta fila o más arriba(en este caso no haremos barrido vertical)


        }
        return ganador;
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
        int toXDelta=2;
        if(column==0){
            toXDelta=9;
        }

        switch (contadores[column]){

            case 0:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, 415);
                break;

            case 1:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, 255);
                break;

            case 2:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, 110);
                break;

            case 3:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, -50);
                break;

            case 4:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, -205);
                break;

            case 5:
                translateAnimation=new TranslateAnimation(0, toXDelta, -450, -350);
                break;
        }
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        //translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }


}
