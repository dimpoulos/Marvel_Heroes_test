package com.codehub.marvelheroesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codehub.marvelheroesapp.MainActivity;
import com.codehub.marvelheroesapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private void gotoNextScreen() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    private void gotoMainScreen() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    private void gotoConfirmPass(){
        Intent intent =  new Intent(LoginActivity.this, ConfirmPassActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button register_now = findViewById(R.id.register_now);
        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(RegisterActivity.class);
            }
        });

        final TextInputLayout e1,e2;

        Button b1;
        final Database db;
        db = new Database(this);
        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.password);
        b1 = (Button)findViewById(R.id.login_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = e1.getEditText().getText().toString().trim();
                String password = e2.getEditText().getText().toString().trim();
                Boolean chkemailpass = db.emailpassword(username,password);
                if(chkemailpass==true) {
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    gotoMainScreen();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button forgot_pass = findViewById(R.id.forgot_pass);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ConfirmPassActivity.class);
            }
        });
    }

    private void gotoActivity(Class activityName) {
        Intent intent = new Intent(LoginActivity.this, activityName);
        startActivity(intent);
    }
}
