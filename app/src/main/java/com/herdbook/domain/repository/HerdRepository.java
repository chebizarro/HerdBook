package com.herdbook.domain.repository;

import static com.herdbook.data.source.local.model.DBMapper.mapHerdDto;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.HerdDataSource;
import com.herdbook.data.source.Local;
import com.herdbook.data.source.Remote;
import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.local.model.DBMapper;
import com.herdbook.domain.model.Herd;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Flowable;

@Singleton
public class HerdRepository {

    private final HerdDataSource mHerdRemoteDataSource;
    private final HerdDataSource mHerdLocalDataSource;

    @VisibleForTesting
    @Nullable
    Map<Integer, Herd> mCachedHerds;

    @VisibleForTesting
    boolean mCacheIsDirty = false;

    @Inject
    HerdRepository(@Remote HerdDataSource herdRemoteDataSource,
                   @Local HerdDataSource herdLocalDataSource) {
        mHerdLocalDataSource = herdLocalDataSource;
        mHerdRemoteDataSource = herdRemoteDataSource;
    }


    public Flowable<List<Herd>> getHerds() {
        if (mCachedHerds != null && mCacheIsDirty) {
            return Flowable.fromIterable(mCachedHerds.values()).toList().toFlowable();
        } else if (mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }

        Flowable<List<Herd>> remoteHerds = getAndSaveRemoteHerds();

        if(mCacheIsDirty) {
            return remoteHerds;
        } else {
            Flowable<List<Herd>> localHerds = getAndCacheLocalHerds();
            return Flowable.concat(localHerds, remoteHerds)
                    .filter(herds -> !herds.isEmpty())
                    .firstOrError()
                    .toFlowable();
        }
    }

    public Flowable<List<HerdWithAnimals>> getHerdsWithAnimals() {
        return mHerdLocalDataSource.getHerdsWithAnimals();
    }

    private Flowable<List<Herd>> getAndCacheLocalHerds() {
        return mHerdLocalDataSource.getHerds()
                .flatMap(herds -> Flowable.fromIterable(herds)
                        .map(DBMapper::mapHerdDto)
                        .doOnNext(herd -> mCachedHerds.put(herd.getId(), herd))
                        .toList()
                        .toFlowable());
    }

    private Flowable<List<Herd>> getAndSaveRemoteHerds() {
        return mHerdRemoteDataSource
                .getHerds()
                .flatMap(herds -> Flowable.fromIterable(herds)
                        .map(DBMapper::mapHerdDto)
                        .doOnNext(herd -> {
                    //mHerdLocalDataSource.saveHerd(herd);
                    mCachedHerds.put(herd.getId(), herd);
                }).toList().toFlowable())
                .doOnComplete(() -> mCacheIsDirty = false);

    }

    public Flowable<Optional<Herd>> getHerd(int herdId) {

        final Herd cachedHerd = getHerdWithID(herdId);

        if (cachedHerd != null) {
            return Flowable.just(Optional.of(cachedHerd));
        }

        if(mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }

        Flowable<Optional<Herd>> localHerd = getHerdWithIdFromLocalRepository(herdId);
        Flowable<Optional<Herd>> remoteHerd = mHerdRemoteDataSource
                .getHerd(herdId)
                .map(herd -> Optional.of(mapHerdDto(herd.get())))
                .doOnNext(mapOptional -> {
                    if (mapOptional.isPresent()) {
                        Herd herd = mapOptional.get();
                        //mHerdLocalDataSource.saveHerd(herd);
                        mCachedHerds.put(herd.getId(), herd);
                    }
                });

        return Flowable.concat(localHerd, remoteHerd)
                .firstElement()
                .toFlowable();
    }

    private Flowable<Optional<Herd>> getHerdWithIdFromLocalRepository(int herdId) {
        return mHerdLocalDataSource
                .getHerd(herdId)
                .map(herd -> Optional.of(mapHerdDto(herd.get())))
                .doOnNext(herdOptional -> {
                    if (herdOptional.isPresent()) {
                        mCachedHerds.put(herdId, herdOptional.get());
                    }
                })
                .firstElement().toFlowable();
    }

    private Herd getHerdWithID(int herdId) {
        if (mCachedHerds == null || mCachedHerds.isEmpty()) {
            return null;
        } else {
            return mCachedHerds.get(herdId);
        }
    }

    public void saveHerd(@NonNull Herd herd) {
        //mHerdRemoteDataSource.saveHerd(herd);
        //mHerdLocalDataSource.saveHerd(herd);

        if(mCachedHerds == null) {
            mCachedHerds = new LinkedHashMap<>();
        }
        mCachedHerds.put(herd.getId(), herd);
    }
}
