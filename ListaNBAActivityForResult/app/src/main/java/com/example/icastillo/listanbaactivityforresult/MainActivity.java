package com.example.icastillo.listanbaactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int ACTIVITY_LISTA=1;
    ImageView imagen;
    TextView nombre;
    TextView estadio;
    Equipo equipo;
    Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen=(ImageView) findViewById(R.id.image);
        nombre=(TextView) findViewById(R.id.nombre);
        estadio=(TextView) findViewById(R.id.estadio);
        btnSelect=(Button) findViewById(R.id.botonSelect);
        btnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, ListaEquiposActivity.class);
        startActivityForResult(intent, ACTIVITY_LISTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Equipo equipo=null;
        if (requestCode == ACTIVITY_LISTA) {
            if(resultCode == Activity.RESULT_OK){
                equipo=data.getParcelableExtra("equipo");
                imagen.setImageResource(equipo.getIdImagen());
                nombre.setText(equipo.getNombre());
                estadio.setText(equipo.getEstadio());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

}
