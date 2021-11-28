package com.herdbook.ui.herd;

import com.herdbook.ui.di.ActivityScoped;
import com.herdbook.ui.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link HerdPresenter}.
 */
@Module
public abstract class HerdModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HerdFragment herdFragment();

    @ActivityScoped
    @Binds
    abstract HerdContract.Presenter providePresenter(HerdPresenter presenter);
}
