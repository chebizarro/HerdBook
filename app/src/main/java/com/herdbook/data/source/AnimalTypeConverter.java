package com.herdbook.data.source;

import androidx.room.TypeConverter;
import java.util.Date;

import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.local.model.DBAnimal;

public class AnimalTypeConverter {


    @TypeConverter
    public static Date toDate(String date) { return new Date(); }

    @TypeConverter
    public static String toString(Date date) { return date.toString(); }

    @TypeConverter
    public static String animalToString(DBAnimal animal) { return  animal.getName(); }


}
