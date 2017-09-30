package com.example.ivan.alineatexto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText txtEdit;
    ImageButton btnDcha;
    ImageButton btnIzq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEdit= (EditText)findViewById(R.id.editText);
        btnDcha=(ImageButton)findViewById(R.id.alignRight);
        btnIzq=(ImageButton)findViewById(R.id.alignLeft);
    }


    /*
    * Propósito: Cuando se pulsa el botón de alinear a la izquierda, se alinea el contenido del EditText a la izquierda
    *
    * */
    public void alineaDcha(View view){
        txtEdit.setGravity(Gravity.RIGHT);
    }

    /*
    * Propósito: Cuando se pulsa el botón de alinear a la izquierda, se alinea el contenido del EditText a la izquierda
    *
    * */
    public void alineaIzq(View view){
        txtEdit.setGravity(Gravity.LEFT);
    }


}
