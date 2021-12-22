package com.herdbook.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.herdbook.data.source.local.model.Animal;
import com.herdbook.data.source.local.model.Herd;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface HerdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Herd herd);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Animal animal);

    @Update
    void updateHerd(Herd herd);

    @Update
    void updateAnimal(Animal animal);

    @Query("DELETE FROM herd_table")
    void deleteAll();

    @Query("SELECT * FROM herd_table ORDER BY name ASC")
    Flowable<List<Herd>> getAlphabetizedHerds();

    @Query("SELECT * FROM herd_table")
    Flowable<List<Herd>> getHerds();

    @Query("SELECT * FROM herd_table WHERE id =:herdid")
    Herd getHerd(int herdid);

    @Transaction
    @Query("SELECT * FROM herd_table")
    Flowable<List<HerdWithAnimals>> loadHerdAndAnimals();

}

