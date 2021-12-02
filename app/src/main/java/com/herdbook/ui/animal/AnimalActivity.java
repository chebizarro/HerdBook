package com.herdbook.ui.animal;

import dagger.android.support.DaggerAppCompatActivity;
import android.os.Bundle;

import com.herdbook.R;

public class AnimalActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AnimalFragment.newInstance())
                .commitNow();
        }
    }
}