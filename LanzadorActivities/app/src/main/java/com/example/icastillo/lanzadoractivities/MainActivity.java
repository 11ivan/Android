package com.example.icastillo.lanzadoractivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonGaleria;
    Button botonCalculadora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonGaleria=(Button) findViewById(R.id.btnGallery);
        botonCalculadora=(Button) findViewById(R.id.btnCalculator);
        botonGaleria.setOnClickListener(this);
        botonCalculadora.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){

            case R.id.btnGallery:
                    intent=new Intent(this, GaleriaActivity.class);
                break;

            case R.id.btnCalculator:
                intent=new Intent(this, CalculadoraActivity.class);
                break;


        }
        startActivity(intent);
    }




}
