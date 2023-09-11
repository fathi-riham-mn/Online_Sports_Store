package com.riham.sanaabilstore.activities;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.riham.sanaabilstore.R;

public class DrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view){
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activity_container);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar=drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

       drawerLayout.closeDrawer(GravityCompat.START);

       switch (item.getItemId()){
           case R.id.nav_video:
               startActivity(new Intent(this, Videos.class));
               overridePendingTransition(0,0);
               break;

           case R.id.nav_orders:
               startActivity(new Intent(this, CartActivity.class));
               overridePendingTransition(0,0);
               break;

           case R.id.nav_status:
               startActivity(new Intent(this, Delivery.class));
               overridePendingTransition(0,0);
               break;

           case R.id.nav_about:
               startActivity(new Intent(this, About.class));
               overridePendingTransition(0,0);
               break;

           case R.id.nav_location:
               startActivity(new Intent(this, Location.class));
               overridePendingTransition(0,0);
               break;

           case R.id.nav_logout:
               startActivity(new Intent(this, WelcomePage.class));
               overridePendingTransition(0,0);
               break;
       }
        return false;
    }
    protected void allocateActivityTitle(String titleString){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}