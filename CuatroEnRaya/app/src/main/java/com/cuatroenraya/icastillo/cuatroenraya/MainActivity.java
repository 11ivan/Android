package com.cuatroenraya.icastillo.cuatroenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout col0;
    LinearLayout col1;
    LinearLayout col2;
    LinearLayout col3;
    LinearLayout col4;
    LinearLayout col5;
    LinearLayout col6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        col0=(LinearLayout) findViewById(R.id.col0);
        col1=(LinearLayout) findViewById(R.id.col1);
        col2=(LinearLayout) findViewById(R.id.col2);
        col3=(LinearLayout) findViewById(R.id.col3);
        col4=(LinearLayout) findViewById(R.id.col4);
        col5=(LinearLayout) findViewById(R.id.col5);
        col6=(LinearLayout) findViewById(R.id.col6);

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

                break;

            case R.id.col1:

                break;

            case R.id.col2:

                break;

            case R.id.col3:

                break;

            case R.id.col4:

                break;

            case R.id.col5:

                break;

            case R.id.col6:

                break;
        }
    }





}
