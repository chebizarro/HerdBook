package com.herdbook.ui.animal;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dagger.android.support.DaggerDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herdbook.R;
import com.herdbook.databinding.AnimalFragmentBinding;

public class AnimalFragment extends DaggerDialogFragment {

    private AnimalViewModel mViewModel;

    private AnimalFragmentBinding binding;

    public static AnimalFragment newInstance() {
        return new AnimalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AnimalFragmentBinding.inflate((getLayoutInflater()));
        View view = binding.getRoot();

        NavController navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        // TODO: Use the ViewModel
    }

}