package com.example.icastillo.pirdrapapeltijeras;

import android.content.Context;

/**
 * Created by icastillo on 07/12/2017.
 */

public class GestoraLoginActivity {

    /*
    * Proposito: Comprueba los datos de login del usuario
    * Precondiciones: No hay
    * Entradas: El contexto de la aplicación y un objeto Player
    * Postcondiciones: El array de enteros estará a 0 si tod es correcto,
    *                  primera posicion 1 si el nombre de Usuario está vacío,
    *                  primera posición 2 si el nombre no existe,
    *                  segunda posicion 1 si el password está vacío,
    *                  tercera posición 1 si el el password no es correcto,
    * */
    public int[] compruebaCamposLogin(Context context, Player player){
        int[] arrayErrores={0,0,0};
        Util util=new Util();
        UtilFicheros utilFicheros=new UtilFicheros();
        Player playerCopia=null;

        if(!util.compruebaCadena(player.getNombre())){
            arrayErrores[0]=1;
        }else if(!utilFicheros.nameExists(context, player.getNombre())){
            arrayErrores[0]=2;
        }

        if(!util.compruebaCadena(player.getPassword())){
            arrayErrores[1]=1;
        }
        if(arrayErrores[0]==0 && arrayErrores[1]==0){
            if(utilFicheros.readPlayer(context, player)==null){
                arrayErrores[2]=1;
            }
        }

        return  arrayErrores;
    }

}
