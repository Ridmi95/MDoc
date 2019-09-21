package com.example.mdoc;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Doc_Patients_list extends AppCompatActivity {
    ListView PatientsList;
    DBconnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_patients_list);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        ListView listView = (ListView) findViewById(R.id.listviewPatients);
        db = new DBconnection(this);

        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = db.ViewPatientsData();


        if (data.getCount() == 0) {
            Toast.makeText(Doc_Patients_list.this, "the database is empty!", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                thelist.add("Patient Name: "+data.getString(1)+ "\n"+"Address: "+data.getString(2)
                        + "\n"+"Contact number: "+data.getString(3)+"\n"+ "Date: "+data.getString(4)+ "  "+ "Time:"+data.getString(5));


                ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listadapter);

            }
        }
    }



}
