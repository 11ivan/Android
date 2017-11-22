package com.example.icastillo.examenprimeraevaluacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VerProductoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ProductoModa2 productoModa=null;
    ProductoMueble2 productoMueble=null;

    ListaProductos2 listaProductos=new ListaProductos2();
    TextView txtNombre;
    ImageView imageProducto;
    Spinner spinnerTallas;
    Spinner spinnerColores;
    TextView txtPrecio;
    TextView txtDescripcion;
    Button btnAddToCesta;
    Object objeto;
    ArrayList<String> coloresProducto;
    ArrayList<String> tallasProducto;
    ArrayAdapter<String> adapterColore;
    ArrayAdapter<String> adapterTallas;
    String productoElegido;
    TextView talla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        talla=(TextView) findViewById(R.id.talla);
        txtNombre=(TextView) findViewById(R.id.nombreProducto);
        imageProducto=(ImageView)findViewById(R.id.idImagen);
        txtPrecio=(TextView) findViewById(R.id.precioProducto);
        txtDescripcion=(TextView) findViewById(R.id.descripcionProducto);
        spinnerTallas=(Spinner) findViewById(R.id.spinnerTalla);

        productoElegido=getIntent().getStringExtra("nombreProducto");
        objeto=listaProductos.getProduct(productoElegido);
        if(objeto instanceof ProductoModa2){
            productoModa=new ProductoModa2((ProductoModa2) objeto);
        }else{
            productoMueble=new ProductoMueble2((ProductoMueble2) objeto);
            spinnerTallas.setVisibility(View.INVISIBLE);
            talla.setVisibility(View.INVISIBLE);
        }

        txtNombre.setText(((Producto2)objeto).getNombre());
        imageProducto.setImageResource(((Producto2)objeto).getIdImagen());
        txtPrecio.setText(((Producto2)objeto).getPrecio());
        txtDescripcion.setText(((Producto2)objeto).getDescripcion());

        spinnerColores=(Spinner) findViewById(R.id.spinnerColor);

        if(productoModa!=null){

            tallasProducto=listaProductos.getTallasProducto(productoModa.getNombre());
            adapterTallas=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tallasProducto);
            adapterTallas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
            spinnerTallas.setAdapter(adapterTallas);
            coloresProducto=listaProductos.getColoresProducto(productoModa.getNombre());
        }else{
            coloresProducto=listaProductos.getColoresProducto(productoMueble.getNombre());
        }

        adapterColore=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, coloresProducto);
        adapterColore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColores.setAdapter(adapterColore);
        spinnerColores.setOnItemSelectedListener(this);

        btnAddToCesta=(Button)findViewById(R.id.addToCesta);
        btnAddToCesta.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //No sé cómo hacer el switch para
        imageProducto.setImageResource(listaProductos.getProductIdImage(coloresProducto.get(i), productoElegido));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
