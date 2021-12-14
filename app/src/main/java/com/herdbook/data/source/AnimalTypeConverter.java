package com.herdbook.data.source;

import androidx.room.TypeConverter;
import java.util.Date;

import com.herdbook.data.models.Animal;
import com.herdbook.data.models.Herd;

public class AnimalTypeConverter {

    @TypeConverter
    public static Herd toHerd(String name) { return new Herd(name); }

    @TypeConverter
    public static String herdToString(Herd herd) { return herd.getHerdName(); }

    @TypeConverter
    public static Date toDate(String date) { return new Date(); }

    @TypeConverter
    public static String toString(Date date) { return date.toString(); }

    @TypeConverter
    public static String animalToString(Animal animal) { return  animal.getName(); }

    @TypeConverter
    public static Animal toAnimal(String name) { return new Animal(name, 0, null); }

}
