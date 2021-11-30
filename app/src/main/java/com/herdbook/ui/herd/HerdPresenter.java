package com.herdbook.ui.herd;

import androidx.annotation.Nullable;

import com.herdbook.data.source.HerdRepository;

import javax.inject.Inject;

public class HerdPresenter implements HerdContract.Presenter {

    @Nullable
    private HerdContract.View mHerdView;

    @Inject
    HerdRepository mHerdRepository;

    @Inject
    public HerdPresenter() { }

    @Override
    public void takeView(HerdContract.View view) {
        mHerdView = view;
    }

    @Override
    public void dropView() {
        mHerdView = null;
    }

}
