package com.example.ivan.galeria;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static com.example.ivan.galeria.R.drawable.a1;

public class MainActivity extends AppCompatActivity {

    ImageView imgTemp;
    ImageView img1;
    ImageButton btnNext;
    ImageButton btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1=(ImageView) findViewById(R.id.image);
        imgTemp=(ImageView) findViewById(R.id.image);
        btnNext=(ImageButton) findViewById(R.id.next);
        btnPrev=(ImageButton) findViewById(R.id.previous);
    }


    public void nextImage(View view){
        switch (imgTemp.getId()){

            case R.id.image:
                    img1.setImageResource(R.drawable.a2);
                    imgTemp.setId(R.id.image2);
                break;

            case R.id.image2:
                    img1.setImageResource(R.drawable.a3);
                    imgTemp.setId(R.id.image3);
                break;

            case R.id.image3:
                    img1.setImageResource(R.drawable.a1);
                    imgTemp.setId(R.id.image);
                break;
        }
    }

    public void previousImage(View view){
        switch (imgTemp.getId()){

            case R.id.image:
                img1.setImageResource(R.drawable.a3);
                imgTemp.setId(R.id.image3);
                break;

            case R.id.image2:
                img1.setImageResource(R.drawable.a1);
                imgTemp.setId(R.id.image);
                break;

            case R.id.image3:
                img1.setImageResource(R.drawable.a2);
                imgTemp.setId(R.id.image2);
                break;
        }
    }


}
