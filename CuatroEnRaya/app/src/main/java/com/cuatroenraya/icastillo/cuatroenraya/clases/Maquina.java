package com.cuatroenraya.icastillo.cuatroenraya.clases;

import java.util.Random;

/**
 * Created by icastillo on 30/01/2018.
 */

public class Maquina {

    private Juego juego;

    public Maquina(){
        juego=new Juego();
    }

    public int ponFichaAleatoria(int[][] arrayParaleloTablero){
        int columna=-1;
        Random aleatorio=new Random();

        //Comprobar si la columna generada está llena
        do {
            columna = aleatorio.nextInt(7);
        }while (columnaLlena(arrayParaleloTablero, columna));

        return columna;
    }

    /**
    * Proposito: Comprueba si una columna del tablero ya está llena
    * Precondiciones: No hay
    * Entradas: Un array de dos dimernsiones que es el array paralelo al tablero, y un entero que es la columna a comprobar
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si la columna está llena y false sino
    * */
    private boolean columnaLlena(int[][] arrayParaleloTablero, int columna){
        boolean llena=false;

        if(arrayParaleloTablero[columna][5]!=0){
            llena=true;
        }
        return llena;
    }


    public int ponFicha(int[][] arrayParaleloTablero, int[] ultimaFichaPuesta){
        juego.getDatosGameActivity().setArrayParaleloTablero(arrayParaleloTablero);
        juego.getDatosGameActivity().setUltimaFichaPuesta(ultimaFichaPuesta);
        int columna=-1;

        //Simular jugadas para la máquina a partir de la última ficha puesta
        columna = simulaJugadas(2);

        //Si la columna es -1 es que la máquina no ganaría
        if (columna==-1) {

            //Simulamos las jugadas para el usuario
            columna = simulaJugadas(1);

            //Si el usuario tampoco gana
            if(columna==-1){
                //Comprobar que la puesta aleatoria no convenga al usuario para la proxima jugada****

                //do{
                    columna=ponFichaAleatoria(juego.getDatosGameActivity().getArrayParaleloTablero());
                //}while (simulaJugadas()!=-1);
            }
        }

        return columna;
    }

    /**
    * Proposito: Simula las jugadas posibles por el usuario a partir de la última ficha que puso
    * Precondiciones: No hay
    * Entradas: Un entero que será la ficha con la que se simulará la jugada, 1 si es el usuario y 2 si es la máquina
    * Salidas: Un entero
    * Postcondiciones: El entero será la columna que pondría para ganar, -1 si no tiene posibilidad de ganar
    * */
    public int simulaJugadas(int ficha){
        juego.getDatosGameActivity().setHayGanador(false);
        int jugada=-1;
        boolean sal=false;
        //Insertamos fichas en las columnas y comprobamos si ha ganado
        for (int i=0;i<=6 && !sal;i++){
            //Si la columna no está llena
            if (!columnaLlena(juego.getDatosGameActivity().getArrayParaleloTablero(), i)) {
                //Insertamos ficha en la copia del tablero
                insertaFicha(i, ficha);//1 ficha del usuario
                //Comprobamos si hay ganador
                juego.compruebaGanador();
                //Si no hay ganador
                if(!juego.getDatosGameActivity().isHayGanador()){
                    //Borramos la última ficha insertada
                    removeUltimaFichaPuesta();
                }else {//Si hay ganador
                    sal=true;
                    jugada=i;
                }
            }
        }
        return jugada;
    }


    /**
     * Proposito: Inserta una ficha en el tablero
     * Precondiciones: No hay
     * Entradas: Un entero que será la columna en la que se insertará la ficha
     *           Entero que será la ficha a insertar 1 si es el usuario y 2 si es la máquina
     * Salidas: Un entero
     * Postcondiciones: El entero será la columna que pondría para ganar, -1 si no tiene posibilidad de ganar
     * */
    public void insertaFicha(int columna, int ficha){
        boolean sal=false;
        for (int i=0;i<juego.getDatosGameActivity().getArrayParaleloTablero()[0].length && !sal;i++){
            if(juego.getDatosGameActivity().getArrayParaleloTablero()[columna][i]==0){
                juego.getDatosGameActivity().setUltimaFichaPuesta(0, columna);
                juego.getDatosGameActivity().setUltimaFichaPuesta(1, i);
                juego.getDatosGameActivity().setArrayParaleloTablero(columna, i, ficha);
                //juego.getDatosGameActivity().getArrayParaleloTablero()[columna][i]=ficha;
                sal=true;
            }
        }
    }




    public void removeUltimaFichaPuesta(){
        //copiaTablero[copiaUltimaFichaPuesta[0]][copiaUltimaFichaPuesta[1]]=0;
        juego.getDatosGameActivity().setArrayParaleloTablero(juego.getDatosGameActivity().getUltimaFichaPuesta()[0], juego.getDatosGameActivity().getUltimaFichaPuesta()[1], 0);
        juego.getDatosGameActivity().setUltimaFichaPuesta(0, 0);
        juego.getDatosGameActivity().setUltimaFichaPuesta(1, 0);
    }

    /*NEGAMAX
    *
    1) Obtiene el numero de jugadas posibles del tablero que recibe
    2) Crea un tablero auxiliar que es copia del que recibe
    3) Entra en un bucle que se repite tantas veces como numero de jugadas posibles se puedan hacer
    4) Hace una jugada (inserta una ficha en el tablero auxiliar)
    5) Comprueba si es GamerOver o la profundidad del análisis es 0,
    5.1) Si es así, evalúa la jugada y retorna su valor.
    5.2) Sino:
    5.2.1) vuelve a llamar a la funcion negamax, pasandole el nuevo tablero, el jugador contrario (lo multiplica por -1), una profundidad menor, y dos valores -beta, y -alfa_local, que sirven para "podar el arbol de opciones"
    5.2.2) Evalúa la puntuación que se obtiene
    5.2.2.1) Si es mayor su puntuación a la máxima puntuación, la jugada es elegida como posible mejor jugada
    5.2.2.2) Sino, si es igual, de elige aleatoriamente a una de ellas. Esto se hace para que el juego sea un poco aleatorio, al no elegir siempre la misma jugada que tenga la misma puntuación que otras.
    5.2.3) Hace una poda alfa-beta, para dejar de revisar jugadas, si superan el valor de beta.
    * */

}
