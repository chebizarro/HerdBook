package com.herdbook.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herdbook.data.source.local.model.Animal;

import java.util.List;

@Dao
public interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Animal animal);

    @Query("DELETE from animal_table")
    void deleteAll();

    @Query("SELECT * from animal_table")
    List<Animal> getAnimals();

    @Query("SELECT * from animal_table WHERE id =:animalId")
    Animal getAnimal(int animalId);

}
