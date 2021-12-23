package com.herdbook.data.source.local.model;

import com.herdbook.domain.model.Animal;
import com.herdbook.domain.model.Herd;

public final class DBMapper {

    public static Animal mapAnimalDto(DBAnimal dbAnimal) {
        return new Animal(dbAnimal.getName(), dbAnimal.getName());
    }

    public static Herd mapHerdDto(DBHerd dbHerd) {
        return new Herd(dbHerd.mHerdName);
    }

}
