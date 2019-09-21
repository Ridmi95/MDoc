package com.example.mdoc;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Doc_Patients_list extends AppCompatActivity {
    ListView PatientsList;
    DBconnection db;
    DatePickerDialog picker;
    EditText eText;
    Button searchbutton;
    String[] patientlist, addresslist, contactlist, datelist, problist;

    Daoappointment app = new Daoappointment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_patients_list);

        ListView listView = (ListView) findViewById(R.id.listviewPatients);
        db = new DBconnection(this);

        ArrayList<String> thelist = new ArrayList<>();
        Cursor data = db.ViewPatientsData();


        if (data.getCount() == 0) {
            Toast.makeText(Doc_Patients_list.this, "the database is empty!", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                thelist.add("Patient Name: "+data.getString(2)+ "\n"+ "Contact number: "+data.getString(4)+"\n"+ "Date: "+data.getString(6)+ "  "+ "Problem:"+data.getString(5));


                ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listadapter);

            }
        }

        //search for patients' list

        eText = (EditText) findViewById(R.id.search);
       // calender
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Doc_Patients_list.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

//
//        searchbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String key = eText.getText().toString().trim();
//
//                List<Daoappointment> pat = db.search(key);
//                patientlist = new String[pat.size()];
//                addresslist = new String[pat.size()];
//                contactlist = new String[pat.size()];
//                datelist = new String[pat.size()];
//                problist = new String[pat.size()];
//
//                for (int i = 0; i < pat.size(); i++){
//                    String patlist = pat.get(i).getPatientname();
//                    String add = pat.get(i).getEmail();
//                    String contactnum = pat.get(i).getMobile();
//                    String date = pat.get(i).getDate();
//                    String prob = pat.get(i).getProblems();
//
//                    patientlist[i] = patlist;
//                    addresslist[i] = add;
//                    contactlist[i] = contactnum;
//                    datelist[i] = date;
//                    problist[i] = prob;
//                }
//
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_patients_list, R.id.search, patientlist, addresslist, contactlist, datelist, problist );
//                arrayAdapter.setAdapter(listadapter);
//
//
//
//            }
//        });
//
//


    }







}
