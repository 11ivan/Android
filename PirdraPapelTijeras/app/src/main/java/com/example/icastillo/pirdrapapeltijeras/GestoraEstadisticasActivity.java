package com.example.icastillo.pirdrapapeltijeras;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by icastillo on 09/12/2017.
 */

public class GestoraEstadisticasActivity {

    /*
    * Propósito: Devuelve un listado con los nombreas de los Player del fichero FilePlayers.dat
    * Precondiciones: no hay
    * Entradas: El contexto de la aplicacion
    * Salidas: Un arrayList de Cadenas
    * Postcondiciones: El arrayList contendrá los nombres de los player que haya en el fichero
    * */
    public ArrayList<Player> getListPlayers(Context context, SharedPreferences sharedPreferences){
        ArrayList<Player> listPlayers=new ArrayList<Player>();
        ArrayList<String> listaNombresPlayers=new ArrayList<String>();
        GestoraFicheroPlayers gestoraFicheroPlayers=new GestoraFicheroPlayers();
        Player player=null;

        listaNombresPlayers=gestoraFicheroPlayers.getNamePlayerList(context);

        for (int i=0;i<listaNombresPlayers.size();i++){
            player=new Player();
            player.setNombre(listaNombresPlayers.get(i));
            player.setVictorias(sharedPreferences.getInt(player.getNombre()+"victorias", 0));
            player.setDerrotas(sharedPreferences.getInt(player.getNombre()+"derrotas", 0));
            player.setEmpates(sharedPreferences.getInt(player.getNombre()+"empates", 0));
            player.setVecesPiedra(sharedPreferences.getInt(player.getNombre()+"veces_piedra", 0));
            player.setVecesPapel(sharedPreferences.getInt(player.getNombre()+"veces_papel", 0));
            player.setVecesTijera(sharedPreferences.getInt(player.getNombre()+"veces_tijera", 0));
            listPlayers.add(player);
        }

        return listPlayers;
    }




}
