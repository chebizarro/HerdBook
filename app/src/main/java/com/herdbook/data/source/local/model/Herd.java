package com.herdbook.data.source.local.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.rxjava3.annotations.NonNull;

@Entity(tableName="herd_table")
public class Herd extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name="name")
    private String mHerdName;

    public Herd(@NonNull String herdName) { this.mHerdName = herdName; }

    @Bindable
    public String getHerdName() { return this.mHerdName; }

    @Bindable
    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }
}
