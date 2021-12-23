package com.herdbook.data.source;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.local.model.DBHerd;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HerdDataSource {

    Flowable<List<DBHerd>> getHerds();

    Flowable<List<HerdWithAnimals>> getHerdsWithAnimals();

    Flowable<Optional<DBHerd>> getHerd(int herdId);

    void saveHerd(@NonNull DBHerd herd);

}
