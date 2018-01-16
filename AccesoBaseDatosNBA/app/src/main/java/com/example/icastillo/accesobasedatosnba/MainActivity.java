package com.example.icastillo.accesobasedatosnba;

import android.app.Activity;
import android.app.ListActivity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Equipo[] equipos={new Equipo("Boston Celtics", "TD Garden"),
            new Equipo("Los Angeles Lakers", "Staples Center"),
            new Equipo("New York Knicks", "Madison Square Garden"),
            new Equipo("Chicago Bulls", "United Center"),
            new Equipo("Miami Heat", "AmericanAirlines Arena"),
            new Equipo("Dallas Maverick", "American Airlines Center"),
            new Equipo("Denver Nugget", "Pepsi Center"),
            new Equipo("Sacramento Kings", "Golden 1 Center")};*/

    Button btnVerEquipos;
    MyAdapter<Equipo> adapterEquipos;
    Equipo[] equiposDataBase;
    ListView listView;
    VMMainActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*AppDataBase.getDataBase(this).
                equipoDAO().insertEquipos(equipos);*/

        /*final VMMainActivity viewModel = ViewModelProviders.of((FragmentActivity) getApplicationContext()).get(VMMainActivity.class);
        viewModel.userLiveData.observer(this, new Observer() {
            @Override
            public void onChanged(@Nullable Equipo data) {
                // update ui.
            }
        });*/

        listView=(ListView) findViewById(R.id.lista);

        //equiposDataBase=AppDataBase.getDataBase(this).equipoDAO().getEquipos();


        viewModel = ViewModelProviders.of(this).get(VMMainActivity.class);

        //viewModel.cargaLista(getApplication());

        //adapterEquipos=new MyAdapter<Equipo>(this, R.layout.styleequipos, R.id.idEquipo, viewModel.equiposLiveData.getValue());

        viewModel.equiposLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                /*if(adapterEquipos!=null) {
                    listView.setAdapter(adapterEquipos);
                }*/
                if(viewModel.equiposLiveData.getValue().length>0) {
                    adapterEquipos = new MyAdapter<Equipo>(getApplicationContext(), R.layout.styleequipos, R.id.idEquipo, viewModel.equiposLiveData.getValue());
                    listView.setAdapter(adapterEquipos);
                }
            }
        });

        btnVerEquipos=(Button) findViewById(R.id.btnEquipos);
        btnVerEquipos.setOnClickListener(this);

        //setListAdapter(adapterEquipos);
    }



    @Override
    public void onClick(View view) {
        /*Intent intent=new Intent(this, ListaEquipos.class);
        startActivity(intent);*/
        viewModel.cargaLista(getApplication());
        /*if(viewModel.equiposLiveData.getValue().length>0) {
            adapterEquipos = new MyAdapter<Equipo>(this, R.layout.styleequipos, R.id.idEquipo, viewModel.equiposLiveData.getValue());
            listView.setAdapter(adapterEquipos);
        }*/

    }


    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, T[] object){
            super(c, resourceId, textId, object);
        }

        @Override
        public int getViewTypeCount(){
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolder viewHolder=null;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();

                row=inflater.inflate(R.layout.styleequipos, parent, false);

                viewHolder=new ViewHolder(row, R.id.idNombre, R.id.idEstadio, R.id.idEquipo);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) row.getTag();
            }

            viewHolder.getId().setText(String.valueOf(viewModel.equiposLiveData.getValue()[position].get_id()));
            viewHolder.getNombre().setText(viewModel.equiposLiveData.getValue()[position].get_nombre());
            viewHolder.getEstadio().setText(viewModel.equiposLiveData.getValue()[position].get_estadio());

            return (row);
        }

    }

}
