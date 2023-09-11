package com.riham.sanaabilstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.riham.sanaabilstore.R;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, Sanaabil Sports");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Sanaabil_Sports", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Sanaabil_Sports", "Failed to read value.", error.toException());
            }
        });

    }
    public void onButtonSignUpClicked(View view){
        Intent intent = new Intent(WelcomePage.this, SignUp.class);
        startActivity(intent);
    }

    public void onButtonSignInClicked(View view){
        Intent intent = new Intent(WelcomePage.this, SignIn.class);
        startActivity(intent);
    }
}