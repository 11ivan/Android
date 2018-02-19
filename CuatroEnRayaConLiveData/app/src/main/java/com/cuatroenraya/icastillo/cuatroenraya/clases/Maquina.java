package com.cuatroenraya.icastillo.cuatroenraya.clases;

import java.util.Random;

/**
 * Created by icastillo on 30/01/2018.
 */

public class Maquina {



    public int ponFicha(int[][] arrayParaleloTablero, int[] ultimaFichaPuesta){
        int columna=-1;
        Random aleatorio=new Random();

        //Comprobar si la columna generada está llena
        do {
            columna = aleatorio.nextInt(7);
        }while (columnaLlena(arrayParaleloTablero, columna));

        return columna;
    }

    /*
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






    /////////////////////////////////////////////////////////////////////////////////////////////
/*
        */
/*
    * Proposito: Cuando se pone una ficha en el tablero comprueba si ha ganado
    * Precondiciones: Se ha puesto una ficha en el tablero y el total de fichas puestas debe ser mayor o igual que 7
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Habrá un ganador o no.
    * *//*

    private void compruebaGanador(){
        //La comprobacion Horizontal siempre se va a hacer  (Por ahora)
        comprobacionHorizontal();

        //Las diagonales también se comprobarán
        if(sePuedeComprobarDiagonaIzquierdaDerecha()){
            //Comprobar diagonal de izquierda a derecha desde abajo hacia arriba
            compruebaDiagonaIzquierdaDerecha();
        }

        if(sePuedeComprobarDiagonaDerechaIzquierda()){
            //Comprobar diagonal de derecha a izquierda desde abajo hacia arriba
            compruebaDiagonaDerechaIzquierda();
        }

        //Segun la fila que se ha colocado la ultima ficha
        switch (datosGameActivity.getUltimaFichaPuesta()[1]) {
            case 3://Si la fila es cualquiera de éstas se comprobará vertical desde la columna y fila en que se puso la ficha hasta abajo
            case 4:
            case 5:
                comprobacionVertical();
                break;
        }

    }

    */
/*
    * Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    *
    * Solo se hara esta comprobacion cuando la ficha no este en
    * *//*

    public boolean sePuedeComprobarDiagonaIzquierdaDerecha(){
        boolean sePuede=true;
        int columna=datosGameActivity.getUltimaFichaPuesta()[0];
        int fila=datosGameActivity.getUltimaFichaPuesta()[1];

        if(columna==0 && fila==5 || columna==0 && fila==4 || columna==0 && fila==3 || columna==1 && fila==4 || columna==2 && fila==5 || columna==1 && fila==5
                || columna==4 && fila==0 || columna==5 && fila==0 || columna==6 && fila==0 || columna==5 && fila==1 || columna==6 && fila==1 || columna==6 && fila==2){
            sePuede=false;
        }

        return sePuede;
    }

    */
/*
    * Proposito: Metodo para comprobar acierto en la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * *//*

    public void compruebaDiagonaIzquierdaDerecha(){
        int aciertos=0;
        int columna=0;
        int fila=2;
        int iteraciones=2;
        int fichaActual=0;
        int siguienteFicha=0;
        boolean sal=false;

        //Primera diagonal
        for (int i=0;i<=iteraciones && !sal;i++){
            fichaActual=datosGameActivity.getArrayParaleloTablero()[i][fila];
            siguienteFicha=datosGameActivity.getArrayParaleloTablero()[i+1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            fila++;
        }
        if (aciertos==3){
            Toast.makeText(this, "Alguien ha ganado DIAGONAL1", Toast.LENGTH_SHORT).show();
            datosGameActivity.setHayGanador(true);
        }

        if (!datosGameActivity.isHayGanador()){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[i][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL2", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }


        if (!datosGameActivity.isHayGanador()){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[i][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL3", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }


        if (!datosGameActivity.isHayGanador()){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL4", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }


        if (!datosGameActivity.isHayGanador()){ //Quinta diagonal  //ESTA NO DETECTA GANADOR
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=2;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL5", Toast.LENGTH_SHORT).show();

                datosGameActivity.setHayGanador(true);
            }
        }


        if (!datosGameActivity.isHayGanador()){ //Sexta diagonal  //ESTA TAMPOCO NO DETECTA GANADOR
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL6", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }

    }


    */
/*
    * Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de derecha a izquierda, desde abajo hacia arriba \
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * *//*

    public boolean sePuedeComprobarDiagonaDerechaIzquierda(){
        boolean sePuede=true;
        int columna=datosGameActivity.getUltimaFichaPuesta()[0];
        int fila=datosGameActivity.getUltimaFichaPuesta()[1];

        if(columna==0 && fila==0 || columna==0 && fila==1 || columna==0 && fila==2 || columna==1 && fila==0 || columna==1 && fila==1 || columna==2 && fila==0
                || columna==4 && fila==5 || columna==5 && fila==5 || columna==5 && fila==4 || columna==6 && fila==5 || columna==6 && fila==4 || columna==6 && fila==3){
            sePuede=false;
        }

        return sePuede;
    }

    */
/*
    * Proposito: Metodo para acierto en la diagonal de derecha a izquierda, desde abajo hacia arriba \
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * *//*

    public void compruebaDiagonaDerechaIzquierda(){
        int aciertos=0;
        int columna=6;
        int fila=2;
        int iteraciones=2;
        int fichaActual=0;
        int siguienteFicha=0;
        boolean sal=false;

        //Primera diagonal
        for (int i=0;i<=iteraciones && !sal;i++){
            fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
            siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            columna--;
            fila++;
        }
        if (aciertos==3){
            Toast.makeText(this, "Alguien ha ganado DIAGONAL1 dchaIzq", Toast.LENGTH_SHORT).show();
            datosGameActivity.setHayGanador(true);
        }

        if (!datosGameActivity.isHayGanador()){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL2 dchaIzq", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }

        if (!datosGameActivity.isHayGanador()){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL3 dchaIzq", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }

        if (!datosGameActivity.isHayGanador()){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=5;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL4 dchaIzq", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }

        if (!datosGameActivity.isHayGanador()){ //Quinta diagonal
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=4;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL5 dchaIzq", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }

        if (!datosGameActivity.isHayGanador()){ //Sexta diagonal
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][fila];
                siguienteFicha=datosGameActivity.getArrayParaleloTablero()[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                Toast.makeText(this, "Alguien ha ganado DIAGONAL6 dchaIzq", Toast.LENGTH_SHORT).show();
                datosGameActivity.setHayGanador(true);
            }
        }
    }

    */
/*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * *//*

    private void comprobacionVertical(){
        int aciertos=0;
        int columna=datosGameActivity.getUltimaFichaPuesta()[0];
        int fila=datosGameActivity.getUltimaFichaPuesta()[1];
        int fichaActual=0;
        int fichaSiguiente=0;
        int iteraciones=fila-3;//-3 porque solo necesitamos 3 iteraciones a partir de la fila de la ficha puesta
        boolean sigue=true;

        //Esteblecemos el indice an la fila de la ultima ficha puesta
        for(int i=fila;i>iteraciones && sigue;i--){
            fichaActual=datosGameActivity.getArrayParaleloTablero()[columna][i];
            fichaSiguiente=datosGameActivity.getArrayParaleloTablero()[columna][i-1];

            //Si la ficha es distinta de la siguiente no hay más que comprobar
            if(fichaActual!=fichaSiguiente){
                sigue=false;
            }else {
                aciertos++;
            }
        }
        if(aciertos==3){
            datosGameActivity.setHayGanador(true);
            Toast.makeText(this, "Alguien ha ganado VERTICAL", Toast.LENGTH_SHORT).show();
        }
    }

    */
/*
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    * *//*

    private void comprobacionHorizontal(){
        int aciertos=0;
        int fila=datosGameActivity.getUltimaFichaPuesta()[1];
        boolean sal=false;
        int fichaActual=0;
        int siguienteFicha=0;

        //Si la ultima ficha puesta no tiene una igual a su izquierda o su derecha no hay mas que comprobar
        */
/*if(ultimaFichaPuesta[1]==arrayParaleloTablero[0][1]){

        }*//*

        for(int i=0;i<datosGameActivity.getArrayParaleloTablero().length-1 && aciertos<3;i++){
            fichaActual=datosGameActivity.getArrayParaleloTablero()[i][fila];
            siguienteFicha=datosGameActivity.getArrayParaleloTablero()[i+1][fila];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else if(aciertos>0){
                aciertos=0;
            }
        }
        if(aciertos==3){
            datosGameActivity.setHayGanador(true);
        }
    }
*/


}
