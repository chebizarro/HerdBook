package com.herdbook.data.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.herdbook.data.source.local.model.Animal;
import com.herdbook.data.source.local.model.Herd;

import java.util.List;

public class HerdWithAnimals {
    @Embedded
    public Herd herd;

    @Relation(
            parentColumn = "id",
            entityColumn = "herdId"
    )
    public List<Animal> animals;

}
