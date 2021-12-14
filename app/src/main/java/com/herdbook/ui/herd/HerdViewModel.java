package com.herdbook.ui.herd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import com.herdbook.data.source.HerdRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class HerdViewModel extends AndroidViewModel {

    private final HerdRepository mRepository;

    private CompositeDisposable disposable;


    @Inject
    public HerdViewModel(Application application, HerdRepository herdRepository) {
        super(application);
        mRepository = herdRepository;
        disposable = new CompositeDisposable();

    }




    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}