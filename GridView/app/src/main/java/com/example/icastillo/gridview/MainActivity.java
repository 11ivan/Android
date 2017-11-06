package com.example.icastillo.gridview;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    ImageView img1;
    ImageView img2;

    ImageView[] arrayImagenes={img1, img2};
    Integer[] arrayIdImages={R.drawable.enanaroja, R.drawable.binaria, R.drawable.jupiter, R.drawable.sol, R.drawable.tierra, R.drawable.neptuno};
    Integer[] arrayIdReverso={R.drawable.reverso, R.drawable.reverso, R.drawable.reverso, R.drawable.reverso, R.drawable.reverso, R.drawable.reverso};
    String[] arrayCadena={"Mamon", "Perraco", "No", "Quiere", "Imagenes"};
    TextView acertados;
    TextView porAcertar;
    GridView gridView;
    ArrayAdapter<ImageView> adapterImages;
    CustomGridAdapter gridAdapter;
    Boolean[] arrayLevantadas={false, false, false, false, false, false};
    int cartasLevantadas=0;
    int[] idLevantadas=new int[2];
    int[] posicionLevantadas=new int[2];
    ImageView[] arrayImagesViews=new ImageView[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //img1=new ImageView(this);
        //img2=new ImageView(this);
        //img1.setImageResource(R.drawable.binaria);
        //img2.setImageResource(R.drawable.enanaroja);
        acertados=(TextView) findViewById(R.id.textoAcertados);
        porAcertar=(TextView) findViewById(R.id.textoPorAcertar);
        gridView=(GridView) findViewById(R.id.grid);
        //adapterImages=new ArrayAdapter<ImageView>(this, R.layout.stylecell, R.id.idImageView, arrayImagenes);
        //gridView.setAdapter(adapterImages);
        gridAdapter=new CustomGridAdapter(this, arrayIdReverso);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        levantaCarta(view, position);
    }

    //sleep
    /*Proposito: Para la ejecución del programa durante 1500 milisegundos
    * Prototipo: public void sleep()
    * Precondiciones: No hay
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: El programa se para durante 1500 milisegundos
    * */
   /* public void sleep(){
        Thread timer = new Thread() {
            public void run(){
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
        timer.run();
    }*/

    //levantaCarta
    /*Proposito: Levanta una carta si no está levantada ya
    * Prototipo: public void levantaCarta(View view, int position)
    * Precondiciones: No hay
    * Entradas: Un View que será el ImageView de la carta a levantar y un entero que será la posición de la carta a levantar
    * Salidas: No hay
    * Postcondiciones: La carta se habrá levantado sino se ha levantado ya
    * */
    public void levantaCarta(View view, int position){
        ImageView image;
        cartasLevantadas++;

        if (!compruebaCartaLevantada(position) && cartasLevantadas<=2) {
            marcaCartaLevantada(position);
            image = (ImageView) view;
            image.setImageResource(arrayIdImages[position]);
            //sleep();
            idLevantadas[cartasLevantadas-1]=arrayIdImages[position];
            posicionLevantadas[cartasLevantadas-1]=position;
            arrayImagesViews[cartasLevantadas-1]=(ImageView) view;

            if(cartasLevantadas==2) {//Si hay dos cartas levantadas comprobamos si ha acertado
                Toast.makeText(this, compruebaAcierto2(idLevantadas), Toast.LENGTH_LONG).show();
                //Comprobar Acierto
                if(compruebaAcierto(idLevantadas)){

                }else{//si no ha acertado volteamos y desmarcamos las cartas levantadas, también actualizaremos el contador de cartas levantadas a 0

                    tapaCartas(arrayImagesViews);
                    desmarcaCartasLevantada(posicionLevantadas);
                }
            }
            //compruebaAcierto(posicionLevantada);
        }
    }




    //Tengo que guardar las cartas que se hayan levantado (posicion), y cuando se hayan levantado dos debo comprobar si ha acertado,
    //sino a acertado debo desmarcar las cartas levantadas, actualizar el contador de levantadas a 0
    //y volver a voltear las dos cartas

    //CompruebaAcierto
    /*Proposito: Comprueba ha acertado la pareja
    * Prototipo: public Boolean compruebaAcierto(int[] cartasLevantadas)
    * Precondiciones: No hay
    * Entradas: Un array de enteros que son las posiciones de las cartas levantadas
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si ha acertado y falso sino
    * */
    public Boolean compruebaAcierto(int[] cartasLevantadas){
        Boolean acertado=false;
        //sleep();
        if(idLevantadas[0]==idLevantadas[1]){
            acertado=true;
        }
        return acertado;
    }

    public String compruebaAcierto2(int[] cartasLevantadas){
        Boolean acertado=false;
        String cadena="falso";

        if(idLevantadas[0]==idLevantadas[1]){
            acertado=true;
            cadena="verdadero";
        }
        return cadena;
    }

    //tapaCartas
    /*Proposito: Tapa dos cartas,
    * Prototipo: public void tapaCartas(int[] posicionCartas)
    * Precondiciones: Las cartas están levantadas
    * Entradas: Un array de enteros que son las posiciones de las cartas
    * Salidas: No hay
    * Postcondiciones: Las cartas se han tapado
    * */
    public void tapaCartas(ImageView[] ImagescartasLevantadas){
        //sleep();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImagescartasLevantadas[0].setImageResource(R.drawable.reverso);
                ImagescartasLevantadas[1].setImageResource(R.drawable.reverso);
            }
        },2000);
    }





    //CompruebaCartaLevantada
    /*Proposito: Comprueba si una carta se ha levantado
    * Prototipo: public Boolean compruebaCartaLevantada(int position)
    * Precondiciones: No hay
    * Entradas: Un entero que es la posicion de la carta
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si la carta se ha levantado ya y falso sino
    * */
    public Boolean compruebaCartaLevantada(int position){
        Boolean levantada=false;

        if(arrayLevantadas[position]==true){
            levantada=true;
        }
        return levantada;
    }

    //MarcaCartaLevantada
    /*Proposito: Marca la carta levantada
    * Prototipo: public void marcaCartaLevantada(int position)
    * Precondiciones: La carta no está marcada como levantada
    * Entradas: Un entero que es la posición de la carta
    * Salidas: no hay
    * Postcondiciones: La carta se habrá marcado como levantada
    * */
    public void marcaCartaLevantada(int position){
        arrayLevantadas[position]=true;
    }


    //DesmarcaCartaLevantada
    /*Proposito: Desmarca una carta que se ha levantado carta levantada
    * Prototipo: public void desmarcaCartaLevantada(int position)
    * Precondiciones: La carta no está desmarcada
    * Entradas: Un entero que es la posición de la carta
    * Salidas: no hay
    * Postcondiciones: La carta se habrá desmarcado como levantada
    * */
    public void desmarcaCartasLevantada(int[] positions){
        arrayLevantadas[positions[0]]=false;
        arrayLevantadas[positions[1]]=false;
        cartasLevantadas=0;
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
            imageView.setImageResource(idImages[position]);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
            return imageView;
        }

    }


}
