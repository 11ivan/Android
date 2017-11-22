package com.example.icastillo.examenpasado;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditJugador extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher, AdapterView.OnItemSelectedListener {

    GestoraAddEditJugador gestoraAddEditJugador=new GestoraAddEditJugador();

    RadioGroup rdGroup;
    ImageButton imgButton;
    EditText nombre;
    EditText apellido;
    Spinner spinnerAltura;
    Spinner spinnerPeso;
    Button btnSave;

    ArrayAdapter<Integer> adapterSpinnerAltura;
    ArrayAdapter<String> adapterSpinnerPeso;
    String[] arrayPeso=gestoraAddEditJugador.fillArrayPeso();
    Integer[] arrayAltura=gestoraAddEditJugador.fillArrayAltura();

    Jugador jugador=new Jugador();
    final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_jugador);

        rdGroup=(RadioGroup) findViewById(R.id.radioGroup);
        rdGroup.setOnCheckedChangeListener(this);

        imgButton=(ImageButton)findViewById(R.id.btnImagen);
        imgButton.setOnClickListener(this);

        nombre=(EditText) findViewById(R.id.txtNombre);
        nombre.addTextChangedListener(this);

        apellido=(EditText)findViewById(R.id.txtApellido);
        apellido.addTextChangedListener(this);

        //Spinner altura
        spinnerAltura=(Spinner)findViewById(R.id.spinnerAltura);
        adapterSpinnerAltura=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, arrayAltura);
        adapterSpinnerAltura.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAltura.setAdapter(adapterSpinnerAltura);
        spinnerAltura.setOnItemSelectedListener(this);

        //Spinner Peso
        spinnerPeso=(Spinner) findViewById(R.id.spinnerPeso);
        adapterSpinnerPeso=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayPeso);
        adapterSpinnerPeso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeso.setAdapter(adapterSpinnerPeso);
        spinnerPeso.setOnItemSelectedListener(this);

        btnSave=(Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){

            case R.id.btnImagen:
                    intent=new Intent(this, ListaImagenesActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.btnSave:
                    modificaJugador();
                    if(compruebaDatosJugador()) {
                        intent = new Intent();
                        intent.putExtra("jugador", jugador);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }else {
                        Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }


    //Metodo para comprobar todos los datos del jugador
    public boolean compruebaDatosJugador(){
        boolean vale=false;

        if(jugador.getNombre().replace(" ", "").length()>0 && jugador.getNombre().replace(" ", "").length()>0 &&
            !jugador.getAltura().equals("") && !jugador.getPeso().equals("") && jugador.getIdImagen()!=0 ){
            vale=true;
        }

        return vale;
    }

    //Método para realizar los cambios en el jugador
    public void modificaJugador(){
        jugador.setNombre(nombre.getText().toString());
        jugador.setApellido(apellido.getText().toString());
        jugador.setPeso(arrayPeso[spinnerPeso.getSelectedItemPosition()]);
        jugador.setAltura(String.valueOf(arrayAltura[spinnerAltura.getSelectedItemPosition()]));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){

            case R.id.base:
                jugador.setPosicion("Base");
                break;

            case R.id.escolta:
                jugador.setPosicion("Escolta");
                break;

            case R.id.alero:
                jugador.setPosicion("Alero");
                break;

            case R.id.ala:
                jugador.setPosicion("Ala-Pivot");
                break;

            case R.id.pivot:
                jugador.setPosicion("Pivot");
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //Metodo para saber el item seleccionado en cada spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /*switch (parent.getId()){
            case adapterSpinnerAltura.get:
                    jugador.setAltura(String.valueOf(arrayAltura[position]));
                break;

            case R.id.spinnerPeso:
                    jugador.setPeso(arrayPeso[position]);
                break;
        }
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Jugador jugador=null;
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                //jugador=data.getParcelableExtra("jugador");
                jugador.setIdImagen(data.getIntExtra("idImagen", 0));
                imgButton.setBackgroundResource(jugador.getIdImagen());
                //imgButton.setImageResource(jugador.getIdImagen());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult



}
