package com.herdbook.data.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.local.model.DBAnimal;

import java.util.List;

public class HerdWithAnimals {
    @Embedded
    public DBHerd herd;

    @Relation(
            parentColumn = "id",
            entityColumn = "herdId"
    )
    public List<DBAnimal> animals;

}
