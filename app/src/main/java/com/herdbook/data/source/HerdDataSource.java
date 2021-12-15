package com.herdbook.data.source;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.DAO.HerdWithAnimals;
import com.herdbook.data.models.Herd;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HerdDataSource {

    Flowable<List<Herd>> getHerds();

    Flowable<List<HerdWithAnimals>> getHerdsAndAnimals();

    Flowable<Optional<Herd>> getHerd(int herdId);

    void saveHerd(@NonNull Herd herd);

}
