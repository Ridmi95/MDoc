package com.example.mdoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class viewappointment extends AppCompatActivity {

//    private ListView listviewl;
//    private  ArrayList<String> arrayList;
//    private ArrayAdapter adapter;



    Daoappointment viewapp = new Daoappointment();
    //ListView list;
    DBconnection dBconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewappointment);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        dBconnection = new DBconnection(this);
////////////////////////////////////////////////////////////////////////////////////////////////////////

//       listviewl = (ListView)findViewById(R.id.viewapp);
//       arrayList = new ArrayList<String>();


        ListView listView =(ListView)findViewById(R.id.viewapp);


        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = dBconnection.viewAppointmentData();

        if(data.getCount() == 0){
            Toast.makeText(this,"The database is Empty",Toast.LENGTH_LONG).show();

        }else{
            while (data.moveToNext()){
                thelist.add("Doctor Name:"+data.getString(1)+"\n"+"Patient Name:"+
                        data.getString(2)+"\n"+"Email :"+data.getString(3)+"\n"+"Time:"+data.getString(4)+"\n"+"Problem:"+data.getString(5)+"\n" +"Date:"+data.getString(6)+"\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);
                }
            }


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int position, long id) {

                final int which_item =  position;

                new AlertDialog.Builder(viewappointment.this)
                 .setIcon(android.R.drawable.ic_delete)
                 .setTitle("Are you sure?")
                 .setMessage("Do you want to delete this item ")
                 .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int which) {
                         thelist.remove(which_item);
                         adapterView.notifyAll();

                     }
                 })

                 .setNegativeButton("No",null)
                 .show();




                return true;
            }
        });

        }





    }

