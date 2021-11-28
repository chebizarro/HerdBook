package com.herdbook.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;
import dagger.android.support.DaggerFragment;

import com.herdbook.R;

public class MainFragment extends DaggerFragment implements MainContract.View {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }
}