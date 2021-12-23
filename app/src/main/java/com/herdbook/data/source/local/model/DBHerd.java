package com.herdbook.data.source.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.rxjava3.annotations.NonNull;

@Entity(tableName="herd_table")
public class DBHerd {

    public int getId() {
        return id;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name="name")
    public String mHerdName;

}
