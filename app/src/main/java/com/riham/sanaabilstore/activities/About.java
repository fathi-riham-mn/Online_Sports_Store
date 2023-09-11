package com.riham.sanaabilstore.activities;


import android.os.Bundle;
import android.view.WindowManager;
import com.riham.sanaabilstore.databinding.ActivityAboutBinding;

public class About extends DrawerBase {

    ActivityAboutBinding activityAboutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutBinding.getRoot());
        allocateActivityTitle("About");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}