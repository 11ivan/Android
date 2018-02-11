package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.app.FragmentTransaction;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;
import com.cuatroenraya.icastillo.cuatroenraya.viewmodels.ViewModelMainActivity;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnFragmentInteractionListener, MenuPrincipalFragment.OnFragmentInteractionListener, IntroducirNombreUsuarioFragment.OnFragmentInteractionListener {

    OptionsFragment optionsFragment=new OptionsFragment();
    MenuPrincipalFragment menuPrincipalFragment=new MenuPrincipalFragment();
    IntroducirNombreUsuarioFragment introducirNombreUsuarioFragment=new IntroducirNombreUsuarioFragment();
    String fragmentCargado="";
    String KEYFRAGMENT="ultimoFragment";
    LiveData<Usuario[]> liveDataUsuarios;
    ViewModelMainActivity viewModelMainActivity;
    Usuario usuario=new Usuario();
    String nombreUsuario="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //usuarioLiveData=new Usuario();
        viewModelMainActivity=new ViewModelMainActivity(getApplication());

        //Comprobar si hay algun usuario en la base de datos
        //Si hay usuario mostrar mensaje de bienvenida(Ya que será el único usuario en la base de datos)
        //Si no hay usuario Cargamos el Fragment para pedir un nombre de usuario
        liveDataUsuarios=viewModelMainActivity.getUsuariosLiveData();
        if(viewModelMainActivity.getUsuariosLiveData().getValue()==null){
            cargaFragmentNombreUsuario();
        }else {
            //Recuperamos el usuario
            viewModelMainActivity.cargaUsuario();
            //Cargamos el Fragment pricipal
            cargaFragmentPrincipal();
        }

        //Obtener ancho y alto el pixels para ajustar las imagenes
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        */
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEYFRAGMENT, fragmentCargado);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        fragmentCargado = savedInstanceState.getString(KEYFRAGMENT);
        switch (fragmentCargado){
            case "opciones":
                cargaFragmentOpciones();
            break;
            case "nombreusuario":
                cargaFragmentNombreUsuario();
                break;
        }
    }

    //Inicia la actividad del juego
    public void startActivityGame(){
        Intent intent=new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    //Carga el fragment de las opciones
    public void cargaFragmentOpciones(){
        fragmentCargado="opciones";
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.relativeMainActivity, optionsFragment);
        fragmentTransaction.commit();
    }

    public void cargaFragmentPrincipal(){
        fragmentCargado="principal";
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.relativeMainActivity, menuPrincipalFragment);
        fragmentTransaction.commit();
    }

    public void cargaFragmentNombreUsuario(){
        fragmentCargado="nombreusuario";
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.relativeMainActivity, introducirNombreUsuarioFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void ClickListener(View v) {
        switch (v.getId()){
            //Casos del Fragment opciones
            case R.id.btnBackOpciones:
                    //Comprobar y guardar los datos del fragment de opciones
                    cargaFragmentPrincipal();
            break;

            //Casos del Fragment del menu principal
            case R.id.btnPlay:
                    startActivityGame();
            break;
            case R.id.btnOptions:
                    cargaFragmentOpciones();
            break;

            //Casos Fragment Introducir Nombre Usuario
            case R.id.btnContinuarFragmentNombreUsuario:
                    usuario.setNombre(nombreUsuario);
                    viewModelMainActivity.insertUsuario(usuario);
                    //Cargamos el Fragment Principal
                    cargaFragmentPrincipal();
            break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean pulsado=false;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            pulsado=true;
            Toast.makeText(this, "atras", Toast.LENGTH_SHORT).show();
        }
        //return super.onKeyDown(keyCode, event);
        return pulsado;
    }


}
