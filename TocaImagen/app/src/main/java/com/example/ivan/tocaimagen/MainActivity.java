package com.example.ivan.tocaimagen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    int posImagen=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView=(ImageView)findViewById(R.id.imagen);
    }

    public void changeImage(View view){
        switch (posImagen) {
            case 1:
                    imgView.setImageResource(R.drawable.nosound);
                    //imgView.setBackgroundResource(R.drawable.nosound);
                    posImagen=2;
                break;
            case 2:
                    imgView.setImageResource(R.drawable.sound);
                    //imgView.setBackgroundResource(R.drawable.sound);
                    posImagen=1;
                break;

        }
    }





}
