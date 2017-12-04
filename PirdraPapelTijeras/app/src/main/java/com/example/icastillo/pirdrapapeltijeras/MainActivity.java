package com.example.icastillo.pirdrapapeltijeras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay;
    Button btnEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=(Button) findViewById(R.id.btnPlay);
        btnEstadisticas=(Button) findViewById(R.id.btnEstadisticas);

        btnPlay.setOnClickListener(this);
        btnEstadisticas.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnPlay:
                
                break;


            case R.id.btnEstadisticas:

                break;
        }
    }







}
