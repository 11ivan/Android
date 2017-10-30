package com.example.icastillo.listahetereogenea;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Objects;

public class MainActivity extends ListActivity {

    Object[] arrayObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    class MyAdapter<T> extends ArrayAdapter<T>{

        MyAdapter(Context c, int resourceId, int textId, T[] objects){
            super(c, resourceId, textId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row=convertView;



            return row;
        }


    }//Fin clase MyAdapter
}
