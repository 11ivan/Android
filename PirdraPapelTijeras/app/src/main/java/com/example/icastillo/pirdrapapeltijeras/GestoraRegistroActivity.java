package com.example.icastillo.pirdrapapeltijeras;

import android.content.Context;

/**
 * Created by icastillo on 06/12/2017.
 */

public class GestoraRegistroActivity {

    /*
    * Proposito: Comprueba los datos de registro del usuario
    * Precondiciones: No hay
    * Entradas: El contexto de la aplicación, un objeto Player y una cadena que será la confirmación del password
    * Salidas: Un array de enteros
    * Postcondiciones: El array de enteros estará a 0 si tod es correcto,
    *                  primera posicion 1 si el nombre de Usuario está vacío,
    *                  primera posición 2 si el nombre ya existe,
    *                  segunda posicion 1 si el password está vacío,
    *                  tercera posición 1 si la confirmación del password está vacío,
    *                  cuarta posición 1 si el password del usuario y la confirmación no coinciden,
    * */
    public int[] compruebaCamposRegistro(Context context, Player player, String confirmPassword){
        int[] arrayErrores={0,0,0,0};
        Util util=new Util();
        UtilFicheros utilFicheros=new UtilFicheros();

        if(!util.compruebaCadena(player.getNombre())){
            arrayErrores[0]=1;
        }else if(utilFicheros.nameExists(context, player.getNombre())){
            arrayErrores[0]=2;
        }

        if(!util.compruebaCadena(player.getPassword())){
            arrayErrores[1]=1;
        }
        if(!util.compruebaCadena(confirmPassword)){
            arrayErrores[2]=1;
        }

        if(arrayErrores[1]==0 && arrayErrores[2]==0 && arrayErrores[3]==0){
            if(!player.getPassword().equals(confirmPassword)){
                arrayErrores[3]=1;
            }
        }

        return  arrayErrores;
    }

    /*
    * Proposito: Dado un nombre y un password crea un objeto Player
    * Precondiciones: No hay
    * Entradas: El contexto de la aplicación y dos cadenas la primera el nombre y la segunda el password
    * Salidas: Un objeto Player
    * Postcondiciones: El objeto Player contiene los datos dados
    * */


}
