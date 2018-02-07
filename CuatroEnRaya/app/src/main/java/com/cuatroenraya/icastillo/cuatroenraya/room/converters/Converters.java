package com.cuatroenraya.icastillo.cuatroenraya.room.converters;

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

}
