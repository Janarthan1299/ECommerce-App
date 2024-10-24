package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_login_Activity extends AppCompatActivity {

    Button butn7;
    EditText email1, password1,codeword1;
    private FirebaseAuth auth;

    // Define admin email and password
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "638701";
    private static final String ADMIN_codeword = "The admin";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        butn7 = findViewById(R.id.button11);
        auth = FirebaseAuth.getInstance();

        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);
        codeword1 = findViewById(R.id.codeword1);

        butn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail1 = email1.getText().toString();
                String userPassword1 = password1.getText().toString();
                String codeword=codeword1.getText().toString();

                if (TextUtils.isEmpty(userEmail1) || TextUtils.isEmpty(userPassword1) || TextUtils.isEmpty(codeword)) {
                    Toast.makeText(Admin_login_Activity.this, "Enter Email,Password and codeword!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the email and password match the predefined admin credentials
                if (userEmail1.equals(ADMIN_EMAIL) && userPassword1.equals(ADMIN_PASSWORD) && codeword.equals(ADMIN_codeword)) {
                    // Sign in the admin user using Firebase Authentication
                    auth.signInWithEmailAndPassword(userEmail1, userPassword1)
                            .addOnCompleteListener(Admin_login_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Admin_login_Activity.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Admin_login_Activity.this, Admin_Activity.class));
                                        finish(); // Close login activity
                                    } else {
                                        Toast.makeText(Admin_login_Activity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // If the email or password does not match, show an error
                    Toast.makeText(Admin_login_Activity.this, "Invalid admin credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
