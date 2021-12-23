package com.herdbook.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.local.model.DBAnimal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface HerdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBHerd herd);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBAnimal animal);

    @Update
    void updateHerd(DBHerd herd);

    @Update
    void updateAnimal(DBAnimal animal);

    @Query("DELETE FROM herd_table")
    void deleteAll();

    @Query("SELECT * FROM herd_table ORDER BY name ASC")
    Flowable<List<DBHerd>> getAlphabetizedHerds();

    @Query("SELECT * FROM herd_table")
    Flowable<List<DBHerd>> getHerds();

    @Query("SELECT * FROM herd_table WHERE id =:herdid")
    DBHerd getHerd(int herdid);

    @Transaction
    @Query("SELECT * FROM herd_table")
    Flowable<List<HerdWithAnimals>> loadHerdAndAnimals();

}

