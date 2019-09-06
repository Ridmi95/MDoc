package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    EditText username, password;
    DBconnection dBconnection;
    Daologin login = new Daologin();
    Button btnlogin, btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        //getSupportActionBar().setTitle("Login");

        dBconnection = new DBconnection(this);

        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnreg = findViewById(R.id.btnreg_log);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString().trim();
                String upass = password.getText().toString().trim();
                Log.i("username",uname);
                Log.i("password",upass);
                if(uname.equals("admin") && upass.equals("admin"))
                {
                    Intent intent = new Intent(Login_Form.this,MainNavigationActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void viewregister(View view) {

        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }
}