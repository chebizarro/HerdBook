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

import com.herdbook.R;
import com.herdbook.data.models.Animal;
import com.herdbook.databinding.MainFragmentBinding;
import com.herdbook.ui.animal.AnimalGridAdapter;
import com.herdbook.ui.animal.AnimalGridViewAdapter;
import com.herdbook.ui.herd.HerdListSelectedListener;
import com.herdbook.ui.herd.HerdViewModel;
import com.herdbook.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends DaggerFragment implements MainContract.View, HerdListSelectedListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainFragmentBinding binding;

    private RecyclerView mRecyclerView;

    private AnimalGridAdapter mAdapter;

    private View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;

    private HerdViewModel viewModel;

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HerdViewModel.class);

        mRecyclerView = binding.mainRecyclerView;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));

        //Your RecyclerView.Adapter
        mAdapter = new AnimalGridAdapter(getActivity());

        //This is the code to provide a sectioned grid
        List<AnimalGridViewAdapter.Section> sections = new ArrayList<>();

        //Sections
        sections.add(new AnimalGridViewAdapter.Section(0,"Section 1"));
        sections.add(new AnimalGridViewAdapter.Section(5,"Section 2"));
        sections.add(new AnimalGridViewAdapter.Section(12,"Section 3"));
        sections.add(new AnimalGridViewAdapter.Section(14,"Section 4"));
        sections.add(new AnimalGridViewAdapter.Section(20,"Section 5"));

        //Add your adapter to the sectionAdapter
        AnimalGridViewAdapter.Section[] dummy = new AnimalGridViewAdapter.Section[sections.size()];
        AnimalGridViewAdapter mSectionedAdapter = new
                AnimalGridViewAdapter(getActivity(),R.layout.main_section_header,R.id.section_text,mRecyclerView,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);

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
    public void onAnimalSelected(Animal animal) {

    }
}