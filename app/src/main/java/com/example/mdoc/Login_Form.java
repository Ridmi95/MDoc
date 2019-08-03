package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        //getSupportActionBar().setTitle("Login");
    }

    public void signup(View view) {

        Intent intent = new Intent(this, register.class);
        startActivity(intent);

    }

    public void login(View view) {
        Intent intent = new Intent(this,myProfile.class);
        startActivity(intent);
    }
}
