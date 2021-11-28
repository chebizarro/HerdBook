package com.herdbook.ui;

import com.herdbook.ui.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.HasAndroidInjector;

public class HerdBookApplication extends DaggerApplication implements HasAndroidInjector {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
