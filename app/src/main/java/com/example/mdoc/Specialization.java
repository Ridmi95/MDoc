package com.example.mdoc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Specialization extends AppCompatActivity {

    List<String> category = new ArrayList<String>();
    List<String> department = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dspecialization);

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
        Spinner sItems2 = findViewById(R.id.departmentSpinner);

        sItems2.setPrompt("Select Department");
        sItems1.setAdapter(categoryadapter);
        sItems2.setAdapter(departmentadapter);

    }
}


