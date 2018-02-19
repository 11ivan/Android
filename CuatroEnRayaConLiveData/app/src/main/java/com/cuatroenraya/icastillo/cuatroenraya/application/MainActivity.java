package com.cuatroenraya.icastillo.cuatroenraya.application;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Handler;
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

    OptionsFragment optionsFragment;
    MenuPrincipalFragment menuPrincipalFragment;
    IntroducirNombreUsuarioFragment introducirNombreUsuarioFragment=new IntroducirNombreUsuarioFragment();
    String fragmentCargado="principal";
    String KEYFRAGMENT="ultimoFragment";
    Usuario[] arrayUsuarios;
    ViewModelMainActivity viewModelMainActivity;
    Usuario usuarioDeViewModel=new Usuario();//Dato Bindeado al ViewModel
    String nombreUsuario="";
    RepositorioUsuarios repositorioUsuarios;
    Configuracion configuracionDeUsuario=new Configuracion();
    RepositorioConfiguraciones repositorioConfiguraciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositorioUsuarios=new RepositorioUsuarios(getApplication());
        repositorioConfiguraciones=new RepositorioConfiguraciones(getApplication());

        viewModelMainActivity = ViewModelProviders.of(this).get(ViewModelMainActivity.class);//Ya está inicializado usuarioLiveData

        viewModelMainActivity.getUsuarioLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                //Toast.makeText(getApplicationContext(), "Ha entrado en onChanged", Toast.LENGTH_LONG).show();
                usuarioDeViewModel=usuario;
                //configuracionDeUsuario=repositorioConfiguraciones.getConfiguracionUsuario(usuarioDeViewModel.getId());//Cargamos la configuaración  PUEDE SER NULL
                //Si después de cargar el usuario es null pedimos el nombre
                if(usuarioDeViewModel==null){
                    cargaFragmentNombreUsuario();
                }else {//Sino cargamos el Fragment que corresponda
                    configuracionDeUsuario=repositorioConfiguraciones.getConfiguracionUsuario(usuarioDeViewModel.getId());//Cargamos la configuaración  PUEDE SER NULL >> YA NO DEBERIA
                    cargaFragmentCorrespondiente();
                }
            }
        });

        menuPrincipalFragment=new MenuPrincipalFragment();
        optionsFragment=new OptionsFragment();

        //Recuperamos el usuario
        viewModelMainActivity.cargaUsuario();//De aquí tira para el metodo onChanged

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
        outState.putString(Keys.KEYFRAGMENT, fragmentCargado);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        fragmentCargado = savedInstanceState.getString(Keys.KEYFRAGMENT);
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
        intent.putExtra(Keys.ID_USUARIO, usuarioDeViewModel.getId());
        intent.putExtra(Keys.NOMBRE_USUARIO, usuarioDeViewModel.getNombre());
        intent.putExtra(Keys.TABLERO, configuracionDeUsuario.getTipoTablero());
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

    public void goToPuntuacionesActivity(){
        Intent intent = new Intent(this, PuntuacionesActivity.class);
        startActivity(intent);
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

            /*case R.id.btnActualizaConfiguracion:  //Lo gestionamos desde el fragment
                    repositorioConfiguraciones.updateConfiguracion();
                break;*/

            //Casos del Fragment del menu principal
            case R.id.btnPlay:
                    startActivityGame();
            break;
            case R.id.btnOptions:
                    cargaFragmentOpciones();
            break;
            case R.id.btnPuntuaciones:
                    goToPuntuacionesActivity();
            break;

            //Casos Fragment Introducir Nombre Usuario
            case R.id.btnContinuarFragmentNombreUsuario:
                usuarioDeViewModel=new Usuario();
                usuarioDeViewModel.setNombre(nombreUsuario);
                viewModelMainActivity.insertUsuario(usuarioDeViewModel);//Insertamos el usuario en la base de datos

                //El proximo fragment a cargar cuando entre en onchaged debe ser el principal
                fragmentCargado="principal";

                //Cargamos en el view model el usuario insertado
                viewModelMainActivity.cargaUsuario();//Hay que cargarlo para obtener el id, es necesario para insertar la configuracion

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        configuracionDeUsuario=new Configuracion();
                        configuracionDeUsuario.setIdUsuario(usuarioDeViewModel.getId());
                        configuracionDeUsuario.setTipoTablero(R.drawable.tableroaluminio);
                        repositorioConfiguraciones.insertConfiguracion(configuracionDeUsuario);
                    }
                }, 500);
                //Insertamos una configuracion por defecto para el usuario          //¿INSERTA UNA CONFIGURACION POR DEFECTO?>>(NO)
                /*configuracion.setIdUsuario(usuarioDeViewModel.getId());//El id del usuario debe ser 1
                configuracion.setTipoTablero(R.drawable.tableroaluminio);
                repositorioConfiguraciones.insertConfiguracion(configuracion);*/
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
