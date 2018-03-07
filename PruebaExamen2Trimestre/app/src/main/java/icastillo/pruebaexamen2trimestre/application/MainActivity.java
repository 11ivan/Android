package icastillo.pruebaexamen2trimestre.application;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.Fragment;
import icastillo.pruebaexamen2trimestre.R;
import icastillo.pruebaexamen2trimestre.room.entities.Persona;
import icastillo.pruebaexamen2trimestre.room.repositorios.RepositorioPersonas;
import icastillo.pruebaexamen2trimestre.viewmodels.ViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragmentListPersonas.OnFragmentInteractionListener {

    Persona p1=new Persona();
    Persona p2=new Persona();
    Persona p3=new Persona();
    Persona p4=new Persona();
    Persona[] personas=new Persona[4];

    //
    FragmentListPersonas fragmentListPersonas;
    //ViewModel viewModel;
    //Persona[] arrayPersonas;
    RepositorioPersonas repositorioPersonas;

    //Variables a XML
    Button btnListarPersonas;

    String fragmentActual="";
    String KEY="key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
       /* p1.setNombre("Pepe");
        p1.setApellidos("Lopez");
        p2.setNombre("OOO");
        p2.setApellidos("TIO");
        p3.setNombre("NOOOOO");
        p3.setApellidos("SIIII");
        p4.setNombre("Bueno");
        p4.setApellidos("Vale");

        personas[0]=p1;
        personas[1]=p2;
        personas[2]=p3;
        personas[3]=p4;*/


        repositorioPersonas=new RepositorioPersonas(getApplication());
        //repositorioPersonas.insertPersonas(personas);

        fragmentListPersonas=new FragmentListPersonas();

        //Boton Listar Personas
        btnListarPersonas=(Button) findViewById(R.id.btnListarPersonas);
        btnListarPersonas.setOnClickListener(this);

        /*//ViewModel
        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getLiveDataPersonas().observe(this, new Observer<Persona[]>() {
            @Override
            public void onChanged(@Nullable Persona[] personas) {
                if(personas!=null){
                    arrayPersonas=personas;
                    if(!fragmentActual.equals("")) {
                        //Cargar el Fragment que habia
                        cargaFragmentListPersonas();
                    }
                }
            }
        });*/

       // viewModel.cargaPersonas();


    }//Fin onCreate



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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnListarPersonas:
                //Cargamos las personas
                //viewModel.cargaPersonas();

                //Vamos al Fragment del listado de Personas
                cargaFragmentListPersonas();
            break;
            case R.id.btnListarCoches:

            break;

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void listItemClickListener(View view, int position) {

    }

    @Override
    public void buttonClickListener(View view) {

    }

    public void cargaFragmentListPersonas(){
        fragmentActual="listPersonas";
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeMainActivity,  fragmentListPersonas).commit();
    }



}
