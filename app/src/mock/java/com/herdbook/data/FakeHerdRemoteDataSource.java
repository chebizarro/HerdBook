package com.herdbook.data;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.HerdDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class FakeHerdRemoteDataSource implements HerdDataSource {

    @Inject
    public FakeHerdRemoteDataSource() {

    }

    @Override
    public Flowable<List<DBHerd>> getHerds() {
        return null;
    }

    @Override
    public Flowable<List<HerdWithAnimals>> getHerdsWithAnimals() {
        return null;
    }

    @Override
    public Flowable<Optional<DBHerd>> getHerd(int herdId) {
        return null;
    }

    @Override
    public void saveHerd(@NonNull DBHerd herd) {

    }
}
