package com.herdbook.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.rxjava3.annotations.NonNull;

@Entity(tableName="herd_table")
public class Herd {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name="name")
    private String mHerdName;

    public Herd(@NonNull String herdName) { this.mHerdName = herdName; }

    public String getHerdName() { return this.mHerdName; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }
}
