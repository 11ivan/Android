package com.example.icastillo.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listview;
    String[] listaCadenas={"Yeah 1", "Yeah 2", "Yeah 3", "Yeah 4", "Yeah 5", "Yeah 6", "Yeah 7", "Yeah 8", "Yeah 9", "Yeah 10",  "Yeah 11",  "Yeah 12"};
    ArrayAdapter<String> adapter;
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtview=(TextView) findViewById(R.id.txtView);
        listview=(ListView) findViewById(R.id.listV);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCadenas);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        txtview.setText(listaCadenas[position]);
    }



}
