package com.herdbook.ui.herd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herdbook.data.dao.HerdWithAnimals;
import com.herdbook.domain.repository.HerdRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class HerdViewModel extends AndroidViewModel {

    private final HerdRepository mRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<HerdWithAnimals>> herds = new MutableLiveData<>();
    private final MutableLiveData<Boolean> herdLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public HerdViewModel(Application application, HerdRepository herdRepository) {
        super(application);
        mRepository = herdRepository;
        disposable = new CompositeDisposable();
        fetchHerds();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    private void fetchHerds() {
        loading.setValue(true);
        disposable.add(mRepository.getHerdsWithAnimals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<HerdWithAnimals>>() {
                    @Override
                    public void onNext(List<HerdWithAnimals> herdWithAnimals) {
                        herdLoadError.setValue(false);
                        herds.setValue(herdWithAnimals);
                        loading.setValue(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        herdLoadError.setValue(true);
                        loading.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        loading.setValue(false);
                    }
                }));
    }


    public MutableLiveData<List<HerdWithAnimals>> getHerds() {
        return herds;
    }

    public MutableLiveData<Boolean> getError() {
        return herdLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

}