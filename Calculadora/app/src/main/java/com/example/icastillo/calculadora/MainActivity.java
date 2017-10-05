package com.example.icastillo.calculadora;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup rdGroup;
    //RadioButton rdBtn1, rdBtn2,rdBtn3,rdBtn4;
    TextView txtView;
    EditText txtEdit1, txtEdit2;
    Util util=new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdGroup=(RadioGroup)findViewById(R.id.radioGr);
        rdGroup.clearCheck();
       // rdBtn1=(RadioButton)findViewById(R.id.suma);
       // rdBtn2=(RadioButton)findViewById(R.id.resta);
       // rdBtn3=(RadioButton)findViewById(R.id.divide);
       // rdBtn4=(RadioButton)findViewById(R.id.multiplica);
        rdGroup.setOnCheckedChangeListener(this);
        //rdBtn1.setOnCheckedChangeListener(this);
        txtView=(TextView)findViewById(R.id.resultado);
        txtEdit1=(EditText)findViewById(R.id.n1);
        txtEdit2=(EditText)findViewById(R.id.n2);
        //txtEdit2.addTextChangedListener();
    }


    /*
    * Proposito: Segun el radioButton pulsado
    * Prototipo:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    @Override
    public void onCheckedChanged(RadioGroup rd, int idBtnRadio){

        double n1=Double.parseDouble(txtEdit1.getText().toString());
        double n2=Double.parseDouble(txtEdit2.getText().toString());

        //Toast.makeText(MainActivity.this, "Ha entrado", Toast.LENGTH_SHORT).show();

        switch (idBtnRadio){
            case R.id.suma:
                    txtView.setText(util.suma(n1, n2).toString());
                break;

            case R.id.resta:
                txtView.setText(util.resta(n1, n2).toString());
                break;

            case R.id.divide:
                if(util.divide(n1, n2)>0){
                    txtView.setText(util.divide(n1, n2).toString());
                }else{
                    txtView.setText("Error división entre 0");
                }
                break;

            case R.id.multiplica:
                txtView.setText(util.multiplica(n1, n2).toString());
                break;
        }
    }







}
