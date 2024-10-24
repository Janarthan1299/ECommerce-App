package com.example.ecommerceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.R;

public class InsertActivity extends AppCompatActivity {

    EditText edt_name, edt_position;
    Button btn_submit;

    String sname="", sposition="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //calling text boxes
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_position = (EditText)findViewById(R.id.edt_position);

        //calling button
        btn_submit = (Button) findViewById(R.id.btn_submit);


        //action listener to take in value and send it to database
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_name.getText().toString().trim().isEmpty() ||
                        edt_position.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(),"Enter value", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sname = edt_name.getText().toString().trim();
                    sposition= edt_position.getText().toString().trim();
/*
                    Staff staff = new Staff(sname,sposition);
                    StaffRepository staffRepository = new StaffRepository(getApplicationContext());
                    staffRepository.InsertTask(staff);
                    finish();*/
                }

            }
        });




    }


    }
