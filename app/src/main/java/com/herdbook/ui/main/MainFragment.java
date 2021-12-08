package com.herdbook.ui.main;

import static com.herdbook.util.ActivityUtils.calculateNoOfColumns;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;
import dagger.android.support.DaggerFragment;

import com.herdbook.databinding.MainFragmentBinding;
import com.herdbook.ui.herd.HerdGridAdapter;


public class MainFragment extends DaggerFragment implements MainContract.View {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainFragmentBinding binding;

    private HerdGridAdapter mHerdAdapter;

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);

        binding = MainFragmentBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        RecyclerView recyclerView = binding.mainRecyclerView;

        int numberOfColumns = calculateNoOfColumns(getContext(), 180);

        if (numberOfColumns == 0) {
            numberOfColumns = 1;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                numberOfColumns));

        //mHerdAdapter = new HerdGridAdapter(getContext());
        //recyclerView.setAdapter(mHerdAdapter);

        recyclerView.setHasFixedSize(true);

        return view;
    }
}