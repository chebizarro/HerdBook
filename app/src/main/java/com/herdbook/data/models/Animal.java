package com.herdbook.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="animal_table")
public class Animal {

    public enum Type {
        CATTLE,
        GOAT,
        HORSE,
        SHEEP;
    }

    public enum Sex {
        FEMALE,
        MALE,
        CASTRATED,
        FREEMARTIN,
        INTERSEX;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "herd")
    private Herd mHerd;

    @ColumnInfo(name = "type")
    private Type mType;

    @ColumnInfo(name = "dob")
    private Date mDob;

    @ColumnInfo(name = "sex")
    private Sex mSex;

    public Animal(@NonNull String name, @NonNull Herd herd, Type type) {
        this.mName = name;
        this.mHerd = herd;
        this.mType = type;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type mType) {
        this.mType = mType;
    }

    @NonNull
    public Herd getHerd() {
        return mHerd;
    }

    public void setHerd(@NonNull Herd mHerd) {
        this.mHerd = mHerd;
    }

    public Date getDob() {
        return mDob;
    }

    public void setDob(Date mDob) {
        this.mDob = mDob;
    }

    public Sex getSex() {
        return mSex;
    }

    public void setSex(Sex mSex) {
        this.mSex = mSex;
    }

}
