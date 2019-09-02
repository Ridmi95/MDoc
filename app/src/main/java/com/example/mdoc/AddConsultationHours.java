package com.example.mdoc;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddConsultationHours extends AppCompatActivity {

    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calender;
    int currentHour;
    int currentMinute;
    String amPm;

    int []ids = new int[]{R.id.time1, R.id.time2,R.id.time3,R.id.time4,R.id.time5,R.id.time6,R.id.time7 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consultation_hours);

        for(int id:ids) {
            chooseTime = findViewById(id);

            chooseTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calender = Calendar.getInstance();
                    currentHour = calender.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calender.get(Calendar.MINUTE);

                    timePickerDialog = new TimePickerDialog(AddConsultationHours.this, new TimePickerDialog.OnTimeSetListener() {
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

        }








    }
}
