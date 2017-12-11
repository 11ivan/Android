package com.example.icastillo.pirdrapapeltijeras;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
                    //intent.putExtra("NombreUsuario", nombreUsuario);
                break;
        }
        startActivity(intent);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean pulsado=false;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            pulsado=true;
            ShowDialog();
        }
        //return super.onKeyDown(keyCode, event);
        return pulsado;
    }

    public void ShowDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Aviso");
        builder.setMessage("Seguro que quiere salir?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //aqui se hacen cosas
                iniciaLoginActivity();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Y aqui otras cosas
            }
        });
        //Creamos el mensaje de alerta
        builder.create().show();
    }

    public void iniciaLoginActivity(){
        finish();
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
