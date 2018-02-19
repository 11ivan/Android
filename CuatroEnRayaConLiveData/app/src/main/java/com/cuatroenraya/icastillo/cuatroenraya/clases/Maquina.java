package com.cuatroenraya.icastillo.cuatroenraya.clases;

import java.util.Random;

/**
 * Created by icastillo on 30/01/2018.
 */

public class Maquina {

    private int copiaTablero[][]=new int[7][6];
    private int[] copiaUltimaFichaPuesta=new int[2];
    private boolean hayGanador=false;

    public int ponFichaAleatoria(int[][] arrayParaleloTablero){
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


    public int ponFicha(int[][] arrayParaleloTablero, int[] ultimaFichaPuesta){
        copiaTablero=arrayParaleloTablero.clone();
        copiaUltimaFichaPuesta=ultimaFichaPuesta.clone();
        int columna=-1;

        //Simular jugadas a partir de la última ficha puesta
        columna = simulaJugadas();

        //Si la columna es -1 es que el usuario no ganaria
        if (columna==-1) {
            /*columna=ponFichaAleatoria(arrayParaleloTablero);
            //Simulamos la insercion en la columna generada aleatoriamente
            insertaMiFicha(columna);
            //Comprobamos que la que vamos a poner no favorece al usuario
            columna=simulaJugadas();*/

            do{
                columna=ponFichaAleatoria(arrayParaleloTablero);
            }while (simulaJugadas()!=-1);
        }

        /*if (columna==-1) {
            columna=ponFichaAleatoria(arrayParaleloTablero);
        }else{
            //ANALIZAR JUGADA SI PUSIERA LA FICHA EN LA COLUMNA
            //Ponemos la ficha
            //copiaTablero[columna]
            //Simulamos jugadas de nuevo a partir de la ficha que voy a poner
        }*/

        //Si poniendo una sola ficha el usuario gana, Bloqueamos

        //Sino si

        return columna;
    }

    /**
     *
     *
     *
     * */
    public int simulaJugadas(){
        hayGanador=false;
        int jugada=-1;
        boolean sal=false;
        //Insertamos fichas en las columnas y comprobamos si ha ganado
        for (int i=0;i<=6 && !sal;i++){
            //Si la columna no está llena
            if (!columnaLlena(copiaTablero, i)) {
                //Insertamos ficha en la copia del tablero
                insertaFicha(i);
                //Comprobamos si hay ganador
                compruebaGanador();
                //Si no hay ganador
                if(!hayGanador){
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

    public void insertaFicha(int columna){
        boolean sal=false;
        for (int i=0;i<copiaTablero[0].length && !sal;i++){
            if(copiaTablero[columna][i]==0){
                copiaUltimaFichaPuesta[0]=columna;
                copiaUltimaFichaPuesta[1]=i;
                copiaTablero[columna][i]=1;
                sal=true;
            }
        }
    }

    public void insertaMiFicha(int columna){
        boolean sal=false;
        for (int i=0;i<copiaTablero[0].length && !sal;i++){
            if(copiaTablero[columna][i]==0){
                copiaUltimaFichaPuesta[0]=columna;
                copiaUltimaFichaPuesta[1]=i;
                copiaTablero[columna][i]=2;
                sal=true;
            }
        }
    }

    public void removeUltimaFichaPuesta(){
        copiaTablero[copiaUltimaFichaPuesta[0]][copiaUltimaFichaPuesta[1]]=0;
        copiaUltimaFichaPuesta[0]=0;
        copiaUltimaFichaPuesta[1]=0;
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

    /////////////////////////////////////////////////////////////////////////////////////////////
    /** Proposito: Cuando se pone una ficha en el tablero comprueba si ha ganado
    * Precondiciones: Se ha puesto una ficha en el tablero y el total de fichas puestas debe ser mayor o igual que 7
    * Entradas: No hay
    * Salidas: No hay
    * Postcondiciones: Habrá un ganador o no.
    * */

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
        switch (copiaUltimaFichaPuesta[1]) {
            case 3://Si la fila es cualquiera de éstas se comprobará vertical desde la columna y fila en que se puso la ficha hasta abajo
            case 4:
            case 5:
                comprobacionVertical();
                break;
        }

    }

    /** Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    *
    * Solo se hara esta comprobacion cuando la ficha no este en
    * */

    public boolean sePuedeComprobarDiagonaIzquierdaDerecha(){
        boolean sePuede=true;
        int columna=copiaUltimaFichaPuesta[0];
        int fila=copiaUltimaFichaPuesta[1];

        if(columna==0 && fila==5 || columna==0 && fila==4 || columna==0 && fila==3 || columna==1 && fila==4 || columna==2 && fila==5 || columna==1 && fila==5
                || columna==4 && fila==0 || columna==5 && fila==0 || columna==6 && fila==0 || columna==5 && fila==1 || columna==6 && fila==1 || columna==6 && fila==2){
            sePuede=false;
        }

        return sePuede;
    }

   /* * Proposito: Metodo para comprobar acierto en la diagonal de izquierda a derecha, desde abajo hacia arriba /
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */

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
            fichaActual=copiaTablero[i][fila];
            siguienteFicha=copiaTablero[i+1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            fila++;
        }
        if (aciertos==3){
            //Toast.makeText(this, "Alguien ha ganado DIAGONAL1", Toast.LENGTH_SHORT).show();
            hayGanador=true;
        }

        if (!hayGanador){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[i][fila];
                siguienteFicha=copiaTablero[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL2", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[i][fila];
                siguienteFicha=copiaTablero[i+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL3", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=1;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL4", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Quinta diagonal  //ESTA NO DETECTA GANADOR
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=2;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL5", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Sexta diagonal  //ESTA TAMPOCO NO DETECTA GANADOR
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna+1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna++;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL6", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }
    }


    /** Proposito: Metodo para comprobar si es necesario realizar la comprobacion de la diagonal de derecha a izquierda, desde abajo hacia arriba \
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */
    public boolean sePuedeComprobarDiagonaDerechaIzquierda(){
        boolean sePuede=true;
        int columna=copiaUltimaFichaPuesta[0];
        int fila=copiaUltimaFichaPuesta[1];

        if(columna==0 && fila==0 || columna==0 && fila==1 || columna==0 && fila==2 || columna==1 && fila==0 || columna==1 && fila==1 || columna==2 && fila==0
                || columna==4 && fila==5 || columna==5 && fila==5 || columna==5 && fila==4 || columna==6 && fila==5 || columna==6 && fila==4 || columna==6 && fila==3){
            sePuede=false;
        }

        return sePuede;
    }

    /** Proposito: Metodo para acierto en la diagonal de derecha a izquierda, desde abajo hacia arriba
    * Precondiciones:
    * Entradas:
    * Salidas: Un entero con la longitud en esa diagonal
    * Postcondiciones:
    * */
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
            fichaActual=copiaTablero[columna][fila];
            siguienteFicha=copiaTablero[columna-1][fila+1];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else{
                sal=true;//Si en alguna comprobacion de esta diagonal falla no habra que comprobar mas (solo caben 4 fichas)
            }
            columna--;
            fila++;
        }
        if (aciertos==3){
            //Toast.makeText(this, "Alguien ha ganado DIAGONAL1 dchaIzq", Toast.LENGTH_SHORT).show();
            hayGanador=true;
        }

        if (!hayGanador){ //Segunda diagonal (5 Fichas)
            aciertos=0;
            iteraciones++;//3 iteraciones
            fila=1;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL2 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Tercera diagonal
            aciertos=0;
            iteraciones++;//4 iteraciones
            fila=0;
            columna=6;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL3 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Cuarta diagonal//Aqui cambia la cosa
            //4 iteraciones
            aciertos=0;
            fila=0;
            columna=5;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL4 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Quinta diagonal
            //3 iteraciones
            iteraciones=3;
            aciertos=0;
            fila=0;
            columna=4;
            for (int i=0;i<=iteraciones && aciertos<3;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else if(aciertos>0){
                    aciertos=0;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL5 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }

        if (!hayGanador){ //Sexta diagonal
            //2 iteraciones
            sal=false;
            iteraciones=2;
            aciertos=0;
            fila=0;
            columna=3;
            for (int i=0;i<=iteraciones && !sal;i++){
                fichaActual=copiaTablero[columna][fila];
                siguienteFicha=copiaTablero[columna-1][fila+1];
                if(fichaActual!=0 && fichaActual==siguienteFicha){
                    aciertos++;
                }else {
                    sal=true;
                }
                columna--;
                fila++;
            }
            if (aciertos==3){
                //Toast.makeText(this, "Alguien ha ganado DIAGONAL6 dchaIzq", Toast.LENGTH_SHORT).show();
                hayGanador=true;
            }
        }
    }

/**
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    *
*/
    private void comprobacionVertical(){
        int aciertos=0;
        int columna=copiaUltimaFichaPuesta[0];
        int fila=copiaUltimaFichaPuesta[1];
        int fichaActual=0;
        int fichaSiguiente=0;
        int iteraciones=fila-3;//-3 porque solo necesitamos 3 iteraciones a partir de la fila de la ficha puesta
        boolean sigue=true;

        //Esteblecemos el indice an la fila de la ultima ficha puesta
        for(int i=fila;i>iteraciones && sigue;i--){
            fichaActual=copiaTablero[columna][i];
            fichaSiguiente=copiaTablero[columna][i-1];

            //Si la ficha es distinta de la siguiente no hay más que comprobar
            if(fichaActual!=fichaSiguiente){
                sigue=false;
            }else {
                aciertos++;
            }
        }
        if(aciertos==3){
            hayGanador=true;
            //Toast.makeText(this, "Alguien ha ganado VERTICAL", Toast.LENGTH_SHORT).show();
        }
    }

/**
    * Proposito:
    * Precondiciones:
    * Entradas:
    * Salidas:
    * Postcondiciones:
    *
*/
    private void comprobacionHorizontal(){
        int aciertos=0;
        int fila=copiaUltimaFichaPuesta[1];
        boolean sal=false;
        int fichaActual=0;
        int siguienteFicha=0;

        //Si la ultima ficha puesta no tiene una igual a su izquierda o su derecha no hay mas que comprobar
        /*if(ultimaFichaPuesta[1]==arrayParaleloTablero[0][1]){

            }*/

        for(int i=0;i<copiaTablero.length-1 && aciertos<3;i++){
            fichaActual=copiaTablero[i][fila];
            siguienteFicha=copiaTablero[i+1][fila];
            if(fichaActual!=0 && fichaActual==siguienteFicha){
                aciertos++;
            }else if(aciertos>0){
                aciertos=0;
            }
        }
        if(aciertos==3){
            hayGanador=true;
        }
    }


}
