package com.herdbook.data.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.herdbook.data.models.Herd;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface HerdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Herd herd);

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

