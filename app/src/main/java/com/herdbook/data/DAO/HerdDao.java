package com.herdbook.data.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herdbook.data.models.Herd;

import java.util.List;

@Dao
public interface HerdDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Herd herd);

    @Query("DELETE FROM herd_table")
    void deleteAll();

    @Query("SELECT * FROM herd_table ORDER BY name ASC")
    LiveData<List<Herd>> getAlphabetizedHerds();

}
