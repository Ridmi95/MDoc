package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Appoinment extends AppCompatActivity {

    List<String> doctorName = new ArrayList<String>();
    DBconnection dBconnection;
    Spinner docspinner;
    Daoappointment appointment = new Daoappointment();
    EditText doctorname_appointment,patientname_appointment,email_appointment,mobile_appointment,problems_appointment,
    Date_appointment;
    Button btnaddapoointment,btnupdateappointment,btnviewappointment;

    //DatePickerDialog dpd;
    //String dateFinal;
    //int startYear = 0, startMonth = 0, startDay = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);

        dBconnection = new DBconnection(this);
        docspinner = findViewById(R.id.spinnerDoctor);
        patientname_appointment = findViewById(R.id.txtappointmentPatientname);
        email_appointment = findViewById(R.id.txtemailappointment);
        mobile_appointment =findViewById(R.id.txtmobileappointment);
        patientname_appointment = findViewById(R.id.txtproblemsappointment);
        Date_appointment = findViewById(R.id.datapickerappointment);


        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");
        doctorName.add("Namal");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner doctorSpinner = findViewById(R.id.spinnerDoctor);
        doctorSpinner.setAdapter(adapter);


        //dateFinal = todayDateString();
       //Date your_date = new Date();
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(your_date);
        //startYear = cal.get(Calendar.YEAR);
        //startMonth = cal.get(Calendar.MONTH);
        //startDay = cal.get(Calendar.DAY_OF_MONTH);


    }


    public void view(View view) {

        Intent intent = new Intent(this,myProfile.class);
        startActivity(intent);
    }


    //date
   /* public void press(View view) {
        dpd = DatePickerDialog.newInstance(Appoinment.this, startYear, startMonth, startDay);
        dpd.setOnDateSetListener(this);
        dpd.show(getFragmentManager(), "startDatepickerdialog");
    }*/



    //public String todayDateString() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat(
               // "dd/MM/yyyy", Locale.getDefault());

        //return dateFormat.toString();

    //}

}
