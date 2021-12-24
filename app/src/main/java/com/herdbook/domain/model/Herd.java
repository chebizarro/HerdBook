package com.herdbook.domain.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class Herd extends BaseObservable {

    private int id;

    @NonNull
    private String mHerdName;

    private List<Animal> mAnimals = new ArrayList<>();

    public Herd(@NonNull String herdName) { this.mHerdName = herdName; }

    @Bindable
    public String getHerdName() { return this.mHerdName; }

    @Bindable
    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public void addAnimal(@NonNull Animal animal) {
        mAnimals.add(animal);
    }

    public void addAnimals(@NonNull List<Animal> animals) {
        mAnimals.addAll(animals);
    }
}
