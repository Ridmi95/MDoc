package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    Daoregister register = new Daoregister();
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


                Toast toast;

                String uname = username.getText().toString().trim();
                String upass = password.getText().toString().trim();
                Log.i("username",uname);
                Log.i("password",upass);
                if(uname.equals("admin") && upass.equals("admin"))
                {
                    Intent intent = new Intent(Login_Form.this,MainNavigationActivity.class);
                    startActivity(intent);

                }
              else if(uname.equals("d") && upass.equals("d")){
                    Intent intent = new Intent(Login_Form.this,DocHomeActivity.class);
                    startActivity(intent);

                }

                else if (TextUtils.isEmpty(username.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    register.setEmail(username.getText().toString().trim());
                    register.setPassword(password.getText().toString().trim());


                    if (dBconnection.checkLogin(register) == true) {
                        toast = Toast.makeText(getApplicationContext(), " Successfully login ", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(Login_Form.this,MainHome.class);

                        Log.i("cdscd",username.getText().toString().trim());
                        SharedPreferences.Editor editor = getSharedPreferences("userPreference",0).edit();
                        editor.putString("userEmail",username.getText().toString());
                        editor.commit();
                        startActivity(intent);

                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error in login", Toast.LENGTH_LONG);
                        toast.show();

                    }



                

            }
        }});

    }

    public void viewregister(View view) {

        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }


}