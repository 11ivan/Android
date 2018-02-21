package icastillo.examen2trimestre.application.application;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import icastillo.examen2trimestre.R;
import icastillo.examen2trimestre.application.application.room.entities.Futbolista;
import icastillo.examen2trimestre.application.application.room.entities.Posicion;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioFutbolistas;
import icastillo.examen2trimestre.application.application.room.repositories.RepositorioPosiciones;

public class MainActivity extends AppCompatActivity implements FragmentBotones.OnFragmentInteractionListener,
        FragmentListado.OnFragmentInteractionListener, FragmentInsercion.OnFragmentInteractionListener {

    RepositorioFutbolistas repositorioFutbolistas;
    Futbolista f1=new Futbolista();
    Futbolista f2=new Futbolista();
    Futbolista[] futbolistas;

    RepositorioPosiciones repositorioPosiciones;
    Posicion[] posiciones;
    Posicion p1=new Posicion();
    Posicion p2=new Posicion();
    Posicion p3=new Posicion();
    Posicion p4=new Posicion();

    FragmentBotones fragmentBotones;
    FragmentListado fragmentListado;
    FragmentInsercion fragmentInsercion;

    String fragmentActual="";
    String KEY="key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositorioPosiciones=new RepositorioPosiciones(getApplication());
        //Si no hay Posiciones en la base de datos  //Asi inserta siempre  :-/
        if(repositorioPosiciones.getLiveDataPosiciones().getValue()==null || repositorioPosiciones.getLiveDataPosiciones().getValue().length==0) {
            /*p1.setNombrePosicion("Portero");
            p2.setNombrePosicion("Defensa");
            p3.setNombrePosicion("CentroCampista");
            p4.setNombrePosicion("Delantero");
            posiciones=new Posicion[4];
            posiciones[0] = p1;
            posiciones[1] = p2;
            posiciones[2] = p3;
            posiciones[3] = p4;
            repositorioPosiciones.insertPosiciones(posiciones);*/
        }

        //repositorioFutbolistas=new RepositorioFutbolistas(getApplication());
        //Inserciones Futbolistas de Prueba
        /*f1.setNombre("Pepe");
        f1.setApellidos("Castro");
        ArrayList<Integer> idPosiciones = new ArrayList<Integer>();
        idPosiciones.add(1);
        idPosiciones.add(2);
        f1.setIdPosiciones(idPosiciones);

        f2.setNombre("Yeah");
        f2.setApellidos("Yeah");
        ArrayList<Integer> idPosiciones2 = new ArrayList<Integer>();
        idPosiciones2.add(3);
        idPosiciones2.add(4);
        f2.setIdPosiciones(idPosiciones2);
        futbolistas=new Futbolista[2];
        futbolistas[0]=f1;
        futbolistas[1]=f2;
        repositorioFutbolistas.insertFutbolistas(futbolistas);*/

        fragmentBotones=new FragmentBotones();
        fragmentListado=new FragmentListado();
        fragmentInsercion=new FragmentInsercion();

        if(fragmentActual.equals("")){
            cargaFragmentBotones();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY, fragmentActual);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        fragmentActual = savedInstanceState.getString(KEY);
        cargaUltimoFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean pulsado=false;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            pulsado=true;
            //Toast.makeText(this, "atras", Toast.LENGTH_SHORT).show();
        }
        //return super.onKeyDown(keyCode, event);
        return pulsado;
    }

    public void cargaUltimoFragment(){
        switch (fragmentActual){
            case "botones":
                cargaFragmentBotones();
            break;
            case "listFutbolistas":
                cargaFragmentList();
            break;
            case "insercion":
                cargaFragmentInsercion();
            break;
        }
    }

    public void cargaFragmentBotones(){
        fragmentActual="botones";
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeMain, fragmentBotones).commit();
    }

    public void cargaFragmentList(){
        fragmentActual="listFutbolistas";
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeMain, fragmentListado).commit();
    }

    public void cargaFragmentInsercion(){
        fragmentActual="insercion";
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeMain, fragmentInsercion).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
    @Override
    public void OnButtonClick(View view) {
        switch (view.getId()){
            //Botones Fragment Botones
            case R.id.btnListarFutbolistas:
                cargaFragmentList();
            break;
            case R.id.btnInsertar:
                cargaFragmentInsercion();
            break;

            //Boton Atras Fragment Listado e Insercion
            case R.id.btnBackList:
            case R.id.btnBackInsert:
                cargaFragmentBotones();
            break;
        }
    }



}
