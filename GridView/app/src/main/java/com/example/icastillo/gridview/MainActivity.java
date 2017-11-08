package com.example.icastillo.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    ImageView[] arrayImagenesUsadas;
    //Integer[] arrayIdImages={R.drawable.enanaroja, R.drawable.binaria, R.drawable.jupiter, R.drawable.sol, R.drawable.tierra, R.drawable.neptuno};
    Integer[] arrayIdImages;
    TextView acertados;
    TextView porAcertar;
    GridView gridView;
    CustomGridAdapter gridAdapter;
    boolean[] arrayLevantadas;
    int cartasLevantadas=0;
    int[] idLevantadas=new int[2];
    int[] posicionLevantadas=new int[2];
    ImageView[] arrayImagesViews=new ImageView[2];
    GestoraActivity gestora;
    int cantidadAciertos=0;
    int cantidadParejas=1;
    //ContadorActividad contadorActividad=new ContadorActividad();
    //Intent intent=new Intent(this, MainActivity.class);
    //ContadorActividad contadorActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cantidadParejas++;


        //ContadorActividad contadorActividad=new ContadorActividad();
        gestora=new GestoraActivity();

        /*if(contadorActividad.getContador()>1) {
            arrayIdImages = gestora.cargaImagenesAleatorias(getIntent().getExtras().getInt("cantidadParejas"));
            //arrayIdImages = gestora.cargaImagenesAleatorias(cantidadParejas++);
         }else{
            arrayIdImages = gestora.cargaImagenesAleatorias(cantidadParejas);
        }*/

        /*if(savedInstanceState!=null) {
            arrayIdImages = gestora.cargaImagenesAleatorias(savedInstanceState.getInt("cantidadParejas"));
            //arrayIdImages = gestora.cargaImagenesAleatorias(cantidadParejas++);
        }else{
            arrayIdImages = gestora.cargaImagenesAleatorias(cantidadParejas);
        }*/
        arrayIdImages = gestora.cargaImagenesAleatorias(cantidadParejas);

        arrayLevantadas=gestora.cargaArrayBoolean(arrayIdImages.length, false);
        arrayImagenesUsadas=new ImageView[arrayIdImages.length];
        acertados=(TextView) findViewById(R.id.textoAcertados);
        acertados.setText(String.valueOf(cantidadAciertos));
        porAcertar=(TextView) findViewById(R.id.textoPorAcertar);
        porAcertar.setText("/"+String.valueOf(arrayIdImages.length/2));
        gridView=(GridView) findViewById(R.id.grid);
        //adapterImages=new ArrayAdapter<ImageView>(this, R.layout.stylecell, R.id.idImageView, arrayImagenes);
        //gridView.setAdapter(adapterImages);
        gridAdapter=new CustomGridAdapter(this, arrayIdImages);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        levantaCarta(view, position);
        //arrayImagenesUsadas[position]=(ImageView) view;//aquí!!!!!
    }


    //levantaCarta
    /*Proposito: Levanta una carta si no está levantada ya
    * Prototipo: public void levantaCarta(View view, int position)
    * Precondiciones: No hay
    * Entradas: Un View que será el ImageView de la carta a levantar y un entero que será la posición de la carta a levantar
    * Salidas: no hay
    * Postcondiciones: La carta se habrá levantado sino se ha levantado ya
    * */
    public void levantaCarta(View view, final int position){
        final Intent intent=new Intent(this, MainActivity.class);
        final ImageView image;
        //cartasLevantadas++;

        if (!compruebaCartaLevantada(position) && cartasLevantadas<2) {
            arrayImagenesUsadas[position]=(ImageView) view;
            cartasLevantadas++;
            marcaCartaLevantada(position);
            image = (ImageView) view;
            Animation flip;
            flip= AnimationUtils.loadAnimation(this, R.anim.rotate);
            flip.reset();
            image.startAnimation(flip);

            image.setImageResource(arrayIdImages[position]);

            idLevantadas[cartasLevantadas - 1] = arrayIdImages[position];
            posicionLevantadas[cartasLevantadas - 1] = position;
            arrayImagesViews[cartasLevantadas - 1] = (ImageView) view;

            if (cartasLevantadas == 2) {//Si hay dos cartas levantadas comprobamos si ha acertado
                //Toast.makeText(this, compruebaAcierto2(idLevantadas), Toast.LENGTH_LONG).show();---

                //Comprobar Acierto
                if (compruebaAcierto(idLevantadas)) {
                        cantidadAciertos++;
                        acertados.setText(String.valueOf(cantidadAciertos));
                        cartasLevantadas=0;
                        if(acertados.getText().toString().equals(porAcertar.getText().toString().replace("/", ""))){
                            animaCartas();
                            siguienteNivel();
                        }

                } else {//si no ha acertado volteamos y desmarcamos las cartas levantadas, también actualizaremos el contador de cartas levantadas a 0
                    tapaCartas(arrayImagesViews);
                    //desmarcaCartasLevantada(posicionLevantadas);
                }
            }
        }
    }


    //desaparece cartas
    /*public void disapearCards(){
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },1500);
    }*/


    public void animaCartas(){
        final Intent intent=new Intent(this, MainActivity.class);
        //intent.putExtra("cantidadParejas", cantidadParejas++);
        //finish();
        //startActivity(intent);
        ImageView image=null;
        for(int i=0;i<arrayIdImages.length;i++){
            image=arrayImagenesUsadas[i];
            Animation flip;
            flip= AnimationUtils.loadAnimation(this, R.anim.rotate);
            flip.reset();
            image.startAnimation(flip);
        }
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent.putExtra("cantidadParejas", cantidadParejas++);
                //finish();
                //startActivity(intent);
                //arrayIdImages=gestora.cargaImagenesAleatorias(cantidadParejas++);
                //CustomGridAdapter gridAdapter=new CustomGridAdapter(this, arrayIdImages);
                //gridView.setAdapter(new CustomGridAdapter(this, arrayIdImages));
                Bundle bundle =new Bundle();
                bundle.putInt("cantidadParejas", cantidadParejas++);
                onCreate(bundle);
            }
        },1500);*/
    }

    //siguienteNivel
    /*Proposito:
    * Prototipo:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * */
    public void siguienteNivel(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle =new Bundle();
                bundle.putInt("cantidadParejas", cantidadParejas++);
                onCreate(bundle);
            }
        },1500);
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
        String cadena="Vuelve a probar";

        if(idLevantadas[0]==idLevantadas[1]){
            acertado=true;
            cadena="Bien!!";
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
    public void tapaCartas(final ImageView[] ImagescartasLevantadas){
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               /* try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                ImagescartasLevantadas[0].setImageResource(R.drawable.reverso);
                ImagescartasLevantadas[1].setImageResource(R.drawable.reverso);
                desmarcaCartasLevantada(posicionLevantadas);
            }
        },1500);
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
           // imageView.setImageResource(idImages[position]);
            imageView.setImageResource(R.drawable.reverso);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
            return imageView;
        }

    }


}
