package com.herdbook.ui.herd;

import android.os.Bundle;

import com.herdbook.R;
import com.herdbook.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class HerdActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<HerdFragment> herdFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.herd_activity);

        HerdFragment herdFragment =
                (HerdFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (herdFragment == null) {
            herdFragment = herdFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), herdFragment, R.id.fragmentContainer);
        }
    }
}