package com.herdbook.data.DAO;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.herdbook.data.models.Animal;
import com.herdbook.data.models.Herd;

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
