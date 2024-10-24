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

public class Emp_Activity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        BottomNavigationView bnv1 = findViewById(R.id.bottomNavigationView1);
        bnv1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item1) {

                if (item1.getItemId() == R.id.home_admin) {
                    startActivity(new Intent(Emp_Activity.this, Admin_Activity.class));
                    return true;
                }
                else if (item1.getItemId() == R.id.Stock) {
                    startActivity(new Intent(Emp_Activity.this, Stock_Activity.class));
                    return true;
                }
                else if (item1.getItemId() == R.id.emp_det) {
                    startActivity(new Intent(Emp_Activity.this, Emp_Activity.class));
                    return true;
                } else if (item1.getItemId() == R.id.nav_logout) {
                    startActivity(new Intent(Emp_Activity.this, RegistrationActivity.class));
                    return true;
                }
                else {
                    Toast.makeText(Emp_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

    }
}