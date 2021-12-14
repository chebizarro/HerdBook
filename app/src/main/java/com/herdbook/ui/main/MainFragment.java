package com.herdbook.ui.main;

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

import com.herdbook.R;
import com.herdbook.databinding.MainFragmentBinding;
import com.herdbook.ui.animal.AnimalGridAdapter;
import com.herdbook.ui.animal.AnimalGridViewAdapter;
import com.herdbook.ui.herd.HerdGridAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends DaggerFragment implements MainContract.View {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainFragmentBinding binding;

    private RecyclerView mRecyclerView;

    private AnimalGridAdapter mAdapter;

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MainFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


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

        return view;
    }
}