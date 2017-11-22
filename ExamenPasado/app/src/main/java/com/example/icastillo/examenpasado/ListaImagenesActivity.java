package com.example.icastillo.examenpasado;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ListaImagenesActivity extends Activity implements AdapterView.OnItemClickListener {

    GridView grid;
    CustomGridAdapter gridAdapter;
    ListaImagenes listaImagenes=new ListaImagenes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);

        grid=(GridView) findViewById(R.id.grid);
        gridAdapter=new CustomGridAdapter(this, listaImagenes.getArrayImagenes());
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.putExtra("idImagen", listaImagenes.getArrayImagenes()[position]);
        setResult(RESULT_OK, intent);
        finish();
    }


    public class CustomGridAdapter extends BaseAdapter {
        private Activity mContext;

        // Keep all Images in array
        public Integer[] idImages;

        // Constructor
        public CustomGridAdapter(ListaImagenesActivity listaImagenesActivity, Integer[] items) {
            this.mContext = listaImagenesActivity;
            this.idImages = items;
        }

        @Override
        public int getCount() {
            return idImages.length;
        }

        @Override
        public Object getItem(int position) {
            return idImages[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(listaImagenes.getArrayImagenes()[position]);

            return imageView;
        }

    }



}
