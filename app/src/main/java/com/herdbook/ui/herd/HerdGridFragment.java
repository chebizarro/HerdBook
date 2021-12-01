package com.herdbook.ui.herd;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herdbook.R;

import dagger.android.support.DaggerFragment;

public class HerdGridFragment extends DaggerFragment {

    private HerdGridViewModel mViewModel;

    public static HerdGridFragment newInstance() {
        return new HerdGridFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.herd_grid_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HerdGridViewModel.class);
        // TODO: Use the ViewModel
    }

}