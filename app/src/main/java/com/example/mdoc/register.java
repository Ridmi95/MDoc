package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class register extends AppCompatActivity {

    List<String> categoryType = new ArrayList<String>();
    DBconnection dBconnection;
    Spinner registertypespinner;
    Daoregister register = new Daoregister();
    EditText firstname_register,lastname_register,type_register,email_register,nic_register,mobile_register,password_register,
    confirmpassword_register;
    Button btnregister_register;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //getSupportActionBar().setTitle("Signup");



        dBconnection = new DBconnection(this);

        firstname_register = findViewById(R.id.txtrfristname);
        lastname_register = findViewById(R.id.txtrlastname);
        //type_register = findViewById(R.id.txtrtype);
        registertypespinner =findViewById(R.id.categoryTypespinner);
        nic_register = findViewById(R.id.txtrnic);
        email_register = findViewById(R.id.txtremail);
        mobile_register = findViewById(R.id.txtrmobile);
        password_register = findViewById(R.id.txtrpassword);
        confirmpassword_register = findViewById(R.id.txtrconfirmpassword);
        btnregister_register = findViewById(R.id.btnrregister);



        categoryType.add("Customer");
        categoryType.add("Doctor");
        categoryType.add("Laboratory Technician");

        ArrayAdapter<String> categoryad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categoryType);

        categoryad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sITEM = findViewById(R.id.categoryTypespinner);
        sITEM.setPrompt("Select Category");


        sITEM.setAdapter(categoryad);


        btnregister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast;
                //validating the add specialization
                if(TextUtils.isEmpty(firstname_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_LONG);
                    toast.show();
                } else if(TextUtils.isEmpty(lastname_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter Last Name", Toast.LENGTH_LONG);
                    toast.show();
                }
                 else if(TextUtils.isEmpty(nic_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter  nic", Toast.LENGTH_LONG);
                    toast.show();
                } else if(TextUtils.isEmpty(email_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter an email", Toast.LENGTH_LONG);
                    toast.show();
                }else if(TextUtils.isEmpty(password_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter password", Toast.LENGTH_LONG);
                    toast.show();
                }else if(TextUtils.isEmpty(confirmpassword_register.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter confirm password", Toast.LENGTH_LONG);
                    toast.show();
                }

                else {

                    register.setFirstname(firstname_register.getText().toString().trim());
                    register.setLastname(lastname_register.getText().toString().trim());
                    register.setType(registertypespinner.getSelectedItem().toString().trim());
                    register.setNic(nic_register.getText().toString().trim());
                    register.setEmail(email_register.getText().toString().trim());
                    register.setContactnum(Integer.parseInt(mobile_register.getText().toString().trim()));
                    register.setPassword(password_register.getText().toString().trim());


                    if (dBconnection.addregisterInfo(register) == true) {
                        toast = Toast.makeText(getApplicationContext(), " Successfully Registered", Toast.LENGTH_LONG);
                        toast.show();

                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error in register", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }

            }


        });



        }


    public void register(View view) {

        Intent intent = new Intent(this,Login_Form.class);
        startActivity(intent);
    }
}

