package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart_Activity extends AppCompatActivity {

    private Button addToCartButton;
    private Button addToCartButton1;
    private Button deleteFromCartButton;
    private Button deleteFromCartButton1;
    private ListView cartListView;
    private ListView cartListView1;
    private TextView totalPriceTextView;
    private TextView totalPriceTextView1;

    private DatabaseHelper dbHelper;


    private int quantityProductA = 0;
    private int quantityProductB = 0;

    //private int ummiCount=0;
    //private int paalaiCount=0;

    private static final double PRODUCT_A_RATE = 1100.00;
    private static final double PRODUCT_B_RATE = 1200.00;

    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");


    Button button4;
    Button button5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHelper = new DatabaseHelper(this);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.Button5);
        cartListView = findViewById(R.id.cartListView);
        cartListView1 = findViewById(R.id.cartListView1);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView1 = findViewById(R.id.totalPriceTextView1);
        addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton1 = findViewById(R.id.addToCartButton1);
        deleteFromCartButton = findViewById(R.id.deleteToCartButton);
        deleteFromCartButton1 = findViewById(R.id.deleteToCartButton1);

        BottomNavigationView bnv= findViewById(R.id.bottomNavigationView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    startActivity(new Intent(Cart_Activity.this, MainActivity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_cart) {
                    startActivity(new Intent(Cart_Activity.this, Cart_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_report) {
                    startActivity(new Intent(Cart_Activity.this,Report_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.menu_aboutus) {
                    startActivity(new Intent(Cart_Activity.this, Aboutus_Activity.class));
                    return true;
                }
                else if (item.getItemId() == R.id.bot_logout) {
                    startActivity(new Intent(Cart_Activity.this, RegistrationActivity.class));
                    return true;
                }
                else {
                    Toast.makeText(Cart_Activity.this, "Intent failed", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalPriceText = totalPriceTextView.getText().toString();
                // Create an intent to start Qr_Activity
                Intent qrIntent = new Intent(Cart_Activity.this, Qr_Activity.class);
                // Put the total price text as an extra to the intent
                qrIntent.putExtra("totalPrice", totalPriceText);
                // Start the activity
                startActivity(qrIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalPriceText = totalPriceTextView1.getText().toString();
                // Create an intent to start Qr_Activity
                Intent qrIntent = new Intent(Cart_Activity.this, Qr_Activity.class);
                // Put the total price text as an extra to the intent
                qrIntent.putExtra("totalPrice", totalPriceText);
                // Start the activity
                startActivity(qrIntent);
            }
        });


        final ArrayList<String> cartItems = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        cartListView.setAdapter(adapter);

        final ArrayList<String> cartItems1 = new ArrayList<>();
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        cartListView1.setAdapter(adapter1);

        addToCartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityProductB++;
                updateStock("Ummi", -1);
                double totalPrice1 = updateCartList1(cartItems1);
                totalPriceTextView1.setText("Total Price: $" + decimalFormat.format(totalPrice1));
               // ummiCount++;
               // ummi-ummiCount;
            }
        });


        deleteFromCartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityProductB--;
                updateStock("Ummi", 1);
                double totalprice1 = updateCartList1(cartItems1);
                totalPriceTextView1.setText(("total Price: $" + decimalFormat.format(totalprice1)));
               // ummiCount--;
               // ummi+ummiCount;
            }
        });


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the quantity of Product A and update the cart list
                quantityProductA++;
                updateStock("Paalai", -1);
                double totalPrice = updateCartList(cartItems);
                // Update total price
                totalPriceTextView.setText("Total Price: $" + decimalFormat.format(totalPrice));
               // paalaiCount++;
               // paalai-paalaiCount;
            }
        });
        deleteFromCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityProductA--;
                updateStock("Paalai", 1);
                double totalprice = updateCartList(cartItems);
                totalPriceTextView.setText(("total Price: $" + decimalFormat.format(totalprice)));
              //  paalaiCount--;
              //  paalai+paalaiCount;
            }
        });

    }


    private void updateStock(String itemName, int quantityChange) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Get the current stock quantity from the database
        int currentStock = getCurrentStock(db, itemName);

        // Calculate the new stock quantity after applying the change
        int newStockQuantity = currentStock + quantityChange;

        // Update the database with the new stock quantity
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_STOCK_QUANTITY, newStockQuantity);

        // Define the WHERE clause to update the specific item
        String whereClause = DatabaseHelper.COLUMN_ITEM_NAME + " = ?";
        String[] whereArgs = { itemName };

        // Perform the update operation
        db.update(DatabaseHelper.TABLE_STOCK, values, whereClause, whereArgs);

        // Close the database connection
        db.close();
    }

    @SuppressLint("Range")
    private int getCurrentStock(SQLiteDatabase db, String itemName) {
        int currentStock = 0;
        Cursor cursor = null;

        try {
            // Query the database to get the current stock quantity for the specified item
            String[] columns = { DatabaseHelper.COLUMN_STOCK_QUANTITY };
            String selection = DatabaseHelper.COLUMN_ITEM_NAME + " = ?";
            String[] selectionArgs = { itemName };

            cursor = db.query(
                    DatabaseHelper.TABLE_STOCK,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            // If the cursor has data, retrieve the stock quantity
            if (cursor != null && cursor.moveToFirst()) {
                currentStock = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_STOCK_QUANTITY));
            }
        } catch (Exception e) {
            // Handle any database query errors here
            e.printStackTrace();
        } finally {
            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }

        return currentStock;
    }




    // Method to update the cart list with added products and return the total price
        private double updateCartList (ArrayList < String > cartItems) {
            cartItems.clear();
            double totalPrice = (quantityProductA * PRODUCT_A_RATE) + (quantityProductB * PRODUCT_B_RATE);
            if (quantityProductA > 0) {
                cartItems.add("Product A: $" + decimalFormat.format(quantityProductA * PRODUCT_A_RATE));
            }
            // Add other products similarly if needed
            return totalPrice;
        }


        private double updateCartList1 (ArrayList < String > cartItems1) {
            cartItems1.clear();
            double totalPrice1 = (quantityProductA * PRODUCT_A_RATE) + (quantityProductB * PRODUCT_B_RATE);
            if (quantityProductB > 0) {
                cartItems1.add("Product B: $" + decimalFormat.format(quantityProductB * PRODUCT_B_RATE));
            }
            // Add other products similarly if needed
            return totalPrice1;
        }

    }

