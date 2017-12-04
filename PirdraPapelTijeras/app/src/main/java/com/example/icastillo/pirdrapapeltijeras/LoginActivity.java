package com.example.icastillo.pirdrapapeltijeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textUser;
    EditText textPassword;
    Button btnLogin;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUser=(EditText) findViewById(R.id.textUser);
        textPassword=(EditText) findViewById(R.id.textPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegistro=(Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnLogin:
                    //Comprobar datos en fichero de usuarios
                    //en caso de ser correctos iniciamos mainActivity
                    //sino lanzamos mensaje de error
                break;

            case R.id.btnRegister:
                    //Iniciamos actividad de Registro
                    intent=new Intent(this, RegistroActivity.class);
                break;
        }
        startActivity(intent);
    }
}
