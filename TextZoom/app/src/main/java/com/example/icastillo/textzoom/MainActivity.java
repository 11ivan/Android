package com.example.icastillo.textzoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnPlus;
    ImageButton btnMinus;
    EditText txtEdit;
    float fontSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEdit=(EditText) findViewById(R.id.texto);
        fontSize=txtEdit.getTextSize();
        txtEdit.setTextSize(fontSize);
        //btnPlus=(ImageButton)findViewById(R.id.plus);
        //btnMinus=(ImageButton) findViewById(R.id.minus);
       //btnPlus.setOnClickListener(this);
       // btnMinus.setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        //txtEdit=(EditText) findViewById(R.id.texto);
        switch(view.getId()){
            case R.id.plus:
                //Toast.makeText(MainActivity.this, "Mas: "+txtEdit.getTextSize(),Toast.LENGTH_SHORT).show();
                //txtEdit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, txtEdit.getTextSize()+1);
                //fontSize++;
                txtEdit.setTextSize(++fontSize);

                break;
            case R.id.minus:
                    //Toast.makeText(MainActivity.this, "Menos: "+txtEdit.getTextSize(),Toast.LENGTH_SHORT).show();
                    //txtEdit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, txtEdit.getTextSize()-1);
                //fontSize--;
                txtEdit.setTextSize(--fontSize);
                break;

            default:
                break;
        }
    }
}
