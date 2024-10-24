package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    CardView paalaiCard;
    CardView ummiCar;

    Button butn1;
    Button butn2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Initialize views
        paalaiCard = findViewById(R.id.paalaiCard);
        ummiCar = findViewById(R.id.ummiCard);
        butn1 = findViewById(R.id.btn1);
        butn2 = findViewById(R.id.btn2);
        BottomNavigationView bnv= findViewById(R.id.bottomNavigationView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_cart) {
                    startActivity(new Intent(MainActivity.this, Cart_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_report) {
                    startActivity(new Intent(MainActivity.this,Report_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_aboutus) {
                    startActivity(new Intent(MainActivity.this, Aboutus_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.bot_logout) {
                    startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                    return true;
                }
                else {
                    Toast.makeText(MainActivity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });


        // Button click listeners
        butn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass product information to CartActivity
                Intent intent = new Intent(MainActivity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });

        butn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass product information to CartActivity
                Intent intent = new Intent(MainActivity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });


        // CardView click listeners
        paalaiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, PaalaiActivity.class);
                startActivity(in);
            }
        });

        ummiCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this, UmmiActivity.class);
                startActivity(inte);
            }
        });
    }


}
