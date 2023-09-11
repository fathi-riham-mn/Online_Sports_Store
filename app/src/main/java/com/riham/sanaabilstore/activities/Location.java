package com.riham.sanaabilstore.activities;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.databinding.ActivityLocationBinding;

public class Location extends DrawerBase {

    ActivityLocationBinding activityLocationBinding;

    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLocationBinding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(activityLocationBinding.getRoot());
        allocateActivityTitle("Location");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        showMap = findViewById(R.id.showMap);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Location.this, Map.class);
                startActivity(intent);
            }
        });
    }

}