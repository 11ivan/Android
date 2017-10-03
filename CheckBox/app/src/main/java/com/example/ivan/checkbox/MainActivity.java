package com.example.ivan.checkbox;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText txtEdit;
    CheckBox negrita;
    CheckBox masTamano;
    CheckBox menosTamano;
    CheckBox colorRojo;
    Float tamanoLetra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //spanString = new SpannableString(tempString);
        //spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        txtEdit=(EditText)findViewById(R.id.txtEdit);
        tamanoLetra=txtEdit.getTextSize();
        txtEdit.setTextSize(tamanoLetra);
        negrita=(CheckBox)findViewById(R.id.bold);
        masTamano=(CheckBox)findViewById(R.id.moreSize);
        menosTamano=(CheckBox)findViewById(R.id.lessSize);
        colorRojo=(CheckBox)findViewById(R.id.red);

        negrita.setOnCheckedChangeListener(this);
        masTamano.setOnCheckedChangeListener(this);
        menosTamano.setOnCheckedChangeListener(this);
        colorRojo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        SpannableString spanString;
        String tempString="";

        switch (buttonView.getId()){

            case R.id.bold:
                    if(isChecked) {
                        tempString=txtEdit.getText().toString();
                        spanString = new SpannableString(tempString);
                        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
                        txtEdit.setText(spanString);
                    }else{
                        tempString=txtEdit.getText().toString();
                        txtEdit.setText(tempString);
                    }
                    /*if(isChecked) {
                        txtEdit.setText(Html.fromHtml("<b>"+txtEdit.getText().toString()+"</b>"));
                    }else{
                        txtEdit.setText(txtEdit.getText().toString());
                    }*/
                break;

            case R.id.moreSize:
                    if(isChecked && menosTamano.isChecked()){
                        txtEdit.setTextSize(30);
                    }else if(isChecked){
                        txtEdit.setTextSize(70);
                    }else if(menosTamano.isChecked()){
                        txtEdit.setTextSize(3);
                    }else{
                        txtEdit.setTextSize(tamanoLetra);
                     }
                break;

            case R.id.lessSize:
                    if(isChecked && masTamano.isChecked()){
                        txtEdit.setTextSize(30);
                    }else if(isChecked){
                        txtEdit.setTextSize(3);
                    }else if(masTamano.isChecked()){
                        txtEdit.setTextSize(70);
                    }else{
                        txtEdit.setTextSize(tamanoLetra);
                    }
                break;

            case R.id.red:

                //((isChecked==true) ? (txtEdit.setTextColor(Color.RED)):(txtEdit.setTextColor(Color.BLACK)));
               // txtEdit.setTextColor((isChecked)?(txtEdit.setTextColor(Color.RED)):(txtEdit.setTextColor(Color.BLACK)));
                   if(isChecked) {
                        txtEdit.setTextColor(Color.RED);
                    }else{
                        txtEdit.setTextColor(Color.BLACK);
                    }
                break;

        }
    }









}




/*
String tuString = "Hola <b>mundo</b>, me llamo <b>Pepe</b">;
tuTextView.setText(Html.fromHtml(tuString));
* */


/*

    cb=(CheckBox)findViewById(R.id.check);
    cb.setOnCheckedChangeListener(this);


  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (isChecked) {
      cb.setText("This checkbox is: checked");
    }
    else {
      cb.setText("This checkbox is: unchecked");
    }
  }
*/