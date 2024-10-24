package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Report_Activity extends AppCompatActivity {

    private Button buttonSubmit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        buttonSubmit = findViewById(R.id.buttonSubmit);


        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    startActivity(new Intent(Report_Activity.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    startActivity(new Intent(Report_Activity.this, Cart_Activity.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_report) {
                    startActivity(new Intent(Report_Activity.this, Report_Activity.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_aboutus) {
                    startActivity(new Intent(Report_Activity.this, Aboutus_Activity.class));
                    return true;
                } else if (item.getItemId() == R.id.bot_logout) {
                    startActivity(new Intent(Report_Activity.this, RegistrationActivity.class));
                    return true;
                } else {
                    Toast.makeText(Report_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String UriText = "mailto:" +      Uri.encode("ecommerceappkec@gmail.com") +"?subject="+
                        Uri.encode("Feedback") +"$body="+ Uri.encode("");
                Uri uri = Uri.parse(UriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent,"send email"));
            }
        });


        }
    }

