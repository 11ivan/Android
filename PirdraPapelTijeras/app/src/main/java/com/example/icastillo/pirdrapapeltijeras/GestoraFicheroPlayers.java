package com.example.icastillo.pirdrapapeltijeras;

import android.content.Context;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by icastillo on 06/12/2017.
 */

public class GestoraFicheroPlayers {

    private final String FILE_PLAYERS="FilePlayers.dat";

    /*
    * Propósito: Ecribe un objeto Player en el fichero FilePlayers.dat
    * Precondiciones: Los datos del Player son correctos y el nombre no debe existir en el fichero
    * Entradas: El contexto de la aplicación y un objeto Player
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si el Player se escribió correctamente en el fichero y false sino
    * */
    public boolean writePlayer(Context context, Player player){
        boolean exito=true;
        File file=new File(context.getFilesDir(), FILE_PLAYERS);
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
            if(file.exists()) {
                fos = new FileOutputStream(file, true);
            }else{
                fos = new FileOutputStream(file);
            }
            oos=new ObjectOutputStream(fos){@Override protected void writeStreamHeader(){}};
            oos.writeObject(player);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(oos!=null){
                    oos.close();
                }
                if(fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return exito;
    }

    /*
    * Propósito: Dado un nombre y contraseña busca un Player en el fichero FilePlayers.dat
    * Precondiciones: Los datos del player no deben estar vacíos
    * Entradas: El contexto de la aplicacion y un objeto Player que será el buscado en el fichero
    * Salidas: Un Player
    * Postcondiciones: El Player contendrá los datos del player buscado y será null si no se encontró
    * */
    public Player readPlayer(Context context, Player playerBuscado){
        Player player=null;
        File file=new File(context.getFilesDir(), FILE_PLAYERS);
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        boolean encontrado=false;
        if(file.exists()){
            try {
                fis=new FileInputStream(file);
                ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};

                //Lectura anticipada
                player=(Player) ois.readObject();

                while (!encontrado && player!=null){
                    if(player.equals(playerBuscado)){
                        encontrado=true;
                    }else{
                        player=(Player)ois.readObject();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (EOFException e) {
                if(encontrado==false){
                    player=null;
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(ois!=null){
                        ois.close();
                    }
                    if(fis!=null){
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//fin si
        return player;
    }

    /*
    * Propósito: Dado un nombre comprueba si algun Player tiene ya ese nombre en el fichero FilePlayers.dat
    * Precondiciones: El nombre no debe estar vacío
    * Entradas: El contexto de la aplicacion y una cadena que será el nombre a buscar en el fichero
    * Salidas: Un booleano
    * Postcondiciones: El booleano será verdadero si el nombre ya está en el fichero y false sino
    * */
    public boolean nameExists(Context context, String name){
        boolean existe=false;
        File file=new File(context.getFilesDir(), FILE_PLAYERS);
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        Player player=null;

        if(file.exists()){
            try {
                fis=new FileInputStream(file);
                ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};

                player=(Player)ois.readObject();
                while (!existe && player!=null){
                    if(player.getNombre().equals(name)){
                        existe=true;
                    }else {
                        player=(Player) ois.readObject();
                    }
                }
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (EOFException e) {
            }catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(ois!=null) {
                        ois.close();
                    }
                    if(fis!=null){
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//fin si
        return existe;
    }

    /*
    * Propósito: Devuelve un listado con los nombres de los Player del fichero FilePlayers.dat
    * Precondiciones: no hay
    * Entradas: El contexto de la aplicacion
    * Salidas: Un arrayList de Cadenas
    * Postcondiciones: El arrayList contendrá los nombres de los player que haya en el fichero
    * */
    public ArrayList<String> getNamePlayerList(Context context){
        ArrayList<String> arrayListNames=new ArrayList<>();
        File file=new File(context.getFilesDir(), FILE_PLAYERS);
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        Player player=null;

        try {
            fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};

            player=(Player) ois.readObject();
            while (player!=null){
                arrayListNames.add(player.getNombre());
                player=(Player) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(ois!=null) {
                    ois.close();
                }
                if(fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayListNames;
    }


}
