package com.example.ivan.cambiacolorletras;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtView;
    Button btnRed;
    Button btnGreen;
    Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView=(TextView)findViewById(R.id.texto);
        btnRed=(Button)findViewById(R.id.btnRed);
        btnGreen=(Button)findViewById(R.id.btnGreen);
        btnBlue=(Button)findViewById(R.id.btnBlue);
    }

    /*public void botonPulsado(View view){
        String btnPulsado="";
        btnPulsado=
    }*/

    public void colorVerde(View view){
        txtView.setTextColor(Color.GREEN);
    }

    public void colorRojo(View view){
        txtView.setTextColor(Color.RED);
    }

    public void colorAzul(View view){
        txtView.setTextColor(Color.BLUE);
    }






}
