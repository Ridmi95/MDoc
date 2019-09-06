package com.example.mdoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Doctor extends AppCompatActivity {

    ListView doctorList;
    String names[] = {"Doctor 1","Doctor 2","Doctor 3","Doctor 4","Doctor 1","Doctor 2","Doctor 3","Doctor 4","Doctor 1","Doctor 2","Doctor 3","Doctor 4"};
    ImageButton patient,pending,specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        doctorList = findViewById(R.id.doctorList);

        patient = findViewById(R.id.doctor_page_patient);
        pending = findViewById(R.id.doctor_page_pending);
        specialization = findViewById(R.id.doctor_page_specialization);

        MyAdapter adapter = new MyAdapter(this,names);
        doctorList.setAdapter(adapter);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor.this,Patient.class);
                startActivity(intent);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PendingFragment fragment = new PendingFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                //Intent intent = new Intent(Doctor.this,pendin)
            }
        });


        specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor.this,Specialization.class);
                startActivity(intent);
            }
        });




        //doctorList.setOnItemClickListener();
    }


    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String name[];

        MyAdapter(Context c, String name[])
        {
            super(c,R.layout.doctordrow,name);
            this.context = c;
            this.name = name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.doctordrow,parent,false);
            TextView name = row.findViewById(R.id.doctorName);
            name.setText(names[position]);
            return row;
        }
    }


}
