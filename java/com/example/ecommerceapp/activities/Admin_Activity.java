package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_Activity extends AppCompatActivity {

    private EditText paalaiStockEditText;
    private EditText ummiStockEditText;
    private DatabaseHelper databaseHelper;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        databaseHelper = new DatabaseHelper(this);
        paalaiStockEditText = findViewById(R.id.paalai_stock);
        ummiStockEditText = findViewById(R.id.ummi_stock);
        dbHelper = new DatabaseHelper(this);

        BottomNavigationView bnv1 = findViewById(R.id.bottomNavigationView1);
        bnv1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item1) {
                if (item1.getItemId() == R.id.home_admin) {
                    startActivity(new Intent(Admin_Activity.this, Admin_Activity.class));
                    return true;
                } else if (item1.getItemId() == R.id.Stock) {
                    startActivity(new Intent(Admin_Activity.this, Stock_Activity.class));
                    return true;
                } else if (item1.getItemId() == R.id.emp_det) {
                    startActivity(new Intent(Admin_Activity.this, Emp_Activity.class));
                    return true;
                } else if (item1.getItemId() == R.id.nav_logout) {
                    startActivity(new Intent(Admin_Activity.this, RegistrationActivity.class));
                    return true;
                } else {
                    Toast.makeText(Admin_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });


        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStockInformation();
            }

            private void saveStockInformation() {
                String paalaiStock = paalaiStockEditText.getText().toString().trim();
                String ummiStock = ummiStockEditText.getText().toString().trim();

                if (paalaiStock.isEmpty() || ummiStock.isEmpty()) {
                    Toast.makeText(Admin_Activity.this, "Please enter stock information for both items", Toast.LENGTH_SHORT).show();
                    return;
                }

                int newPaalaiStock = Integer.parseInt(paalaiStock);
                int newUmmiStock = Integer.parseInt(ummiStock);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Update stock for 'Paalai'
                updateStock(db, "Paalai", newPaalaiStock);

                // Update stock for 'Ummi'
                updateStock(db, "Ummi", newUmmiStock);

                db.close();

                Toast.makeText(Admin_Activity.this, "Stock information saved successfully", Toast.LENGTH_SHORT).show();

                // Clear EditText fields after saving
                paalaiStockEditText.setText("");
                ummiStockEditText.setText("");
            }

            private void updateStock(SQLiteDatabase db, String itemName, int newStockQuantity) {
                // Check current stock in the database
                int currentStock = getCurrentStock(db, itemName);

                // Calculate total stock (old stock + new stock)
                int totalStock = currentStock + newStockQuantity;

                // Update the stock quantity in the database
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_ITEM_NAME, itemName);
                values.put(DatabaseHelper.COLUMN_STOCK_QUANTITY, totalStock);

                db.update(DatabaseHelper.TABLE_STOCK, values,
                        DatabaseHelper.COLUMN_ITEM_NAME + " = ?", new String[]{itemName});
            }

            @SuppressLint("Range")
            private int getCurrentStock(SQLiteDatabase db, String itemName) {
                int currentStock = 0;

                Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COLUMN_STOCK_QUANTITY +
                        " FROM " + DatabaseHelper.TABLE_STOCK +
                        " WHERE " + DatabaseHelper.COLUMN_ITEM_NAME + " = ?", new String[]{itemName});

                if (cursor != null && cursor.moveToFirst()) {
                    currentStock = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STOCK_QUANTITY));
                    cursor.close();
                }

                return currentStock;
            }

            private void updateOrInsertStock(SQLiteDatabase db, String itemName, int quantity) {
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_ITEM_NAME, itemName);
                values.put(DatabaseHelper.COLUMN_STOCK_QUANTITY, quantity);

                // Check if the item already exists in the database
                Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_STOCK +
                        " WHERE " + DatabaseHelper.COLUMN_ITEM_NAME + " = ?", new String[]{itemName});

                if (cursor != null && cursor.getCount() > 0) {
                    // Item exists, update the stock quantity
                    db.update(DatabaseHelper.TABLE_STOCK, values,
                            DatabaseHelper.COLUMN_ITEM_NAME + " = ?", new String[]{itemName});
                } else {
                    // Item does not exist, insert a new row
                    db.insert(DatabaseHelper.TABLE_STOCK, null, values);
                }

                if (cursor != null) {
                    cursor.close();
                }

                Toast.makeText(Admin_Activity.this, "Stock information saved successfully", Toast.LENGTH_SHORT).show();

                // Clear EditText fields after saving
                paalaiStockEditText.setText("");
                ummiStockEditText.setText("");

            }
        });
    }

    }

