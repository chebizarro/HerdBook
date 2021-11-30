package com.herdbook.ui.herd;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.herdbook.R;
import com.herdbook.databinding.HerdActivityBinding;
import com.herdbook.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class HerdActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<HerdFragment> herdFragmentProvider;

    private HerdActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = HerdActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar.getRoot();
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        HerdFragment herdFragment = (HerdFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (herdFragment == null) {
            herdFragment = herdFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), herdFragment, R.id.fragmentContainer);
        }
    }

}