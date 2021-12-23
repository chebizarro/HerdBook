package com.herdbook.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;
import dagger.android.support.DaggerFragment;

import com.herdbook.data.source.local.model.DBAnimal;
import com.herdbook.databinding.MainFragmentBinding;
import com.herdbook.ui.herd.HerdListActionCallback;
import com.herdbook.ui.herd.HerdListAdapter;
import com.herdbook.ui.herd.HerdViewModel;
import com.herdbook.util.ViewModelFactory;


public class MainFragment extends DaggerFragment implements MainContract.View, HerdListActionCallback {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainFragmentBinding binding;

    private RecyclerView mRecyclerView;

    private HerdListAdapter mAdapter;

    private View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;

    private HerdViewModel viewModel;

    @Inject
    public MainFragment() { }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(HerdViewModel.class);

        mRecyclerView = binding.mainRecyclerView;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        //Your RecyclerView.Adapter
        mAdapter = new HerdListAdapter(viewModel, getViewLifecycleOwner(), this);

        mRecyclerView.setAdapter(mAdapter);

        loadingView = binding.loadingView;

        observableViewModel();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MainFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    private void observableViewModel() {
        viewModel.getHerds().observe(getViewLifecycleOwner(), repos -> {
            if(repos != null) mRecyclerView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) if(isError) {
                //errorTextView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                //errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                //errorTextView.setVisibility(View.GONE);
                //errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    //errorTextView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onAnimalSelected(DBAnimal animal) {

    }
}