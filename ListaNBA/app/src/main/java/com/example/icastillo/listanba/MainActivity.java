package com.example.icastillo.listanba;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class MainActivity extends ListActivity implements TextWatcher, View.OnClickListener {

    Equipo[] arrayEquipos={new Equipo("Boston Celtics", "TD Garden", R.drawable.celtics),
                           new Equipo("Los Angeles Lakers", "Staples Center", R.drawable.lakers),
                           new Equipo("New York Knicks", "Madison Square Garden", R.drawable.knicks),
                           new Equipo("Chicago Bulls", "United Center", R.drawable.bulls),
                           new Equipo("Miami Heat", "AmericanAirlines Arena", R.drawable.miamiheat),
                           new Equipo("Dallas Maverick", "American Airlines Center", R.drawable.dallasmaverick),
                           new Equipo("Denver Nugget", "Pepsi Center", R.drawable.denvernuggets),
                           new Equipo("Sacramento Kings", "Golden 1 Center", R.drawable.sacramento)};

    MyAdapter<Equipo> adapterEquipos;
    AutoCompleteTextView autoCompleteText;

    String[] arrayNombres={arrayEquipos[0].getNombre(), arrayEquipos[1].getNombre(), arrayEquipos[2].getNombre(), arrayEquipos[3].getNombre(),
                           arrayEquipos[4].getNombre(), arrayEquipos[5].getNombre(), arrayEquipos[6].getNombre(),arrayEquipos[7].getNombre()};

    ArrayAdapter<String> adapterAutoComplete;

    Spinner spinner;
    ArrayList<String> arrayListSpinner;
    ArrayAdapter<String> adapterSpinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button) findViewById(R.id.boton);
        button.setOnClickListener(this);

        adapterEquipos=new MyAdapter<Equipo>(this, R.layout.styleequiposimpar, R.id.idNombre, arrayEquipos);
        setListAdapter(adapterEquipos);

        autoCompleteText=(AutoCompleteTextView) findViewById(R.id.autocompleteText);
        autoCompleteText.addTextChangedListener(this);
        adapterAutoComplete=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayNombres);
        autoCompleteText.setAdapter(adapterAutoComplete);
        //arrayadapter para autocompletetext
        spinner=(Spinner) findViewById(R.id.spinner);
        arrayListSpinner=new ArrayList<String>();
        adapterSpinner=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListSpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        addToSpinner();
    }

    public void onListItemClick(ListView l, View v, int position, long id){
    }

    public void addToSpinner(){

        //Comprobar si existe el nombre del equipo para insertarlo y no esta ya insertado
        if(search() && !inserted()) {
            if (adapterSpinner.getCount() < 4) {
                arrayListSpinner.add(autoCompleteText.getText().toString());
                //adapterSpinner.add(autoCompleteText.getText().toString());
                adapterSpinner.notifyDataSetChanged();
            } else {
                // adapterSpinner.insert(autoCompleteText.getText().toString(), 0);
                arrayListSpinner.remove(0);
                arrayListSpinner.add(0, autoCompleteText.getText().toString());
                adapterSpinner.notifyDataSetChanged();
            }
        }
    }


    public boolean search(){
        boolean esta=false;

        for (int i=0;i<arrayNombres.length && !esta;i++){
            if(arrayNombres[i].equals(autoCompleteText.getText().toString())){
                esta=true;
            }
        }
        return esta;
    }

    public boolean inserted(){
        boolean esta=false;

        for (int i=0;i<arrayListSpinner.size() && !esta;i++){
            if(arrayListSpinner.get(i).equals(autoCompleteText.getText().toString())){
                esta=true;
            }
        }
        return esta;
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
