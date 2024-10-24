package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Stock_Activity extends AppCompatActivity {
    EditText paalaiStockEditText1;
    EditText ummiStockEditText1;
    private DatabaseHelper databaseHelper;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        dbHelper = new DatabaseHelper(this);
        databaseHelper = new DatabaseHelper(this);
        paalaiStockEditText1 = findViewById(R.id.stock_paalai);
        ummiStockEditText1 = findViewById(R.id.stock_ummi);


        BottomNavigationView bnv1= findViewById(R.id.bottomNavigationView1);
        bnv1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item1) {
                if (item1.getItemId() == R.id.home_admin) {
                    startActivity(new Intent(Stock_Activity.this, Admin_Activity.class));
                    return true;
                }
                else if (item1.getItemId() == R.id.Stock) {
                    startActivity(new Intent(Stock_Activity.this, Stock_Activity.class));
                    return true;
                }
                else if (item1.getItemId() == R.id.emp_det) {
                    startActivity(new Intent(Stock_Activity.this, Emp_Activity.class));
                    return true;
                }
                else if (item1.getItemId() == R.id.nav_logout) {
                    startActivity(new Intent(Stock_Activity.this, RegistrationActivity.class));
                    return true;
                }

                else {
                    Toast.makeText(Stock_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });

        retrieveAndDisplayStock();
    }

    private void insertStock(SQLiteDatabase db, String itemName, int quantity) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ITEM_NAME, itemName);
        values.put(DatabaseHelper.COLUMN_STOCK_QUANTITY, quantity);
        db.insert(DatabaseHelper.TABLE_STOCK, null, values);
    }

    private void retrieveAndDisplayStock() {
        // Retrieve stock information from the database
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_ITEM_NAME, DatabaseHelper.COLUMN_STOCK_QUANTITY};
        String selection = DatabaseHelper.COLUMN_ITEM_NAME + " IN (?, ?)";
        String[] selectionArgs = {"Paalai", "Ummi"};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_STOCK,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Display the retrieved stock information
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String itemName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ITEM_NAME));
                @SuppressLint("Range") int stockQuantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STOCK_QUANTITY));
                if (itemName.equals("Paalai")) {
                    paalaiStockEditText1.setText(String.valueOf(stockQuantity));
                } else if (itemName.equals("Ummi")) {
                    ummiStockEditText1.setText(String.valueOf(stockQuantity));
                }
            }
            cursor.close();
        }

    }

}