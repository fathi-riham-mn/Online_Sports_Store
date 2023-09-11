package com.riham.sanaabilstore.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.databinding.ActivityDashboardBinding;
import com.riham.sanaabilstore.fragments.HomeFragment;

public class Dashboard extends DrawerBase {

    private ActivityDashboardBinding activityDashboardBinding;
    Fragment homeFragment;


    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        homeFragment = new HomeFragment();
        loadFragment(homeFragment);
    }

    private void loadFragment(Fragment homeFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.commit();
    }



}