package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login_Form extends AppCompatActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        //getSupportActionBar().setTitle("Login");

        username = findViewById(R.id.username);
        password = findViewById(R.id.userpassword);
    }

    public void login(View view) {

        String uname,upass;
        Intent intent;

      //  uname = username.getText().toString();
      //  upass = password.getText().toString();

       // if(uname.equals("admin") && upass.equals("admin"))
      //  {

            intent = new Intent(Login_Form.this, MainNavigationActivity.class);
            startActivity(intent);

      //  }else if(uname.equals("customer") && upass.equals("customer"))
//        {
//            intent = new Intent(this, register.class);
//            startActivity(intent);
//        }



    }

    public void signup(View view) {
        Intent intent = new Intent(this,myProfile.class);
        startActivity(intent);
    }
}


//signup(View view)