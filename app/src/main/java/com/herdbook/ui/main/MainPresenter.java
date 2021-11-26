package com.herdbook.ui.main;

import androidx.annotation.Nullable;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Nullable
    private MainContract.View mMainView;

    @Override
    public void takeView(MainContract.View view) {
       mMainView = view;
    }

    @Override
    public void dropView() {
        mMainView = null;
    }

}
