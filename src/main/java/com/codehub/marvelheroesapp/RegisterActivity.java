package com.codehub.marvelheroesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codehub.marvelheroesapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private void gotoMainScreen() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private TextInputLayout name1;
    Database db;
    // EditText e1,e2,e3,e4,e5,e6;
    private TextInputLayout e1,e2,e3,e4,e5,e6;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Button register = findViewById(R.id.register_button);
//        Button sign_in = findViewById(R.id.sign_in);
//
//        register.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//
//        });
//
//        sign_in.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//
//        });

        db = new Database(this);
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.surname);
        e3=findViewById(R.id.email);
        e4=findViewById(R.id.username);
        e5=findViewById(R.id.password);
        e6=findViewById(R.id.confirmpass);
        b1=(Button)findViewById(R.id.register_button);
        b2=(Button)findViewById(R.id.sign_in);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getEditText().getText().toString().trim();
                String s2 = e2.getEditText().getText().toString().trim();
                String s3 = e3.getEditText().getText().toString().trim();
                String s4 = e4.getEditText().getText().toString().trim();
                String s5 = e5.getEditText().getText().toString().trim();
                String s6 = e6.getEditText().getText().toString().trim();
                if(s3.equals("") || s5.equals("") || s6.equals("") || s1.equals("") || s2.equals("") || s4.equals(""))  {
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s5.equals(s6)){
                        Boolean chkuser = db.chkuser(s4);
                        Boolean chkemail = db.chkemail(s3);
                        if (chkuser == true){
                            if(chkemail==true) /*chkuser == true)*/{
                                Boolean insert = db.insert(s3,s5,s4);
                                if(insert==true){
                                    Toast.makeText(getApplicationContext(), "Registered Successfully",Toast.LENGTH_SHORT).show();
                                    gotoMainScreen();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Email Already exists",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Username Already exists",Toast.LENGTH_SHORT).show();
                        }
                    }

                    else if (s5!=s6)
                        Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
