package com.herdbook.domain.repository;

import com.herdbook.data.source.HerdDataSource;
import com.herdbook.data.source.Local;
import com.herdbook.data.source.Remote;
import com.herdbook.domain.model.Animal;
import com.herdbook.domain.model.Herd;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class AnimalRepository {

    private final HerdDataSource mHerdRemoteDataSource;
    private final HerdDataSource mHerdLocalDataSource;


    @Inject
    AnimalRepository(@Remote HerdDataSource herdRemoteDataSource,
                     @Local HerdDataSource herdLocalDataSource) {
        mHerdLocalDataSource = herdLocalDataSource;
        mHerdRemoteDataSource = herdRemoteDataSource;
    }

    public Flowable<List<Animal>> getAnimals(Herd herd) {
        return null;
    }

}
