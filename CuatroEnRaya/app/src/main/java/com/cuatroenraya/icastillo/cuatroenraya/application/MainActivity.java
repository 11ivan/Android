package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cuatroenraya.icastillo.cuatroenraya.R;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Configuracion;
import com.cuatroenraya.icastillo.cuatroenraya.room.entities.Usuario;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioConfiguraciones;
import com.cuatroenraya.icastillo.cuatroenraya.room.repositories.RepositorioUsuarios;
import com.cuatroenraya.icastillo.cuatroenraya.viewmodels.ViewModelMainActivity;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnFragmentInteractionListener, MenuPrincipalFragment.OnFragmentInteractionListener, IntroducirNombreUsuarioFragment.OnFragmentInteractionListener {

    OptionsFragment optionsFragment=new OptionsFragment();
    MenuPrincipalFragment menuPrincipalFragment=new MenuPrincipalFragment();
    IntroducirNombreUsuarioFragment introducirNombreUsuarioFragment=new IntroducirNombreUsuarioFragment();
    String fragmentCargado="principal";
    String KEYFRAGMENT="ultimoFragment";
    Usuario[] arrayUsuarios;
    ViewModelMainActivity viewModelMainActivity;
    Usuario usuarioDeViewModel=new Usuario();
    String nombreUsuario="";
    RepositorioUsuarios repositorioUsuarios;
    Configuracion configuracion=new Configuracion();
    RepositorioConfiguraciones repositorioConfiguraciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositorioUsuarios=new RepositorioUsuarios(getApplication());
        repositorioConfiguraciones=new RepositorioConfiguraciones(getApplication());
        //usuarioLiveData=new Usuario();
        viewModelMainActivity= ViewModelProviders.of(this).get(ViewModelMainActivity.class);
        viewModelMainActivity.cargaArrayUsuarios();


        viewModelMainActivity.getUsuarioLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                Toast.makeText(getApplicationContext(), "Ha entrado en onChanged", Toast.LENGTH_LONG).show();
                usuarioDeViewModel=usuario;
                cargaFragmentCorrespondiente();//Cargamos el Fragment que corresponda
            }
        });

        try {//CACHO DE CUERNO CON PATAS!!
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Si no hay usuario pedimos un nombre de usuario
        if(viewModelMainActivity.arrayUsuarios.length==0){
            cargaFragmentNombreUsuario();
        }else {
            //Recuperamos el usuario
            viewModelMainActivity.cargaUsuario();
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

    public void cargaFragmentCorrespondiente(){
        switch (fragmentCargado){
            case "principal":
                cargaFragmentPrincipal();
                break;
            case "opciones":
                cargaFragmentOpciones();
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

                usuarioDeViewModel.setNombre(nombreUsuario);
                Usuario[] usuarios={usuarioDeViewModel};
                viewModelMainActivity.insertUsuarios(usuarios);//Insertamos el usuario en la base de datos

                //El proximo fragment a cargar cuando entre en onchaged debe ser el principal
                fragmentCargado="principal";

                //Cargamos en el view model el usuario insertado
                viewModelMainActivity.cargaUsuario();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Insertamos una configuracion por defecto para el usuario
                configuracion.setIdUsuario(usuarioDeViewModel.getId());
                configuracion.setTipoTablero(R.drawable.tableroaluminio);
                repositorioConfiguraciones.insertConfiguracion(configuracion);

                //Cargamos el Fragment Principal
                //cargaFragmentPrincipal(); //Ahora lo cargamos en el metodo onChange del ViewModel
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
