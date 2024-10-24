package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;

public class Qr_Activity extends AppCompatActivity {

    Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        back=findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac=new Intent(Qr_Activity.this, Cart_Activity.class);
                startActivity(bac);
            }
        });

        String totalPrice = getIntent().getStringExtra("totalPrice");

        // Find the TextView in your layout
        TextView totalPriceqr = findViewById(R.id.totalPriceqr);

        // Set the total price text to the TextView
        totalPriceqr.setText(totalPrice);
    }
}