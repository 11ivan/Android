package icastillo.examen2trimestre.application.application.room.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;

/**
 * Created by icastillo on 21/02/18.
 */

public class Converters {

    @TypeConverter
    public String fromIntArrayList(ArrayList<Integer> array){
        String valorArrayComoCadena="";
        for (int i=0;i<array.size();i++){
            valorArrayComoCadena=valorArrayComoCadena + String.valueOf(array.get(i));
        }
        return valorArrayComoCadena;
    }

    @TypeConverter
    public ArrayList<Integer> toIntArrayList(String valorArrayComoCadena){
        ArrayList<Integer> array=new ArrayList<>(valorArrayComoCadena.length());
        for (int i=0;i<valorArrayComoCadena.length();i++){
            array.add (i, ((int)valorArrayComoCadena.charAt(i))-48);
        }
        return array;
    }

}
