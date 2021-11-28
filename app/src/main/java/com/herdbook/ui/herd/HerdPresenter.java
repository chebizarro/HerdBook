package com.herdbook.ui.herd;

import androidx.annotation.Nullable;

import javax.inject.Inject;

public class HerdPresenter implements HerdContract.Presenter {

    private static final String TAG = HerdPresenter.class.getSimpleName();

    @Nullable
    private HerdContract.View mHerdView;

    @Override
    public void takeView(HerdContract.View view) {
        mHerdView = view;
    }

    @Override
    public void dropView() {
        mHerdView = null;
    }

    @Inject
    public HerdPresenter() { }
}
