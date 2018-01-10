package com.example.icastillo.accesobasedatosnba;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListaEquipos extends ListActivity {

    MyAdapter<Equipo> adapterEquipos;
    Equipo[] equiposDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipos);

        equiposDataBase=AppDataBase.getDataBase(this).equipoDAO().getEquipos();
        adapterEquipos=new MyAdapter<Equipo>(this, R.layout.styleequipos, R.id.idEquipo, equiposDataBase );
        setListAdapter(adapterEquipos);
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

            viewHolder.getId().setText(String.valueOf(equiposDataBase[position].get_id()));
            viewHolder.getNombre().setText(equiposDataBase[position].get_nombre());
            viewHolder.getEstadio().setText(equiposDataBase[position].get_estadio());

            return (row);
        }

    }


}
