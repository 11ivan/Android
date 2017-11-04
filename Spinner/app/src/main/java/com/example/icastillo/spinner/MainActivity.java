package com.example.icastillo.spinner;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends ListActivity implements AdapterView.OnItemSelectedListener {

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

    String[] arrayCadena={"Planetas", "Estrellas", "Supernovas"};

    MyAdapter<Object> adapterObject;
    MyAdapter<Estrella> adapterEstrellas;
    MyAdapter<Planeta> adapterPlanetas;
    MyAdapter<Supernova> adapterNovas;

    Object[] arrayAdapters={adapterEstrellas, adapterPlanetas, adapterNovas};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterCadenas=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayCadena);
        adapterCadenas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCadenas);
        adapterObject=new MyAdapter<Object>(this, R.layout.stylestar, R.id.textStar, arrayObject);
        adapterEstrellas=new MyAdapter<Estrella>(this, R.layout.stylestar, R.id.textStar, arrayEstrellas);
        adapterPlanetas=new MyAdapter<Planeta>(this, R.layout.styleplanet, R.id.textPlanet, arrayPlanetas);
        adapterNovas=new MyAdapter<Supernova>(this, R.layout.stylesupernova, R.id.textSupernova, arrayNovas);
        setListAdapter(adapterObject);
        //Toast.makeText(this, getListAdapter().getClass().toString(), Toast.LENGTH_LONG).show();
    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        //setListAdapter((ArrayAdapter)arrayAdapters[position]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //setListAdapter(adapterPlanetas);
    }






    class MyAdapter<T> extends ArrayAdapter<T>{

        MyAdapter(Context c, int resourceId, int textId, T[] objects){
            super(c, resourceId, textId, objects);
        }

        @Override
        public int getViewTypeCount(){
            return 3;
        }

        @Override
        public int getItemViewType(int position){
            int type=0;

            /*if (getListAdapter().getClass().getName().contains("Planeta") && getListAdapter().getClass().getName().contains("Estrella") && getListAdapter().getClass().getName().contains("Supernova")){
                type=0;
            }else if(getListAdapter().getClass().getName().contains("Planeta")){
                type=1;
            }else if(getListAdapter().getClass().getName().contains("Estrella")){
                type=2;
            }else if(getListAdapter().getClass().getName().contains("Supernova")){
                type=3;
            }*/
            if(arrayObject[position] instanceof Planeta){
                type=1;
            }else if(arrayObject[position] instanceof Estrella){
                type=2;
            }else if(arrayObject[position] instanceof Supernova){
                type=3;
            }
            return type;
        }


        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolder viewHolder=null;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();

                switch (getItemViewType(position)){
                    /*case 0:
                            row=inflater.inflate(R.layout.styleplanet, parent, false);
                            viewHolder=new ViewHolder(row, R.id.imagePlanet, R.id.textPlanet);
                        break;*/

                    case 1:
                             row=inflater.inflate(R.layout.styleplanet, parent, false);
                             viewHolder=new ViewHolder(row, R.id.imagePlanet, R.id.textPlanet);

                        break;

                    case 2:
                            row=inflater.inflate(R.layout.stylestar, parent, false);
                            viewHolder=new ViewHolder(row, R.id.imageStar, R.id.textStar);
                        break;

                    case 3:
                            row=inflater.inflate(R.layout.stylesupernova, parent, false);
                            viewHolder=new ViewHolder(row, R.id.imageSupernova, R.id.textSupernova);
                        break;

                }

                row.setTag(viewHolder);

            }else{
                viewHolder=(ViewHolder) row.getTag();
            }

            switch (getItemViewType(position)){
                /*case 0:
                    Planeta planeta=new Planeta(arrayPlanetas[position]);
                    viewHolder.getImageView().setImageResource(planeta.getIdImagen());
                    viewHolder.getTextView().setText(planeta.getNombre());
                    break;*/

                case 1:
                    Planeta planeta1=new Planeta(arrayPlanetas[position]);
                    viewHolder.getImageView().setImageResource(planeta1.getIdImagen());
                    viewHolder.getTextView().setText(planeta1.getNombre());

                    break;

                case 2:
                    Estrella estrella=new Estrella(arrayEstrellas[position]);
                    viewHolder.getTextView().setText(estrella.getNombre());
                    viewHolder.getImageView().setImageResource(estrella.getIdImagen());
                    break;

                case 3:
                    Supernova nova=new Supernova(arrayNovas[position]);
                    viewHolder.getImageView().setImageResource(nova.getIdImagen());
                    viewHolder.getTextView().setText(nova.getNombre());
                    break;
            }

            return  row;
        }

    }//Fin clase Adapter


}
