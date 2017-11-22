package com.example.icastillo.examenprimeraevaluacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListaProductos2 listaProductos=new ListaProductos2();
    Spinner spinner;
    Button btnVerProducto;
    ArrayAdapter<String> adapterSpinner;
    String[] arrayNombresProductos={"Camiseta", "Pantalon", "Zapato", "Mesa"};
    //ArrayList<String> arrayNombresProductos=listaProductos.getNombresProductos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=(Spinner) findViewById(R.id.spinnerProductos);
        adapterSpinner=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayNombresProductos);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        btnVerProducto=(Button) findViewById(R.id.btnVerProducto);
        btnVerProducto.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this, VerProductoActivity.class);
        intent.putExtra("nombreProducto", spinner.getSelectedItem().toString());
        startActivity(intent);
    }


}
