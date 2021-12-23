package com.herdbook.data.source.local;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.herdbook.data.dao.HerdDao;
import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.data.source.local.model.DBHerd;
import com.herdbook.data.source.HerdDataSource;
import com.herdbook.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Flowable;

@Singleton
public class HerdLocalDataSource implements HerdDataSource {

    private BaseSchedulerProvider schedulerProvider;

    private HerdDao mHerdDao;

    @Inject
    public HerdLocalDataSource(@NonNull Context context, @NonNull BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        HerdRoomDatabase db = HerdRoomDatabase.getDatabase(context);
        mHerdDao = db.herdDao();
    }

    @Override
    public Flowable<List<DBHerd>> getHerds() {
        return mHerdDao.getHerds();
    }

    @Override
    public Flowable<List<HerdWithAnimals>> getHerdsWithAnimals() {
        return mHerdDao.loadHerdAndAnimals();
    }

    @Override
    public Flowable<Optional<DBHerd>> getHerd(int herdId) {
        return Flowable.just(Optional.of(mHerdDao.getHerd(herdId)));
    }

    @Override
    public void saveHerd(@NonNull DBHerd herd) {
        mHerdDao.insert(herd);
    }
}
