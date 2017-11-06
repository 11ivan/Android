package com.example.icastillo.autocompletetextview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity implements TextWatcher {

    AutoCompleteTextView autoComplete;


    String[] arrayPalabras={"despacito", "patito", "gusta", "yatusabepaa", "asno", "burro", "deseo", "desmembrar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoComplete=(AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoComplete.addTextChangedListener(this);
        autoComplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayPalabras));

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
}
