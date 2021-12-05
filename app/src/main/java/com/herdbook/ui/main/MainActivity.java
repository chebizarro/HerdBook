package com.herdbook.ui.main;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.herdbook.R;
import com.herdbook.databinding.MainActivityBinding;
import com.herdbook.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<MainFragment> mainFragmentProvider;

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar.getRoot();
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = binding.toolbarLayout;
        toolbarLayout.setTitle(getTitle());

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (mainFragment == null) {
            mainFragment = mainFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.fragmentContainer);
        }
    }
}