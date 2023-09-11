package com.riham.sanaabilstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.riham.sanaabilstore.R;

public class SignUp extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPhoneNumber;
    EditText editTextPassword;


    String emailPattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;

    FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhoneNumber = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextSignUpPassword);
        progressDialog= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


    }

    public void onSignUpButtonClicked(View v){
        String userName = editTextUserName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            editTextUserName.setError("Please enter your name!");
            editTextUserName.requestFocus();
            return;
        }

        if (!email.matches(emailPattern)) {
            editTextEmail.setError("Please enter your email!");
            editTextEmail.requestFocus();
            return;
        } else if (email.isEmpty()) {
            editTextEmail.setError("Please enter your email!");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhoneNumber.setError("Please enter your phone number!");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Please enter your password!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 characters long!");
            editTextPassword.requestFocus();
            return;
        }
        else {
           progressDialog.setMessage("Please Wait While Registration....");
           progressDialog.setTitle("Registration");
           progressDialog.setCanceledOnTouchOutside(false);
           progressDialog.show();
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(userName, email, phone, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(SignUp.this, Dashboard.class);
                                                startActivity(intent);
                                            }
                                            else {

                                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                                    Toast.makeText(SignUp.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(SignUp.this, "User Failed to Register", Toast.LENGTH_SHORT).show();

                                                }
                                                progressDialog.dismiss();
                                            }

                                        }
                                    });

                        }
                        else {
                            Toast.makeText(SignUp.this,"User Failed to Register",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                });

    }

    public void TextSignInClicked(View v){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}