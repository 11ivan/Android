package com.example.icastillo.listanbaactivityforresult;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListaEquiposActivity extends ListActivity {

    Equipo[] arrayEquipos={new Equipo("Boston Celtics", "TD Garden", R.drawable.celtics),
            new Equipo("Los Angeles Lakers", "Staples Center", R.drawable.lakers),
            new Equipo("New York Knicks", "Madison Square Garden", R.drawable.knicks),
            new Equipo("Chicago Bulls", "United Center", R.drawable.bulls),
            new Equipo("Miami Heat", "AmericanAirlines Arena", R.drawable.miamiheat),
            new Equipo("Dallas Maverick", "American Airlines Center", R.drawable.dallasmaverick),
            new Equipo("Denver Nugget", "Pepsi Center", R.drawable.denvernuggets),
            new Equipo("Sacramento Kings", "Golden 1 Center", R.drawable.sacramento)};

    MyAdapter<Equipo> adapterEquipos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipos);
        adapterEquipos=new MyAdapter<Equipo>(this, R.layout.styleequiposimpar, R.id.idNombre, arrayEquipos);
        setListAdapter(adapterEquipos);
    }


    public void onListItemClick(ListView l, View v, int position, long id){
        Intent intent=new Intent();
        intent.putExtra("equipo", arrayEquipos[position]);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    class MyAdapter<T> extends ArrayAdapter<T> {

        MyAdapter(Context c, int resourceId, int textId, T[] object){
            super(c, resourceId, textId, object);
        }

        @Override
        public int getViewTypeCount(){
            return 2;
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

                if(getItemViewType(position)==0){
                    row=inflater.inflate(R.layout.styleequipospar, parent, false);
                }else{
                    row=inflater.inflate(R.layout.styleequiposimpar, parent, false);
                }

                viewHolder=new ViewHolder(row, R.id.idImage, R.id.idNombre, R.id.idEstadio);
                row.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) row.getTag();
            }

            viewHolder.getImage().setImageResource(arrayEquipos[position].getIdImagen());
            viewHolder.getNombre().setText(arrayEquipos[position].getNombre());
            viewHolder.getEstadio().setText(arrayEquipos[position].getEstadio());

            return (row);
        }

    }


}
