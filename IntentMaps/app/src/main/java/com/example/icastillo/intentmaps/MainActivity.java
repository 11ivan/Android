package com.example.icastillo.intentmaps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    String[] arrayCiudades={"Elija la opción","Roma", "Amsterdan", "Amazonas", "Himalaya", "Hawai"};
    ArrayAdapter<String> adapterCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        adapterCiudades=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayCiudades);
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCiudades);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        Boolean inicia=false;
        //intent.setData(Uri.parse("geo:0, 0?q="));
        switch (arrayCiudades[i]){

            case "Roma":
                    intent.setData(Uri.parse("geo:0, 0?q=Roma"));
                    inicia=true;
                break;

            case "Amsterdan":
                intent.setData(Uri.parse("geo:0, 0?q=Amsterdan"));
                inicia=true;
                break;

            case "Amazonas":
                intent.setData(Uri.parse("geo:0, 0?q=Amazonas"));
                inicia=true;
                break;

            case "Himalaya":
                intent.setData(Uri.parse("geo:0, 0?q=Himalaya"));
                inicia=true;
                break;

            case "Hawai":
                intent.setData(Uri.parse("geo:0, 0?q=Hawai"));
                inicia=true;
                break;
        }
        if(inicia) {
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
