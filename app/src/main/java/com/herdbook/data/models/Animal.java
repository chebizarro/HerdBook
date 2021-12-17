package com.herdbook.data.models;

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

    public Animal getSire() {
        return mSire;
    }

    public void setSire(Animal mSire) {
        this.mSire = mSire;
    }

    public Animal getDame() {
        return mDame;
    }

    public void setDame(Animal mDame) {
        this.mDame = mDame;
    }

    public int getHerdId() {
        return mHerdId;
    }

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

    @Bindable
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

    @ColumnInfo(name = "sire")
    private Animal mSire;

    @ColumnInfo(name ="dame")
    private Animal mDame;

    public Animal(@NonNull String name, int herdId, Type type) {
        this.mName = name;
        this.mHerdId = herdId;
        this.mType = type;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
        notifyPropertyChanged(BR.name);
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
