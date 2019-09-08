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

        final ArrayList<String> thelist = new ArrayList<>();
        Cursor data = db.viewData();


//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                final int item_no = thelist;
//
//                return false;
//            }
//        });

        if (data.getCount() == 0) {
            Toast.makeText(MyPlacesOfPractice.this, "the database is empty!", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                thelist.add("Hospital Name: "+data.getString(1)+ "\n"+"Address: "+data.getString(2)
                + "\n"+"Contact number: "+data.getString(3)+"\n"+ "Date: "+data.getString(4)+ "  "+ "Time:"+data.getString(5));


                ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listadapter);

            }
        }
    }

//    public void addPlace(View v){
//
//        Intent intent = new Intent(this, AddPlaces.class);
//        startActivity(intent);
//    }

//    public void viewPOP(){
//
//        if (result.getCount() == 0){
//            //show message
//            showMessage("Error!", "Nothing found!");
//            return;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (result.moveToNext()){
//            buffer.append("Hospital Name: "+result.getString(1)+ "\n");
//            buffer.append("Address of the hospital: "+result.getString(2)+ "\n");
//            buffer.append("Contact number of the hospital: "+result.getString(3)+ "\n\n");
//
//
//        }

//        SQLiteDatabase sqldb = db.getReadableDatabase();
//        Cursor cursor = db.viewData(sqldb);
//
//        String hospital_name, address, contact_number;
//
//        while(cursor.moveToNext()){
//
//            hospital_name = cursor.getString(cursor.getColumnIndex(DatabaseContract.Entry.col_2));
//            address = cursor.getString(cursor.getColumnIndex(DatabaseContract.Entry.col_3));
//            contact_number = cursor.getString(cursor.getColumnIndex(DatabaseContract.Entry.col_4));
//
//            DaoPlaceOfPractice pop = new DaoPlaceOfPractice(hospital_name, address, contact_number);

//            publish

//        }
//    }

//    public void showMessage(String title, String msg){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//         builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(msg);
//        builder.show();
//    }


}
