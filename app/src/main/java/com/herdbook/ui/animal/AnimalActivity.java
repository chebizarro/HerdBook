package com.herdbook.ui.animal;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import android.os.Bundle;

import com.herdbook.R;
import com.herdbook.databinding.AnimalActivityBinding;
import com.herdbook.util.ActivityUtils;

import javax.inject.Inject;

public class AnimalActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<AnimalFragment> animalFragmentProvider;

    private AnimalActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = AnimalActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AnimalFragment animalFragment = (AnimalFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (savedInstanceState == null) {
            animalFragment = animalFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), animalFragment, R.id.container);
        }
    }
}