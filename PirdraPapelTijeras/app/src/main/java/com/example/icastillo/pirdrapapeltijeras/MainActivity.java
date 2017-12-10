package com.example.icastillo.pirdrapapeltijeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay;
    Button btnEstadisticas;
    String nombreUsuario="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=(Button) findViewById(R.id.btnPlay);
        btnEstadisticas=(Button) findViewById(R.id.btnEstadisticas);

        btnPlay.setOnClickListener(this);
        btnEstadisticas.setOnClickListener(this);

        nombreUsuario=getIntent().getStringExtra("NombreUsuario");

    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch(view.getId()){

            case R.id.btnPlay:
                    intent=new Intent(this, JuegoActivity.class);
                    intent.putExtra("NombreUsuario", nombreUsuario);
                break;

            case R.id.btnEstadisticas:
                    intent=new Intent(this, EstadisticasActivity.class);
                    intent.putExtra("NombreUsuario", nombreUsuario);
                break;
        }
        startActivity(intent);
    }







}
