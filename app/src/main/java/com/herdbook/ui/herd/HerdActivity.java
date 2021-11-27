package com.herdbook.ui.herd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.herdbook.R;
import com.herdbook.ui.herd.HerdFragment;

public class HerdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herd_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HerdFragment.newInstance())
                .commitNow();
        }
    }
}