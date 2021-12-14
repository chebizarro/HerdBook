package com.herdbook.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.herdbook.R;
import com.herdbook.databinding.MainActivityBinding;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @Inject
    Lazy<MainFragment> mainFragmentProvider;

    private MainActivityBinding binding;

    private Toolbar mToolbar;

    private NavController mNavController;

    private NavigationView mNavigationView;

    private DrawerLayout mDrawerLayout;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setupNavigation();
    }

    private void setupNavigation() {

        CollapsingToolbarLayout layout = findViewById(R.id.toolbar_layout);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mNavigationView = findViewById(R.id.nav_view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        mNavController = navHostFragment.getNavController();

        mAppBarConfiguration =
                new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        NavigationUI.setupWithNavController(layout, mToolbar, mNavController, mAppBarConfiguration);


        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout);

        NavigationUI.setupWithNavController(mNavigationView, mNavController);

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        mDrawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.action_mainFragment_to_animalEditFragment:
                mNavController.navigate(R.id.animalEditFragment);
                break;
        }
        return true;

    }


}