package com.example.icastillo.examenpasado;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements View.OnClickListener {

    final int REQUEST_CODE=1;
    Button btnAdd;
    MyAdapter adapterJugadores;
    ArrayList<Jugador> arrayJugadores;
    ListaJugadoresMainActivity listaJugadoresMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        arrayJugadores=new ArrayList<Jugador>();
        adapterJugadores=new MyAdapter(this, R.layout.stylejugador, R.id.nombreJugador, arrayJugadores);
        setListAdapter(adapterJugadores);

        listaJugadoresMainActivity=new ListaJugadoresMainActivity();
    }





    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, AddEditJugador.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    public void onListItemClick(ListView l, View v, int position, long id){

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Jugador jugador=null;
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                jugador=data.getParcelableExtra("jugador");
                //imagen.setImageResource(equipo.getIdImagen());
                //nombre.setText(equipo.getNombre());
                //estadio.setText(equipo.getEstadio());
                arrayJugadores.add(jugador);
                adapterJugadores.notifyDataSetChanged();
                //listaJugadoresMainActivity.getArrayListJugadoresActivity().add(jugador);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult







    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, ArrayList<T> object){
            super(c, resourceId, textId, object);
        }

        @Override
        public int getViewTypeCount(){
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position/* % 0*/;
        }


        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolderJugador viewHolderJugador=null;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();

                //if(getItemViewType(position)==0){
                    row=inflater.inflate(R.layout.stylejugador, parent, false);
                //}else{
                    //row=inflater.inflate(R.layout.styleequiposimpar, parent, false);
                //}

                viewHolderJugador=new ViewHolderJugador(row, R.id.imageJugador, R.id.nombreJugador, R.id.posicionJugador, R.id.alturaJugador, R.id.pesoJugador);
                row.setTag(viewHolderJugador);
            }else{
                viewHolderJugador=(ViewHolderJugador) row.getTag();
            }

            viewHolderJugador.getImagen().setImageResource(arrayJugadores.get(position).getIdImagen());
            viewHolderJugador.getNombre().setText(arrayJugadores.get(position).getNombre());
            viewHolderJugador.getPosicion().setText(arrayJugadores.get(position).getPosicion());
            viewHolderJugador.getAltura().setText(arrayJugadores.get(position).getAltura());
            viewHolderJugador.getPeso().setText(arrayJugadores.get(position).getPeso());

            return (row);
        }

    }




}
