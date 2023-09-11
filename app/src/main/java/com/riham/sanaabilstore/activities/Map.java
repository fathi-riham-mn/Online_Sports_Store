package com.riham.sanaabilstore.activities;


import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.FragmentActivity;
import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.riham.sanaabilstore.R;


public class Map extends FragmentActivity implements OnMapReadyCallback {
    FrameLayout map;
    GoogleMap gMap;
    Location currentLocation;
    Marker marker;
    FusedLocationProviderClient fusedClient;
    private static final int REQUEST_CODE = 101;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = findViewById(R.id.map);


                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(Map.this);



    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        LatLng mapSriLanka = new LatLng(7.8731, 80.7718);
        this.gMap.addMarker(new MarkerOptions().position(mapSriLanka).title("Marker SriLanka"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapSriLanka));

    }
    
}
