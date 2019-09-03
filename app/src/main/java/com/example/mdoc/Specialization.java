package com.example.mdoc;

import android.os.Bundle;
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
    private DaoSpeciliazation specialization;
    private DBconnection dBconnection;
    private Button addNewSpecialization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dspecialization);

        specializationName = findViewById(R.id.edtSpecializationName);
        specializationDescription = findViewById(R.id.edtDescription);
        departmentSpinner = findViewById(R.id.categoryspinner);
        addNewSpecialization = findViewById(R.id.btnAddNewSpecialization);


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
        //
        //sItems2.setPrompt("Select Department");
        sItems1.setAdapter(categoryadapter);
        //sItems2.setAdapter(departmentadapter);


        addNewSpecialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specialization.setSpecializationName(specializationName.getText().toString().trim());
                specialization.setSpecializationDescription(departmentSpinner.getSelectedItem().toString());
                specialization.setSpecializationDepartment(specializationDescription.getText().toString().trim());

                if(dBconnection.addNewSpecialization(specialization) > 0)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Specialization Sucessfuly Added",Toast.LENGTH_LONG);
                    toast.show();
                }else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Error in adding",Toast.LENGTH_LONG);
                    toast.show();

                }
            }
        });

    }




}


