package com.herdbook.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herdbook.data.source.local.model.DBAnimal;

import java.util.List;

@Dao
public interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBAnimal animal);

    @Query("DELETE from animal_table")
    void deleteAll();

    @Query("SELECT * from animal_table")
    List<DBAnimal> getAnimals();

    @Query("SELECT * from animal_table WHERE id =:animalId")
    DBAnimal getAnimal(int animalId);

}
