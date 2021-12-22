package com.herdbook.data.source.remote;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.local.model.Herd;
import com.herdbook.data.source.HerdDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Flowable;

@Singleton
public class HerdRemoteDataSource implements HerdDataSource {

    @Inject
    HerdRemoteDataSource() {
    }

    @Override
    public Flowable<List<Herd>> getHerds() {
        return Flowable.empty();
    }

    @Override
    public Flowable<List<HerdWithAnimals>> getHerdsWithAnimals() {
        return null;
    }

    @Override
    public Flowable<Optional<Herd>> getHerd(int herdId) {
        return Flowable.empty();
    }

    @Override
    public void saveHerd(@NonNull Herd herd) {

    }
}
