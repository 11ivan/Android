package com.example.ivan.galeria;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    ImageButton btnNext;
    ImageButton btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView) findViewById(R.id.image);
        btnNext=(ImageButton) findViewById(R.id.next);
        btnPrev=(ImageButton) findViewById(R.id.previous);
    }


    public void nextImage(View view){

        image.setImageDrawable(Dr);
    }

    public void previousImage(View view){


    }


}
