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

public class Employee_Activity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        BottomNavigationView bnv= findViewById(R.id.bottomNavigationView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    startActivity(new Intent(Employee_Activity.this, MainActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_cart) {
                    startActivity(new Intent(Employee_Activity.this, Cart_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_report) {
                    startActivity(new Intent(Employee_Activity.this,Report_Activity.class));
                    return true;
                }

                else if (item.getItemId() == R.id.menu_aboutus) {
                    startActivity(new Intent(Employee_Activity.this, Aboutus_Activity.class));
                    return true;
                }
                else {
                    Toast.makeText(Employee_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });
    }
}