package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    TextView t1;
    EditText name,email,password;

    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth=FirebaseAuth.getInstance();


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        t1=(TextView)findViewById(R.id.Signin);
        Button b1=(Button)findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=name.getText().toString();
                String userEmail=email.getText().toString();
                String userPassword=password.getText().toString();

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegistrationActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(RegistrationActivity.this, "Enter Email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(userPassword)){
                    Toast.makeText(RegistrationActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userPassword.length() < 6){
                    Toast.makeText(RegistrationActivity.this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(RegistrationActivity.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                }else{
                                    Toast.makeText(RegistrationActivity.this, "Registration failed"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });



        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
        }
    }
