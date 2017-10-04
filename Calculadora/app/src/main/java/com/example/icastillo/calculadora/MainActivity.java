package com.example.icastillo.calculadora;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup rdGroup;
    //RadioButton rdBtn1, rdBtn2,rdBtn3,rdBtn4;
    TextView txtView;
    EditText txtEdit1, txtEdit2;

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
    }

    @Override
    public void onCheckedChanged(RadioGroup rd, int idBtnRadio){
        //double n1, n2, resultado;

        switch (idBtnRadio){
            case R.id.suma:
                rdGroup.clearCheck();
                if(findViewById(R.id.suma).isActivated()) {
                    txtView.setText(String.valueOf((Double.parseDouble(txtEdit1.getText().toString())) + (Double.parseDouble(txtEdit2.getText().toString()))));
                }else{
                    findViewById(R.id.suma).setActivated(false);
                }
                break;

            case R.id.resta:
                rdGroup.clearCheck();
                    txtView.setText(String.valueOf( (Double.parseDouble(txtEdit1.getText().toString())) - (Double.parseDouble(txtEdit2.getText().toString())) ));
                break;

            case R.id.divide:
                rdGroup.clearCheck();
                if(Integer.parseInt(txtEdit2.getText().toString())>0){
                    txtView.setText(String.valueOf((Double.parseDouble(txtEdit1.getText().toString()))/(Double.parseDouble(txtEdit2.getText().toString()))));
                }
                break;

            case R.id.multiplica:
                rdGroup.clearCheck();
                    txtView.setText(String.valueOf((Double.parseDouble(txtEdit1.getText().toString()))*(Double.parseDouble(txtEdit2.getText().toString()))));
                break;
        }




    }







}
