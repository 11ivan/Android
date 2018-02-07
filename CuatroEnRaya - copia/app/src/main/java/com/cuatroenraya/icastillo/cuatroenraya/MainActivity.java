package com.cuatroenraya.icastillo.cuatroenraya;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnFragmentInteractionListener, MenuPrincipalFragment.OnFragmentInteractionListener {

    Button btnPlay;
    Button btnOptions;
    OptionsFragment optionsFragment=new OptionsFragment();
    MenuPrincipalFragment menuPrincipalFragment=new MenuPrincipalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargaFragmentPrincipal();

        /*btnPlay=(Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnOptions=(Button) findViewById(R.id.btnOptions);
        btnOptions.setOnClickListener(this);*/



        //Obtener ancho y alto el pixels para ajustar las imagenes
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        */
    }


    /*@Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnPlay:
                    intent=new Intent(this, GameActivity.class);
                    startActivity(intent);
            break;

            case R.id.btnOptions:
                //intent=new Intent(this, GameActivity.class);
                //Metodo para iniciar Fragment de opciones
                cargaFragmentOpciones();
            break;

        }

    }*/


    public void startActivityGame(){
        Intent intent=new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void cargaFragmentOpciones(){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.relativeMainActivity, optionsFragment);
        fragmentTransaction.commit();
    }

    public void cargaFragmentPrincipal(){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.relativeMainActivity, menuPrincipalFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void ClickListener(View v) {
        FragmentTransaction fragmentTransaction;
        switch (v.getId()){

            //Casos del Fragment opciones
            case R.id.btnBackOpciones:
                fragmentTransaction=getFragmentManager().beginTransaction();
                //OptionsFragment optionsFragment=new OptionsFragment();
                fragmentTransaction.replace(R.id.relativeMainActivity, menuPrincipalFragment);
                fragmentTransaction.commit();
                break;

            //Casos del Fragment del menu principal
            case R.id.btnPlay:
                    startActivityGame();
                break;

            case R.id.btnOptions:
                fragmentTransaction=getFragmentManager().beginTransaction();
                //OptionsFragment optionsFragment=new OptionsFragment();
                fragmentTransaction.replace(R.id.relativeMainActivity, optionsFragment);
                fragmentTransaction.commit();
                break;
        }
    }




}
