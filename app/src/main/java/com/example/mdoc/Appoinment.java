package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Appoinment extends AppCompatActivity {

    List<String> doctorName = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);

        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner doctorSpinner = findViewById(R.id.docname);
        doctorSpinner.setAdapter(adapter);


    }


    public void view(View view) {

        Intent intent = new Intent(this,myProfile.class);
        startActivity(intent);
    }
}
