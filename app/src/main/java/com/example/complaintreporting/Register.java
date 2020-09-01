package com.example.complaintreporting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    MyHelper db;
    EditText mTextUsername;
    EditText mTexPassword;
    EditText getTexCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new MyHelper(this);
        mTextUsername=findViewById(R.id.username);
        mTexPassword=findViewById(R.id.pwdd);
        getTexCnfPassword=findViewById(R.id.confirm_pwd);
        mButtonRegister=findViewById(R.id.btn_register);
        mTextViewLogin=findViewById(R.id.btn_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =mTextUsername.getText().toString().trim();
                String password =mTexPassword.getText().toString().trim();
                String conf_password =getTexCnfPassword.getText().toString().trim();

                if(password.equals(conf_password)){
                    long value= db.addUser(user,password);
                    if(value>0){
                        Toast.makeText(Register.this,"You have Registered Successfully",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(Register.this,Login.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(Register.this,"Registerion Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Register.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}