package com.herdbook.ui.herd;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.herdbook.R;
import com.herdbook.databinding.HerdFragmentBinding;
import com.herdbook.ui.di.ActivityScoped;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class HerdFragment extends DaggerFragment implements HerdContract.View {

    public static HerdFragment newInstance() {
        return new HerdFragment();
    }

    private HerdViewModel mViewModel;

    @Inject
    Context mContext;

    @Inject
    HerdContract.Presenter mPresenter;

    @Inject
    public HerdFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        HerdFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.herd_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }

}