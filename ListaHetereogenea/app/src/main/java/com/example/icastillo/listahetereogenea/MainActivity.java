package com.example.icastillo.listahetereogenea;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.zip.Inflater;

public class MainActivity extends ListActivity {

    Object[] arrayObject={new Persona("Johnny", "Depp", new GregorianCalendar(1963, 6, 9), R.drawable.johnnydepp),
                          new Animal( "Ursus Actos", "Oso pardo", R.drawable.osopardo ),
                          new Persona("Megan", "Fox", new GregorianCalendar(1986, 5, 16), R.drawable.meganfox),
                          new Animal( "Ailuropoda melanoleuca", "Oso panda", R.drawable.panda ),
                          new Persona("Will", "Smith", new GregorianCalendar(1968, 9, 25), R.drawable.willsmith),
                          new Animal( "Tyto alba", "Lechuza común", R.drawable.lechuza ),
                          new Persona("Albert", "Einstein", new GregorianCalendar(1879, 3, 14), R.drawable.einstein),
                          new Animal( "Bubo bubo", "Búho real", R.drawable.buhoreal ) };

   // MyAdapter<Persona> adapterPersonas;
   // MyAdapter<Animal> adapterAnimales;
    MyAdapter<Object> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adapterPersonas=new MyAdapter<Persona>(this, R.layout.stylepersona, R.id.nombrePersona, (Persona[]) arrayObject);
        //adapterAnimales=new MyAdapter<Animal>(this, R.layout.styleanimal, R.id.nombreAnimal, (Animal[]) arrayObject);
        adapter=new MyAdapter<Object>(this, R.layout.stylepersona, R.id.nombrePersona, arrayObject);
        setListAdapter(adapter);
    }




    class MyAdapter<T> extends ArrayAdapter<T>{

        MyAdapter(Context c, int resourceId, int textId, T[] objects){
            super(c, resourceId, textId, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int tipoVista=0;
            if(arrayObject[position] instanceof Persona){
                tipoVista=1;
            }
            return tipoVista;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;
            ViewHolder holderPersona=null;
            ViewHolderAnimal holderAnimal=null;
            Persona persona;
            Animal animal;
            int tipoVista=getItemViewType(position);

            if(row==null ) {
                LayoutInflater inflater = getLayoutInflater();

                if(tipoVista == 1){
                    row=inflater.inflate(R.layout.stylepersona, parent, false);
                    holderPersona=new ViewHolder(row, R.id.imagePersona, R.id.nombrePersona, R.id.apellidoPersona, R.id.fechaNacPersona);
                    row.setTag(holderPersona);
                }else{
                    row=inflater.inflate(R.layout.styleanimal, parent, false);
                    holderAnimal=new ViewHolderAnimal(row, R.id.imageAnimal, R.id.nombreAnimal, R.id.txtEspecie);
                    row.setTag(holderAnimal);
                }
            }else{
                if(tipoVista == 1){
                    holderPersona=(ViewHolder)row.getTag();//el tag anterior puede ser de ViewHolderAnimal
                }else{
                    holderAnimal=(ViewHolderAnimal) row.getTag();//el tag anterior puede ser de ViewHolder  de persona
                }
            }

            if(tipoVista == 1){
                persona=new Persona((Persona) arrayObject[position]);
                holderPersona.getNombre().setText(persona.getNombre());
                holderPersona.getApellido().setText(persona.getApellido());
                holderPersona.getFechaNac().setText(persona.getFechaNac().get(Calendar.DAY_OF_MONTH)+"/"+persona.getFechaNac().get(Calendar.MONTH)+"/"+persona.getFechaNac().get(Calendar.YEAR));
                holderPersona.getImagen().setImageResource(persona.getIdFoto());
            }else{
                animal=new Animal((Animal) arrayObject[position]);
                holderAnimal.getNombre().setText(animal.getNombre());
                holderAnimal.getEspecie().setText(animal.getNombreCient());
                holderAnimal.getImagen().setImageResource(animal.getIdFoto());
            }

            return row;
        }


    }//Fin clase MyAdapter
}
