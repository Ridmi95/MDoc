package com.example.mdoc;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class listOfDoc extends AppCompatActivity {
    //search
    SearchView searchView;
    DBconnection dBconnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doc);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //search
        searchView = (SearchView)findViewById(R.id.Doclist);

        ListView listView =(ListView)findViewById(R.id.listodoc);
        dBconnection = new DBconnection(this);

        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = dBconnection.viewAppointmentData();

        if(data.getCount() == 0){
            Toast.makeText(this,"The database is Empty",Toast.LENGTH_LONG).show();

        }else{
            while (data.moveToNext()){


                thelist.add("Doctor Name:"+data.getString(1)+"\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);

                //search
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {

                        //listAdapter.getFilter().Filter(s);

                        return false;
                    }
                });
            }
        }



    }

}
