package com.riham.sanaabilstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.riham.sanaabilstore.R;

public class ForgotPassword extends AppCompatActivity {

    EditText editTextForgotEmail;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextForgotEmail = (EditText)findViewById(R.id.editTextForgotEmail);
        progressBar=(ProgressBar)findViewById(R.id.progressBar6);
        mAuth = FirebaseAuth.getInstance();

    }
    public void onResetButtonClicked(View v){

        resetPassWord();
    }


    private void  resetPassWord(){
        String email = editTextForgotEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            editTextForgotEmail.setError("Email is required");
            return;

        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this,
                                    "Password reset email sent",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassword.this, SignIn.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(ForgotPassword.this,
                                    "Failed to send password reset email",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}