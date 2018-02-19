package com.cuatroenraya.icastillo.cuatroenraya.room.converters;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by icastillo on 10/01/18.
 */

public class Converters {

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    @TypeConverter
    public String fromIntArray(int[] array){
        String valorArrayComoCadena="";
        for (int i=0;i<array.length;i++){
            valorArrayComoCadena=valorArrayComoCadena + String.valueOf(array[i]);
        }
        return valorArrayComoCadena;
    }

    @TypeConverter
    public int[] toIntArray(String valorArrayComoCadena){
        int[] array=new int[valorArrayComoCadena.length()];
        for (int i=0;i<valorArrayComoCadena.length();i++){
            array[i]=((int)valorArrayComoCadena.charAt(i))-48;
        }
        return array;
    }

    @TypeConverter
    public String fromIntArrayBid(int[][] array){
        String valorArrayComoCadena="";
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++) {
                valorArrayComoCadena = valorArrayComoCadena + String.valueOf(array[i][j]);
            }
            valorArrayComoCadena=valorArrayComoCadena+",";//Separamos las columnas por comas para la reconstruccion
        }
        return valorArrayComoCadena;
    }

    @TypeConverter
    public int[][] toIntArrayBid(String valorArrayComoCadena){
        int[][] array=new int[7][6];
        String[] arrayTableroSplit=valorArrayComoCadena.split(",");
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                array[i][j]=((int)arrayTableroSplit[i].charAt(j))-48;
            }
        }
        return array;
    }

}
