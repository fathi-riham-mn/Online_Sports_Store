package com.riham.sanaabilstore.activities;



import android.os.Bundle;
import android.view.WindowManager;

import com.riham.sanaabilstore.databinding.ActivityDeliveryBinding;


public class Delivery extends DrawerBase {

    ActivityDeliveryBinding activityDeliveryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDeliveryBinding = ActivityDeliveryBinding.inflate(getLayoutInflater());
        setContentView(activityDeliveryBinding.getRoot());
        allocateActivityTitle("Delivery");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}