package com.example.icastillo.spinner;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends ListActivity  {

    Estrella[] arrayEstrellas={new Estrella("Enana amarilla", R.drawable.sol),
                               new Estrella("Enana roja", R.drawable.enanaroja),
                               new Estrella("Estrella binaria", R.drawable.binaria)};

    Planeta[] arrayPlanetas={new Planeta("Tierra", R.drawable.tierra),
                             new Planeta("Júpiter", R.drawable.jupiter),
                             new Planeta("Neptuno", R.drawable.neptuno)};

    Supernova[] arrayNovas={new Supernova("SuperNova 1", R.drawable.supernova1),
                            new Supernova("SuperNova 2", R.drawable.supernova2),
                            new Supernova("SuperNova 3", R.drawable.supernova3)};

    Object[] arrayObject={new Estrella("Enana amarilla", R.drawable.sol),
                          new Estrella("Enana roja", R.drawable.enanaroja),
                          new Estrella("Estrella binaria", R.drawable.binaria),
                          new Planeta("Tierra", R.drawable.tierra),
                          new Planeta("Júpiter", R.drawable.jupiter),
                          new Planeta("Neptuno", R.drawable.neptuno),
                          new Supernova("SuperNova 1", R.drawable.supernova1),
                          new Supernova("SuperNova 2", R.drawable.supernova2),
                          new Supernova("SuperNova 3", R.drawable.supernova3)};

    MyAdapter<Object> adapterObject;
    MyAdapter<Estrella> adapterEstrellas;
    MyAdapter<Planeta> adapterPlanetas;
    MyAdapter<Supernova> adapterNovas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapterObject=new MyAdapter<Object>(this, R.layout.stylestar, R.id.textStar, arrayObject);
        adapterEstrellas=new MyAdapter<Estrella>(this, R.layout.stylestar, R.id.textStar, arrayEstrellas);
        adapterPlanetas=new MyAdapter<Planeta>(this, R.layout.styleplanet, R.id.textPlanet, arrayPlanetas);
        adapterNovas=new MyAdapter<Supernova>(this, R.layout.stylesupernova, R.id.textSupernova, arrayNovas);
        setListAdapter(adapterObject);
        Toast.makeText(this, getListAdapter().getClass().toString(), Toast.LENGTH_LONG).show();
    }









    class MyAdapter<T> extends ArrayAdapter<T>{

        MyAdapter(Context c, int resourceId, int textId, T[] objects){
            super(c, resourceId, textId, objects);
        }

      /*  @Override
        public int getViewTypeCount(){
            return 3;
        }

        @Override
        public int getItemViewType(int position){
            int type=0;

            if (getListAdapter().getClass().getName().contains("Planeta") && getListAdapter().getClass().getName().contains("Estrella") && getListAdapter().getClass().getName().contains("Supernova")){
                type=0;
            }else if(getListAdapter().getClass().getName().contains("Planeta")){
                type=1;
            }else if(getListAdapter().getClass().getName().contains("Estrella")){
                type=2;
            }else if(getListAdapter().getClass().getName().contains("Supernova")){
                type=3;
            }

            return type;
        }


        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();




            }else{

            }


            return  row;
        }*/

    }//Fin clase Adapter


}
