package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Appoinment extends AppCompatActivity implements  View.OnClickListener {

    List<String> doctorName = new ArrayList<String>();


    DBconnection dBconnection;
    Spinner docspinner;
    Daoappointment Appoinment = new Daoappointment();
    EditText patientname_appointment, email_appointment, mobile_appointment, problems_appointment,chooseTime,time;
    TextView Date_appointment;
    Button btnaddapoointment, btnupdateappointment, btnviewappointment, btn;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;


    //oncreate appointment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_appoinment);


        dBconnection = new DBconnection(this);

        Date_appointment = findViewById(R.id.txtdate);
        btn = findViewById(R.id.btndatepicker);
        btn.setOnClickListener(this);

        docspinner = findViewById(R.id.spinnerDoctor);
        patientname_appointment = findViewById(R.id.txtappointmentPatientname);
        email_appointment = findViewById(R.id.txtemailappointment);
        mobile_appointment = findViewById(R.id.txtmobileappointment);
        problems_appointment = findViewById(R.id.txtproblemsappointment);
        chooseTime = findViewById(R.id.txtTime);
        time = findViewById(R.id.txtTime);
        //Date_appointment = findViewById(R.id.btndatepicker);
        btnaddapoointment = findViewById(R.id.btnaddappointment);
        //btnupdateappointment = findViewById(R.id.btnupdateappointment);
        btnviewappointment = findViewById(R.id.Viewappointment);

        Daoregister register = new Daoregister();


        doctorName.add("Saman Kumara");
        doctorName.add("Ajith Dewapriya");
        doctorName.add("Ramesh Ellawela");
        doctorName.add("Kasun Rathnayake");
        doctorName.add("Thathee Yapa");
        doctorName.add("Nimesh Perera");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, doctorName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner doctorSpinner = findViewById(R.id.spinnerDoctor);
        doctorSpinner.setPrompt("Select Doctor name");

        doctorSpinner.setAdapter(adapter);

        // Time
        chooseTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Appoinment .this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        if (hourOfDay >= 12){
                            amPm = "PM";

                        } else {
                            amPm = "AM";
                        }

                        chooseTime.setText(String.format("%02d: %02d",hourOfDay,minutes) + amPm);

                    }
                }, currentHour , currentMinute ,false);

                timePickerDialog.show();
            }
        });


     //add appointment
        btnaddapoointment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast toast;

                if (TextUtils.isEmpty(patientname_appointment.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter  patient Name", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(email_appointment.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter  nic", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(mobile_appointment.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter  mobile number", Toast.LENGTH_LONG);
                    toast.show();

                } else if (TextUtils.isEmpty(problems_appointment.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter your problem", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(Date_appointment.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter a date", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    Appoinment.setDoctorname(docspinner.getSelectedItem().toString().trim());
                    Appoinment.setPatientname(patientname_appointment.getText().toString().trim());
                    Appoinment.setEmail(email_appointment.getText().toString().trim());
                    Appoinment.setMobile(mobile_appointment.getText().toString().trim());
                    Appoinment.setProblems(problems_appointment.getText().toString().trim());
                    Appoinment.setDate(Date_appointment.getText().toString().trim());
                    Appoinment.setTime(time.getText().toString().trim());

                    if (dBconnection.addAppointmentInfo(Appoinment) == true) {
                        toast = Toast.makeText(getApplicationContext(), " Successfully Add an Appointment", Toast.LENGTH_LONG);
                        toast.show();
                        //Intent intent = new Intent(Appoinment.this,MainHome.class);
                        //startActivity(intent);

                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error in register", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }





            }
        });


        btnviewappointment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Appoinment.this, viewappointment.class);
                startActivity(intent);
            }
        });


    }

//datepicker
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btndatepicker:
                getDateTimeAppointment.getDateView(this, Date_appointment);
        }
    }
}
