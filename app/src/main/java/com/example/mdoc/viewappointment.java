package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class viewappointment extends AppCompatActivity {

    //Daoappointment viewapp = new Daoappointment();
    //ListView list;
    DBconnection dBconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewappointment);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        ListView listView =(ListView)findViewById(R.id.viewapp);
        dBconnection = new DBconnection(this);

        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = dBconnection.viewData();

        if(data.getCount() == 0){
            Toast.makeText(this,"The database is Empty",Toast.LENGTH_LONG).show();

        }else{
            while (data.moveToNext()){
                thelist.add("Doctor Name:"+data.getString(0)+"\n"+"Patient Name:"+
                        data.getString(1)+"\n"+"Problem:"+data.getString(4)+"\n"+"Email :"+data.getString(2)+"\n" +"Date:"+data.getString(5)+"\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);
                }
            }

        }





    }

