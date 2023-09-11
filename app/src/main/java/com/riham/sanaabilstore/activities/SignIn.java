package com.riham.sanaabilstore.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.riham.sanaabilstore.R;

public class SignIn extends AppCompatActivity {

    EditText signInEmail;
    EditText signInPassword;
    TextView textForgotPassword;
    TextView textRegister;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String emailPattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signInEmail = findViewById(R.id.editTextSignInEmail);
        signInPassword = findViewById(R.id.editTextSignInPassword);
        textForgotPassword = findViewById(R.id.textForgotPassword);
        textRegister = findViewById(R.id.textRegister);
        progressDialog= new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void textForgotPasswordClicked(View v) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }

    public void textRegisterClicked(View v) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignIn.this, Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void signInButtonClicked(View v) {
        String inputEmail = signInEmail.getText().toString().trim();
        String inputPassword = signInPassword.getText().toString().trim();

        if (!inputEmail.matches(emailPattern)) {
            signInEmail.setError("Enter the Correct Email");
        } else if (inputPassword.isEmpty() || inputPassword.length() < 6) {
            signInPassword.setError("Enter the Correct Password");
        } else {
            progressDialog.setMessage("Please Wait While Login.....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(SignIn.this, "Login Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
