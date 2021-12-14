package com.herdbook.data.source.local;

import android.content.Context;

import com.google.common.base.Optional;
import com.herdbook.data.DAO.HerdDao;
import com.herdbook.data.models.Animal;
import com.herdbook.data.models.Herd;
import com.herdbook.data.source.HerdDataSource;
import com.herdbook.data.source.HerdRoomDatabase;
import com.herdbook.util.schedulers.BaseSchedulerProvider;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.annotations.NonNull;
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
    public Flowable<List<Herd>> getHerds() {
        return mHerdDao.getHerds();
    }

    @Override
    public Flowable<Map<Herd, List<Animal>>> getHerdsAndAnimals() {
        return null;
    }

    @Override
    public Flowable<Optional<Herd>> getHerd(int herdId) {
        return Flowable.just(Optional.of(mHerdDao.getHerd(herdId)));
    }

    @Override
    public void saveHerd(@androidx.annotation.NonNull Herd herd) {
        mHerdDao.insert(herd);
    }
}
