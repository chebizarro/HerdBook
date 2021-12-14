package com.herdbook.data.source.remote;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.models.Animal;
import com.herdbook.data.models.Herd;
import com.herdbook.data.source.HerdDataSource;

import java.util.List;
import java.util.Map;

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
    public Flowable<Map<Herd, List<Animal>>> getHerdsAndAnimals() {
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
