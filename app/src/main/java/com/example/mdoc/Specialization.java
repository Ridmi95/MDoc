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
    private EditText specializationName,specializationDescription;
    private Spinner departmentSpinner;
    private DaoSpecialization specialization = new DaoSpecialization();
    private DBconnection dBconnection;
    private Button addNewSpecialization,viewSpecialization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dspecialization);
        dBconnection = new DBconnection(this);

        specializationName = findViewById(R.id.edtSpecializationName);
        specializationDescription = findViewById(R.id.edtDescription);
        departmentSpinner = findViewById(R.id.categoryspinner);
        addNewSpecialization = findViewById(R.id.btnAddNewSpecialization);
        viewSpecialization = findViewById(R.id.btnViewSpecialization);

        category.add("dental");
        category.add("Eye");
        category.add("OPD");
        category.add("Orthopendic");
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



        addNewSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("info", specializationName.getText().toString().trim());
                //Log.i("info", departmentSpinner.getSelectedItem().toString().trim());
                //Log.i("info", specializationDescription.getText().toString().trim());
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

                    specialization.setSpecializationName(specializationName.getText().toString().trim());
                    specialization.setSpecializationDepartment(departmentSpinner.getSelectedItem().toString().trim());
                    specialization.setSpecializationDescription(specializationDescription.getText().toString().trim());

                    if (dBconnection.addNewSpecialization(specialization) > 0) {
                        toast = Toast.makeText(getApplicationContext(), "Specialization Successfully Added", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error in adding", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
            }
        });

        viewSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Specialization.this,AdminViewSpecialization.class);
                startActivity(intent);
            }
        });



    }




}


