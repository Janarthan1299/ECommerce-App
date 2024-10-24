package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Aboutus_Activity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        BottomNavigationView bnv= findViewById(R.id.bottomNavigationView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    startActivity(new Intent(Aboutus_Activity.this, MainActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_cart) {
                    startActivity(new Intent(Aboutus_Activity.this, Cart_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_report) {
                    startActivity(new Intent(Aboutus_Activity.this, Report_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_aboutus) {
                    startActivity(new Intent(Aboutus_Activity.this, Aboutus_Activity.class));
                    return true;
                } else if (item.getItemId() == R.id.bot_logout) {
                    startActivity(new Intent(Aboutus_Activity.this, RegistrationActivity.class));
                    return true;
                }else {
                    Toast.makeText(Aboutus_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });
    }
}