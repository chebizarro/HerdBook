package com.herdbook.data.source.local.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.herdbook.BR;

import java.util.Date;

@Entity(tableName="animal_table")
public class Animal extends BaseObservable {

    public enum Type {
        CATTLE,
        GOAT,
        HORSE,
        SHEEP
    }

    public enum Sex {
        FEMALE,
        MALE,
        CASTRATED,
        FREEMARTIN,
        INTERSEX
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "herdId")
    private int mHerdId;

    @ColumnInfo(name = "type")
    private Type mType;

    @ColumnInfo(name = "dob")
    private Date mDob;

    @ColumnInfo(name = "sex")
    private Sex mSex;

    public int getHerdId() {
        return mHerdId;
    }

    public int getSireId() {
        return mSireId;
    }

    public void setSireId(int mSireId) {
        this.mSireId = mSireId;
    }

    public int getDameId() {
        return mDameId;
    }

    public void setDameId(int mDameId) {
        this.mDameId = mDameId;
    }

    @ColumnInfo(name = "sire")
    private int mSireId;

    @ColumnInfo(name ="dame")
    private int mDameId;

    public Animal(@NonNull String name, int herdId, Type type) {
        this.mName = name;
        this.mHerdId = herdId;
        this.mType = type;
    }

    @Bindable
    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
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

    public void setHerd(int herdId) {
        this.mHerdId = herdId;
    }

    public Date getDob() {
        return mDob;
    }

    public void setDob(Date mDob) {
        this.mDob = mDob;
    }

    @NonNull
    public Sex getSex() {
        return mSex;
    }

    public void setSex(@NonNull Sex mSex) {
        this.mSex = mSex;
    }

}
