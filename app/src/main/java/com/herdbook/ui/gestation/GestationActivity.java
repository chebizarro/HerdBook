package com.herdbook.ui.gestation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.herdbook.R;
import com.herdbook.ui.gestation.GestationFragment;

public class GestationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestation_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GestationFragment.newInstance())
                    .commitNow();
        }
    }
}