package com.herdbook.ui;

import androidx.annotation.VisibleForTesting;

import com.herdbook.data.source.HerdRepository;
import com.herdbook.ui.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.HasAndroidInjector;

public class HerdBookApplication extends DaggerApplication implements HasAndroidInjector {

    @Inject
    HerdRepository mHerdRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    /**
     * Our Espresso tests need to be able to get an instance of the {@link HerdRepository}
     * so that we can delete all herds before running each test
     */
    @VisibleForTesting
    public HerdRepository getHerdRepository() { return mHerdRepository; }

}
