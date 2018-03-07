package com.example.ivan.appsuma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mitextview;
    String resultCadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mitextview=(TextView)findViewById(R.id.resultado);

    }




    public void suma(View view){
        int result=0;
        EditText text1=(EditText)findViewById(R.id.numero1);
        EditText text2=(EditText)findViewById(R.id.numero2);
        result=Integer.parseInt(text1.getText().toString())+Integer.parseInt(text2.getText().toString());
        mitextview.setText(String.valueOf(result));
    }








}
