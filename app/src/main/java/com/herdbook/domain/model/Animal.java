package com.herdbook.domain.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.herdbook.BR;

public class Animal extends BaseObservable {

    @NonNull
    private final String id;

    @NonNull
    private String name;

    public Animal(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Bindable
    @NonNull
    public String getId() {
        return id;
    }

    @Bindable
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }
    }
}
