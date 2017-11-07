package com.example.icastillo.gridview;

import java.util.Random;

/**
 * Created by icastillo on 06/11/2017.
 */

public class GestoraActivity {


//----------------------------------------------------------------------------------
    /*Proposito: Carga un array de imagenes aleatorias en posisciones aleatorias
    * Prototipo:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    *
    * */
    public Integer[] cargaImagenesAleatorias(int cantidad) {
        Integer[] arrayIdImagenes = new Integer[cantidad*2];
        Random aleatorio = new Random();
        int[] arrayPosiciones=new int[cantidad];
        int imagenaleatoria;
        int posPrimera;
        int posPareja;

        for (int i = 0; i < cantidad; i++) {
            imagenaleatoria=aleatorio.nextInt(cantidad);
            switch (imagenaleatoria) {

                case 0:
                    //arrayIdImagenes[i]=R.drawable.binaria;
                    do{
                        posPrimera=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                        arrayIdImagenes[posPrimera]=R.drawable.binaria;
                    do{
                        posPareja=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=R.drawable.binaria;
                break;


                case 1:
                    do{
                        posPrimera=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=R.drawable.enanaroja;
                    do{
                        posPareja=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=R.drawable.enanaroja;
                break;


                case 3:
                    do{
                        posPrimera=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=R.drawable.sol;
                    do{
                        posPareja=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=R.drawable.sol;

                break;


                case 4:
                    do{
                        posPrimera=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=R.drawable.neptuno;
                    do{
                        posPareja=aleatorio.nextInt(cantidad*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=R.drawable.neptuno;

                break;


            }
        }
        return arrayIdImagenes;
    }

//----------------------------------------------------------------------------------
    /*Proposito: Comprueba si un número está en un array
    * Prototipo:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    *
    * */



}
