package com.example.icastillo.galeriapersonalizada;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/*
Diseñar una aplicación que permita al usuario crear y visualizar una galería de imágenes hecha
 con ViewPager.

Se debe poder configurar la galería (determinar el número de imágenes que contendrá, cuáles
 serán esas imágenes, etc...). Las imágenes se deberán poder escoger de la galería de imágenes del dispositivo.
 Opcionalmente, se puede dar la opción de que el usuario haga una foto para añadirla a la galería.
*/

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    Button addImage;
    Button viewGallery;
    GridView gridView;
    CustomGridAdapter gridAdapter;
    ArrayList<ImageView> arrayImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addImage=(Button) findViewById(R.id.btnAdd);
        viewGallery=(Button) findViewById(R.id.btnVerGaleria);
        arrayImages=new ArrayList<ImageView>();

        gridView=(GridView) findViewById(R.id.grid);
        gridAdapter=new CustomGridAdapter(this, arrayImages);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }









    public class CustomGridAdapter extends BaseAdapter {
        private Activity mContext;

        // Keep all Images in array
        public Integer[] idImages;

        // Constructor
        public CustomGridAdapter(MainActivity mainActivity, Integer[] items) {
            this.mContext = mainActivity;
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
            // imageView.setImageResource(idImages[position]);
            imageView.setImageResource(R.drawable.reverso);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
            return imageView;
        }

    }







}
