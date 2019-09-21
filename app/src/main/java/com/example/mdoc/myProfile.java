package com.example.mdoc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Blob;

public class myProfile extends AppCompatActivity {

    EditText myProfile_fristname,myProfile_lastname,myProfile_email,myProfile_nic,myProfile_mobile;
    DBconnection dBconnection;
    Daoregister myProfile = new Daoregister();
    ImageView imageView;
    Button btnupdate,btndelete,btnichoose;
    Intent intent;
    String email;
    SharedPreferences pref;

    private  static  final int IMAGE_PICK_CODE = 1000;
    private  static final int PERMISSION_CODE = 1001;

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
        imageView = findViewById(R.id.myprofilepic);
        btnichoose = findViewById(R.id.Choose_image);
        btnupdate = findViewById(R.id.btnmyprofileupdate);
        btndelete = findViewById(R.id.btnmyprofileDelete);


        myProfile = dBconnection.getProfileDetails(email);
        myProfile_fristname.setText(myProfile.getFirstname());
        myProfile_lastname.setText(myProfile.getLastname());
        myProfile_email.setText(myProfile.getEmail());
        myProfile_nic.setText(myProfile.getNic());
        myProfile_mobile.setText(String.valueOf(myProfile.getContactnum()));

        btnichoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check the run time permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted,request it.

                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        //show popup for runtime permission

                        requestPermissions(permission, PERMISSION_CODE);
                    } else {

                        //permission already granted
                        pickImageFromGallery();
                    }


                }
                else {

                    pickImageFromGallery();
                }

            }

        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;


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
                else {



                        myProfile.setFirstname(myProfile_fristname.getText().toString().trim());
                        myProfile.setLastname(myProfile_lastname.getText().toString().trim());
                        myProfile.setContactnum(Integer.parseInt(myProfile_mobile.getText().toString().trim()));
                        myProfile.setEmail(myProfile_email.getText().toString().trim());
                        myProfile.setNic(myProfile_nic.getText().toString().trim());




                            if (dBconnection.updateProfile(myProfile)) {
                                toast = Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG);
                                toast.show();

                            } else {
                                toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                                toast.show();
                            }





                }



            }
        });

        //delete myprofile
        btndelete.setOnClickListener(new View.OnClickListener() {
            Toast toast;
            @Override
            public void onClick(View view) {

                try {

                    myProfile_nic.setText(myProfile.getNic());

                    if (dBconnection.deleteMyprofile(myProfile) > 0) {

                        toast = Toast.makeText(getApplicationContext(), "Successfully Delete", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(myProfile.this,Login_Form.class);
                        startActivity(intent);


                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }catch (NumberFormatException ex)
                {
                    toast = Toast.makeText(getApplicationContext(),"Please Enter Valid NIC",Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });



    }

    private void pickImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }

    //handle the runtime intent
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length >0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){
                    //permission granted

                    pickImageFromGallery();
                }
                else {

                     Toast.makeText(this,"Permission denied!",Toast.LENGTH_SHORT).show();

                }
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            imageView.setImageURI(data.getData());
        }
    }
}
