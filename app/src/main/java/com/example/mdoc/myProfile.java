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

public class myProfile extends AppCompatActivity {

    EditText myProfile_fristname,myProfile_lastname,myProfile_email,myProfile_nic,myProfile_mobile;
    DBconnection dBconnection;
    Daoregister myProfile = new Daoregister();
    Button btnupdate,btndelete,btnsave;
    Intent intent;
    String email;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        dBconnection = new DBconnection(this);

        // use shared preference
        pref = getApplication().getSharedPreferences("userPreference",0);
        SharedPreferences.Editor editor = pref.edit();
        email = pref.getString("userEmail",null);

        myProfile_fristname = findViewById(R.id.txtmyprofilefn);
        myProfile_lastname = findViewById(R.id.txtmyprofileln);
        myProfile_email = findViewById(R.id.txtmyprofileemail);
        myProfile_nic = findViewById(R.id.txtmyprofilenic);
        myProfile_mobile = findViewById(R.id.txtmyprofilemobile);
        btnupdate = findViewById(R.id.btnmyprofileupdate);
        btndelete = findViewById(R.id.btnmyprofileDelete);


        myProfile = dBconnection.getProfileDetails(email);
        myProfile_fristname.setText(myProfile.getFirstname());
        myProfile_lastname.setText(myProfile.getLastname());
        myProfile_email.setText(myProfile.getEmail());
        myProfile_nic.setText(myProfile.getNic());
        myProfile_mobile.setText(String.valueOf(myProfile.getContactnum()));


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                //validating the add specialization



                if(TextUtils.isEmpty(myProfile_fristname.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_LONG);
                    toast.show();
                } else if(TextUtils.isEmpty(myProfile_lastname.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter Last Name", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(TextUtils.isEmpty(myProfile_email.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter  nic", Toast.LENGTH_LONG);
                    toast.show();
                } else if(TextUtils.isEmpty(myProfile_nic.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter an email", Toast.LENGTH_LONG);
                    toast.show();
                }else if(TextUtils.isEmpty(myProfile_mobile.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG);
                    toast.show();

                }
                else{


                }

            }
        });



    }



}
