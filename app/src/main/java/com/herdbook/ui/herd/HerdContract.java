package com.herdbook.ui.herd;

import com.herdbook.ui.BasePresenter;
import com.herdbook.ui.BaseView;

public interface HerdContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
