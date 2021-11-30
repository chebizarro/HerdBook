package com.herdbook.ui.herd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.herdbook.data.models.Herd;
import com.herdbook.data.source.HerdRepository;

import java.util.List;

import javax.inject.Inject;

public class HerdViewModel extends AndroidViewModel {

    @Inject
    HerdRepository mRepository;

    //private final LiveData<List<Herd>> allHerds;

    public HerdViewModel(Application application) {
        super(application);
        //allHerds = mRepository.getHerds();
    }

}