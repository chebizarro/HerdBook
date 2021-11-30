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
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "type")
    private Type mType;

    @ColumnInfo(name = "dob")
    private Date mDob;

    @ColumnInfo(name = "sex")
    private Sex mSex;

    public Animal(@NonNull String name, Type type) {
        this.mName = name;
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
