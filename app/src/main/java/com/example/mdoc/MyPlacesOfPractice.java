package com.example.mdoc;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

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

        final ArrayList<String> HospitalList = new ArrayList<>();
        final Cursor data = db.ViewData();


        if (data.getCount() == 0) {
            Toast.makeText(MyPlacesOfPractice.this, "the database is empty!", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                HospitalList.add("Hospital Name: " + data.getString(1) + "\n" + "Address: " + data.getString(2)
                        + "\n" + "Contact number: " + data.getString(3) + "\n" + "Day: " + data.getString(4) + "\n" + "Time: " + data.getString(5));


                ListAdapter listadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, HospitalList);
                listView.setAdapter(listadapter);

            }



        final ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,HospitalList);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int position, long id) {

                final DaoPlaceOfPractice pop = new DaoPlaceOfPractice();

                final int item_no = position;

                new AlertDialog.Builder(MyPlacesOfPractice.this).
                        setIcon(R.drawable.ic_clear_black_24dp).
                        setTitle("Are you sure?").
                        setMessage("Do you want to delete this item?").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {

                                while (data.moveToNext()) {
                                    pop.setContact_Number(data.getString(3));

                                    HospitalList.remove(item_no);
                                    ((ArrayAdapter) listadapter).notifyDataSetChanged();

                                    if (db.deletePlaceOfPractice(pop) > 0) {

                                        Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                                    }
                                }
                            }
                        }).setNegativeButton("No", null)
                            .show();

                return true;
            }
        });
    }




}}
