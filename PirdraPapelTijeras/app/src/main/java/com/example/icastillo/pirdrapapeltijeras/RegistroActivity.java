package com.example.icastillo.pirdrapapeltijeras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textUser;
    EditText textPassword;
    EditText textConfirmPassword;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textUser=(EditText) findViewById(R.id.textUserRegistro);
        textPassword=(EditText) findViewById(R.id.textPasswordRegistro);
        textConfirmPassword=(EditText) findViewById(R.id.textConfirmPassword);
        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Comprobar los datos
            //Escribir usuario en fichero

        //Volver a LoginActivity
    }
}
