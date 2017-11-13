package com.example.icastillo.lanzadoractivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GaleriaActivity extends AppCompatActivity {

    ImageView img1;
    ImageButton btnNext;
    ImageButton btnPrev;
    int posImage=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        img1=(ImageView) findViewById(R.id.image);
        btnNext=(ImageButton) findViewById(R.id.next);
        btnPrev=(ImageButton) findViewById(R.id.previous);

    }

    public void nextImage(View view){
        switch (posImage){
            case 1:
                img1.setImageResource(R.drawable.a2);
                posImage=2;
                break;

            case 2:
                img1.setImageResource(R.drawable.a3);
                posImage=3;
                break;

            case 3:
                img1.setImageResource(R.drawable.a1);
                posImage=1;
                break;
        }
    }

    public void previousImage(View view){
        switch (posImage){
            case 1:
                img1.setImageResource(R.drawable.a3);
                posImage=3;
                break;

            case 2:
                img1.setImageResource(R.drawable.a1);
                posImage=1;
                break;

            case 3:
                img1.setImageResource(R.drawable.a2);
                posImage=2;
                break;
        }
    }

}
