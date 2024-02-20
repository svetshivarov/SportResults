package com.example.sportresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Login {

    TextView registerTitle;
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextReenterPassword;
    EditText editTextEmailAddress;
    Button RegisterBtn;
    DBHelper db;

//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerTitle = findViewById(R.id.registerTitle);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextReenterPassword = findViewById(R.id.editTextReenterPassword);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        db = new DBHelper(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String repassword = editTextReenterPassword.getText().toString();
                String email = editTextEmailAddress.getText().toString();


                if(username.equals("")||password.equals("")||repassword.equals("")||email.equals(""))
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                        if (password.equals(repassword)) {
                            Boolean checkuser = db.checkusername(username);
                            if (checkuser == false) {
                                Boolean insert = db.insertData(username, password, email);
                                if (insert == true) {
                                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Register.this, "User already exists! Please enter another username or go to Login and sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
    }
}

