package com.cuatroenraya.icastillo.cuatroenraya;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    int contador=0;

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
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

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


    public void insertarFicha(int columna){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.ficha);
        imageView.setScaleX(15);
        imageView.setScaleY(11);
        /*LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(50, 50);*/
        //params.setMargins(0,0,0,0);

        //imageView.setLayoutParams(params);
        //imageView.setMinimumHeight(350);
        //imageView.setMinimumWidth(500);

        Animation translate;
        translate= AnimationUtils.loadAnimation(this, R.anim.translate);
        translate.reset();
        //imageView.startAnimation(translate);
        switch (columna){

            case 0:
                col0.addView(imageView);
                //relativeLayout.addView(imageView);//
                break;

            case 1:
                col1.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;

            case 2:
                col2.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;

            case 3:
                col3.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;

            case 4:
                col4.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;

            case 5:
                col5.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;

            case 6:
                col6.addView(imageView);//
                //relativeLayout.addView(imageView);//
                break;
        }
        imageView.startAnimation(translate);
    }


}
