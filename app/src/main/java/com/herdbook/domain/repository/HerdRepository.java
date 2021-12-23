package com.herdbook.domain.repository;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.HerdDataSource;
import com.herdbook.data.source.Local;
import com.herdbook.data.source.Remote;
import com.herdbook.data.source.local.model.DBHerd;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Flowable;

@Singleton
public class HerdRepository implements HerdDataSource {

    private final HerdDataSource mHerdRemoteDataSource;
    private final HerdDataSource mHerdLocalDataSource;

    @VisibleForTesting
    @Nullable
    Map<Integer, DBHerd> mCachedHerds;

    @VisibleForTesting
    boolean mCacheIsDirty = false;

    @Inject
    HerdRepository(@Remote HerdDataSource herdRemoteDataSource,
                   @Local HerdDataSource herdLocalDataSource) {
        mHerdLocalDataSource = herdLocalDataSource;
        mHerdRemoteDataSource = herdRemoteDataSource;
    }


    @Override
    public Flowable<List<DBHerd>> getHerds() {
        if (mCachedHerds != null && mCacheIsDirty) {
            return Flowable.fromIterable(mCachedHerds.values()).toList().toFlowable();
        } else if (mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }

        Flowable<List<DBHerd>> remoteHerds = getAndSaveRemoteHerds();

        if(mCacheIsDirty) {
            return remoteHerds;
        } else {
            Flowable<List<DBHerd>> localHerds = getAndCacheLocalHerds();
            return Flowable.concat(localHerds, remoteHerds)
                    .filter(herds -> !herds.isEmpty())
                    .firstOrError()
                    .toFlowable();
        }
    }

    @Override
    public Flowable<List<HerdWithAnimals>> getHerdsWithAnimals() {
        return mHerdLocalDataSource.getHerdsWithAnimals();
    }

    private Flowable<List<DBHerd>> getAndCacheLocalHerds() {
        return mHerdLocalDataSource.getHerds()
                .flatMap(herds -> Flowable.fromIterable(herds)
                        .doOnNext(herd -> mCachedHerds.put(herd.getId(), herd))
                        .toList()
                        .toFlowable());
    }

    private Flowable<List<DBHerd>> getAndSaveRemoteHerds() {
        return mHerdRemoteDataSource
                .getHerds()
                .flatMap(herds -> Flowable.fromIterable(herds).doOnNext(herd -> {
                    mHerdLocalDataSource.saveHerd(herd);
                    mCachedHerds.put(herd.getId(), herd);
                }).toList().toFlowable())
                .doOnComplete(() -> mCacheIsDirty = false);

    }

    @Override
    public Flowable<Optional<DBHerd>> getHerd(int herdId) {

        final DBHerd cachedHerd = getHerdWithID(herdId);

        if (cachedHerd != null) {
            return Flowable.just(Optional.of(cachedHerd));
        }

        if(mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }

        Flowable<Optional<DBHerd>> localHerd = getHerdWithIdFromLocalRepository(herdId);
        Flowable<Optional<DBHerd>> remoteHerd = mHerdRemoteDataSource
                .getHerd(herdId)
                .doOnNext(mapOptional -> {
                    if (mapOptional.isPresent()) {
                        DBHerd herd = mapOptional.get();
                        mHerdLocalDataSource.saveHerd(herd);
                        mCachedHerds.put(herd.getId(), herd);
                    }
                });

        return Flowable.concat(localHerd, remoteHerd)
                .firstElement()
                .toFlowable();
    }

    private Flowable<Optional<DBHerd>> getHerdWithIdFromLocalRepository(int herdId) {
        return mHerdLocalDataSource
                .getHerd(herdId)
                .doOnNext(herdOptional -> {
                    if (herdOptional.isPresent()) {
                        mCachedHerds.put(herdId, herdOptional.get());
                    }
                })
                .firstElement().toFlowable();
    }

    private DBHerd getHerdWithID(int herdId) {
        if (mCachedHerds == null || mCachedHerds.isEmpty()) {
            return null;
        } else {
            return mCachedHerds.get(herdId);
        }
    }

    @Override
    public void saveHerd(@NonNull DBHerd herd) {
        mHerdRemoteDataSource.saveHerd(herd);
        mHerdLocalDataSource.saveHerd(herd);

        if(mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }
        mCachedHerds.put(herd.getId(), herd);
    }
}
