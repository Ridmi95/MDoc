package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Specialization extends AppCompatActivity {

    private List<String> category = new ArrayList<String>();
    private List<String> department = new ArrayList<String>();
    private EditText specializationName,specializationDescription,specialKey;
    private Spinner departmentSpinner;
    private DaoSpecialization specialization = new DaoSpecialization();
    private DBconnection dBconnection;
    private Button addNewSpecialization,viewSpecialization;
    private String name;
    private int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dspecialization);
        dBconnection = new DBconnection(this);

        specializationName = findViewById(R.id.edtSpecializationName);
        specializationDescription = findViewById(R.id.edtDescription);
        specialKey = findViewById(R.id.edtSpecialKey);
        departmentSpinner = findViewById(R.id.categoryspinner);
        addNewSpecialization = findViewById(R.id.btnAddNewSpecialization);
        viewSpecialization = findViewById(R.id.btnViewSpecialization);

        category.add("dental");
        category.add("Eye");
        category.add("OPD");
        category.add("Orthopedic");
        category.add("Physio");
        department.add("Dental");
        department.add("Eye");


        ArrayAdapter<String> categoryadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
        ArrayAdapter<String> departmentadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,department);

        categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sItems1 = findViewById(R.id.categoryspinner);
        sItems1.setPrompt("Select Category");


        sItems1.setAdapter(categoryadapter);


        //Adding New Specialization to the database
        addNewSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                //validating the add specialization
                if(TextUtils.isEmpty(specializationName.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter Specialization Name",Toast.LENGTH_LONG);
                    toast.show();
                }else if(TextUtils.isEmpty(specializationDescription.getText().toString()))
                {
                    toast = Toast.makeText(getApplicationContext(),"Enter a description", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    //checking for valid format
                    try {
                        specialization.setSpecializationName(specializationName.getText().toString().trim());
                        specialization.setSpecializationDepartment(departmentSpinner.getSelectedItem().toString().trim());
                        specialization.setSpecializationDescription(specializationDescription.getText().toString().trim());
                        specialization.setId(Integer.parseInt(specialKey.getText().toString().trim()));
                    }catch (NumberFormatException ex)
                    {
                        toast = Toast.makeText(getApplicationContext(),"Invalid Format",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    //checking wheather the specialization is already exist in the database
                    if(dBconnection.checkSpecializationExist(specialization.getSpecializationName()) > 0)
                    {
                        toast = Toast.makeText(getApplicationContext(), "Specialization Already Exits", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else {
                        if (dBconnection.addNewSpecialization(specialization) > 0) {
                            toast = Toast.makeText(getApplicationContext(), "Specialization Successfully Added", Toast.LENGTH_LONG);
                            toast.show();
                            addReset();

                        } else {
                            toast = Toast.makeText(getApplicationContext(), "Error in adding", Toast.LENGTH_LONG);
                            toast.show();

                        }
                    }
                }
            }
        });

        viewSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Specialization.this,AdminViewSpecialization.class);
                startActivity(intent);
                finish();
            }
        });

        if(getSpecializationtoUpdate() == 1)
        {
            final DaoSpecialization updateSpecialization = new DaoSpecialization();
            final DaoSpecialization deleteSpecialization = new DaoSpecialization();
            specializationName.setText(name);
            addNewSpecialization.setText("UPDATE");
            viewSpecialization.setText("DELETE");


            //updating the specialization
            addNewSpecialization.setOnClickListener(new View.OnClickListener() {
                Toast toast;
                @Override
                public void onClick(View view) {
                    try {
                    updateSpecialization.setSpecializationName(specializationName.getText().toString().trim());
                    updateSpecialization.setId(Integer.parseInt(specialKey.getText().toString().trim()));
                    updateSpecialization.setSpecializationDepartment(departmentSpinner.getSelectedItem().toString().trim());
                    Log.i("Update Name", updateSpecialization.getSpecializationName());
                    Log.i("Position",String.valueOf(listPosition));
                        if(dBconnection.checkSpecializationExist(updateSpecialization.getSpecializationName()) > 0)
                        {
                            toast = Toast.makeText(getApplicationContext(), "Specialization Already Exits", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else {
                            if (dBconnection.updateSpecialization(updateSpecialization) > 0) {
                                toast = Toast.makeText(getApplicationContext(), "Successfully Updates", Toast.LENGTH_LONG);
                                toast.show();
                                Intent intent = new Intent(Specialization.this, AdminViewSpecialization.class);
                                startActivity(intent);
                                finish();
                            } else {
                                toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    }catch (NumberFormatException ex)
                    {
                        toast = Toast.makeText(getApplicationContext(),"Please Enter a Value of Special Key",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            });

            //deleting the user
            viewSpecialization.setOnClickListener(new View.OnClickListener() {
                Toast toast;
                @Override
                public void onClick(View view) {
                    Log.i("Position",String.valueOf(listPosition));
                    try {
                        deleteSpecialization.setId(Integer.parseInt(specialKey.getText().toString().trim()));

                        if (dBconnection.deleteSpecialization(deleteSpecialization) > 0) {

                            toast = Toast.makeText(getApplicationContext(), "Successfully Delete", Toast.LENGTH_LONG);
                            toast.show();

                            Intent intent = new Intent(Specialization.this, AdminViewSpecialization.class);
                            startActivity(intent);
                            finish();
                        } else {
                            toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }catch (NumberFormatException ex)
                    {
                        toast = Toast.makeText(getApplicationContext(),"Please Enter a Value of Special Key",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });

        }

    }


    public int getSpecializationtoUpdate()
    {

        Intent intent = getIntent();
        name = intent.getStringExtra("SpecilizationName");
        listPosition = intent.getIntExtra("position",0);
        if(name != null)
        {
            return 1;
        }else
            {
                return 0;
            }
    }


    public void addReset()
    {
        specializationName.setText("");
        specializationDescription.setText("");
        specialKey.setText("");
    }





}


