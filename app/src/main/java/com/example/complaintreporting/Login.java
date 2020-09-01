package com.example.complaintreporting;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTexPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    MyHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new MyHelper(this);
        mTextUsername=findViewById(R.id.username);
        mTexPassword=findViewById(R.id.pwd);
        mButtonLogin=findViewById(R.id.login);
        mTextViewRegister=findViewById(R.id.button_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent= new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String password = mTexPassword.getText().toString().trim();
                Boolean res= db.checkUser(user, password);
                if(res==true){
                    Toast.makeText(Login.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Login.this,DB_handler.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(Login.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}