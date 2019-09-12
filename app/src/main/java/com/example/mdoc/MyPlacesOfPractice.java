package com.example.mdoc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyPlacesOfPractice extends AppCompatActivity {
    ListView list;
    DBconnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_place_of_practices_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView listView = (ListView) findViewById(R.id.listviewPOP);
        db = new DBconnection(this);

        final ArrayList<String> PatientList = new ArrayList<>();
        Cursor data = db.ViewData();


        if (data.getCount() == 0) {
            Toast.makeText(MyPlacesOfPractice.this, "the database is empty!", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                PatientList.add("Hospital Name: "+data.getString(2)+ "\n"+"Phone number: "+data.getString(4)
                + "\n"+"Problem: "+data.getString(5)+"\n"+ "Date: "+data.getString(6));


                ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,PatientList);
                listView.setAdapter(listadapter);

            }
        }
    }



}
