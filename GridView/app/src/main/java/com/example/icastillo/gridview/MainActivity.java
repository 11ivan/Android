package com.example.icastillo.gridview;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    ImageView img1=new ImageView(this);
    ImageView img2=new ImageView(this);

    ImageView[] arrayImagenes={img1, img2};
    TextView acertados;
    TextView porAcertar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1.setImageResource(R.drawable.binaria);
        img2.setImageResource(R.drawable.enanaroja);
        acertados=(TextView) findViewById(R.id.textoAcertados);
        porAcertar=(TextView) findViewById(R.id.textoPorAcertar);
        GridView gridView=(GridView) findViewById(R.id.grid);
        gridView.setAdapter(new ArrayAdapter<ImageView>(this, R.layout.stylecell, arrayImagenes));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
