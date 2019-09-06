package com.example.mdoc;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPlaces extends AppCompatActivity {

    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calender;
    int currentHour;
    int currentMinute;
    String amPm;

//    String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    List<String> days = new ArrayList<>();

    DBconnection dbcon = new DBconnection(this);
    EditText edittexthospital, edittextaddress, edittextcontactnumber,time;
    Spinner spinnerdate;
    Button btnsavedata, btnviewdata;

    DaoPlaceOfPractice pop = new DaoPlaceOfPractice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);

        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");


        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,days);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spin = (Spinner) findViewById(R.id.spinnerweek);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        edittexthospital = (EditText)findViewById(R.id.txthospital);
        edittextaddress = (EditText)findViewById(R.id.txtaddress);
        edittextcontactnumber = (EditText)findViewById(R.id.txtcontactnumber);
        spinnerdate = (Spinner)findViewById(R.id.spinnerweek);
        time = (EditText)findViewById(R.id.time1);

        btnsavedata = (Button)findViewById(R.id.btnSave);
        btnviewdata = (Button)findViewById(R.id.viewpop);

//time
            chooseTime = findViewById(R.id.time1);

            chooseTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calender = Calendar.getInstance();
                    currentHour = calender.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calender.get(Calendar.MINUTE);

                    timePickerDialog = new TimePickerDialog(AddPlaces.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            if (hourOfDay >= 12) {
                                amPm = "PM";
                            } else {
                                amPm = "AM";
                            }
                            chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        }
                    }, currentHour, currentMinute, false);

                    timePickerDialog.show();
                }
            });

        btnviewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPlaces.this, MyPlacesOfPractice.class);
                startActivity(intent);
            }
        });

        btnsavedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pop.setHospital_Name(edittexthospital.getText().toString().trim());
                pop.setAddress(edittextaddress.getText().toString().trim());
                pop.setContact_Number(edittextcontactnumber.getText().toString().trim());
                pop.setDate(spinnerdate.getSelectedItem().toString().trim());
                pop.setTime(time.getText().toString().trim());

                if(edittexthospital.length() == 0 || edittextaddress.length() == 0 ||
                        edittextcontactnumber.length() == 0 || time.length() == 0 || spinnerdate.getSelectedItem() == "") {
                    Toast.makeText(AddPlaces.this, "Fields cannot be empty!", Toast.LENGTH_LONG).show();
                }else{
                    if(dbcon.insertData(pop) > 0){
                        Toast.makeText(AddPlaces.this, "Data saved successfully!", Toast.LENGTH_LONG).show();

                    }else{
                       Toast.makeText(AddPlaces.this, "Error!", Toast.LENGTH_LONG).show();
                       // Toast.makeText(AddPlaces.this, "time is: "+spinnerdate.getSelectedItem().toString().trim(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

    }

    public void myPlaces(View v){
        Intent intent = new Intent(this, MyPlacesOfPractice.class);
        startActivity(intent);
    }


}
