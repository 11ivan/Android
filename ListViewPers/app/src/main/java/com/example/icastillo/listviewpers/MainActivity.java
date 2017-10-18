package com.example.icastillo.listviewpers;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    IconicAdapter <Disco> myadapter;
    TextView textView;
    Disco[] arrayDiscos={new Disco("Blakanguai", "Varios artistas", new GregorianCalendar(2016, 10, 5),String.valueOf(R.drawable.bw)),
                        new Disco("Hay", "O no hay", new GregorianCalendar(2015, 1, 15),String.valueOf(R.drawable.bw)),
                        new Disco("Pffff", "Quien tu quieras", new GregorianCalendar(2017, 5, 25), String.valueOf(R.drawable.bw))};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.txtView);
        listView=(ListView) findViewById(R.id.list);
        myadapter=new IconicAdapter<Disco>(this, R.layout.stylelist, R.id.txtView, arrayDiscos);
        listView.setAdapter(myadapter);

    }


    class IconicAdapter<T> extends ArrayAdapter<T> {
        IconicAdapter(Context c, int resourceId, int textId, T[] objects) {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            ImageView imagen = (ImageView) row.findViewById(R.id.imagen);

            imagen.setImageResource(Integer.parseInt(arrayDiscos[position].getImagen()));

            /*if (items[position].equals("calendario")) {
                icon.setImageResource(R.drawable.calendar48x48);
            }
            else if (items[position].equals("GPS")){
                icon.setImageResource(R.drawable.compass48x48);
            }
            else if (items[position].equals("alarma")){
                icon.setImageResource(R.drawable.alarm48x48);
            }
            else{
                icon.setImageResource(R.drawable.delete);
            }*/

            return (row);
        }

    }//fin clase iconicAdapter


}
