package com.herdbook.ui.main;


import com.herdbook.ui.BasePresenter;
import com.herdbook.ui.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
