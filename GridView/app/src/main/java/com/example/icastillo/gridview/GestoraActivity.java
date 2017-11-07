package com.example.icastillo.gridview;

import java.util.Random;

/**
 * Created by icastillo on 06/11/2017.
 */

public class GestoraActivity {


//----------------------------------------------------------------------------------
    /*Proposito: Carga un array de imagenes aleatorias en posisciones aleatorias
    * Prototipo: Integer[] cargaImagenesAleatorias(int cantidad)
    * Precondiciones: No hay
    * Entradas: Un entero que es la cantidad de parejas a cargar en el array
    * Salidas: Un array de enteros
    * Postcondiciones: El array de enteros se ha cargado con la cantidad de parejas indicada
    *
    * */
    public Integer[] cargaImagenesAleatorias(int cantidadParejas) {
        Integer[] misImagenes={R.drawable.binaria, R.drawable.enanaroja, R.drawable.jupiter, R.drawable.neptuno,
                               R.drawable.sol, R.drawable.supernova1, R.drawable.tierra, R.drawable.supernova2, R.drawable.supernova3};
        Integer[] arrayIdImagenes = new Integer[cantidadParejas*2];
        Random aleatorio = new Random();
        Integer[] arrayIntroducidas=new Integer[cantidadParejas];
        int imagenaleatoria;
        int posPrimera;
        int posPareja;
        boolean noPasa=true;

        for (int i = 0; i < cantidadParejas; i++) {
            //Controlar que la imagen aleatoria no se repita////////////////////////////////////////////////////

            do{
                imagenaleatoria=aleatorio.nextInt(misImagenes.length-1);
                if(i>0) {
                    noPasa = search(imagenaleatoria, arrayIntroducidas);
                }else {
                    noPasa=false;
                }
            }while (noPasa);///////////////////////////////////////////////////////////////////

            noPasa=true;
            arrayIntroducidas[i]=imagenaleatoria;

            switch (imagenaleatoria) {

                case 0:
                    //arrayIdImagenes[i]=R.drawable.binaria;
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                        arrayIdImagenes[posPrimera]=misImagenes[0];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[0];
                break;


                case 1:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[1];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[1];
                break;


                case 2:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[2];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[2];

                break;


                case 3:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[3];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[3];

                break;

                case 4:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[4];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[4];

                    break;

                case 5:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[5];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[5];

                    break;

                case 6:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[6];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[6];

                    break;

                case 7:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[7];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[7];

                    break;

                case 8:
                    do{
                        posPrimera=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPrimera]!=null);
                    arrayIdImagenes[posPrimera]=misImagenes[8];
                    do{
                        posPareja=aleatorio.nextInt(cantidadParejas*2);
                    }while(arrayIdImagenes[posPareja]!=null);
                    arrayIdImagenes[posPareja]=misImagenes[8];

                    break;

            }
        }
        return arrayIdImagenes;
    }

//----------------------------------------------------------------------------------
    /*Proposito: Carga un array de Booleanos con la cantidad y valor especificado
    * Prototipo: Boolean[] cargaArrayBoolean()
    * Precondiciones: No hay
    * Entradas: Un entero que es el tamaño del array y un booleano que será el valor con el se cargará
    * Salidas: Un array de booleanos
    * Postcondiciones: El array de booleanos se ha cargado con el tamaño y valor indicado
    * */
    public boolean[] cargaArrayBoolean(int size, boolean value){
        boolean[] arrayBoolean=new boolean[size];

        for(int i=0;i<arrayBoolean.length;i++){
            arrayBoolean[i]=value;
        }

        return arrayBoolean;
    }

//----------------------------------------------------------------------------------
    /*Proposito: Comprueba si un número entero se encuentra en un array de enteros
    * Prototipo: boolean search(int numSearch, Integer[] arraySearch)
    * Precondiciones: el array contiene algun entero
    * Entradas: Un entero que es el número a buscar y un array de enteros en el que se buscará
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si el entero está en el array y falso sino
    */
    public boolean search(int numSearch, Integer[] arraySearch){
    boolean esta=false;
        for(int i=0;i<arraySearch.length && !esta;i++){
            if(arraySearch[i]!=null) {
                if (arraySearch[i] == numSearch) {
                    esta = true;
                }
            }
        }
    return esta;
    }


}
