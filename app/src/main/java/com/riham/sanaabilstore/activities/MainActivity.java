package com.riham.sanaabilstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.riham.sanaabilstore.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        TextView textView;
        textView = findViewById(R.id.name1);
        textView.animate().translationX(1000).setDuration(1000).setStartDelay(2500);

        textView = findViewById(R.id.name2);
        textView.animate().translationX(1000).setDuration(1000).setStartDelay(2500);

        Thread thread = new Thread(){

            public void run(){
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(MainActivity.this,WelcomePage.class);
                    startActivity(intent);
                }
            }

        };
        thread.start();
    }
}