package com.example.mdoc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Patient extends AppCompatActivity {
    private ListView patientList;
    private String names[];
    private DBconnection dBconnection = new DBconnection(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        patientList = findViewById(R.id.doctorList);

        List<Daoregister> regsiteredList = dBconnection.getAllPaatients();
        names = new String[regsiteredList.size()];
        for(int i = 0; i< regsiteredList.size(); i++)
        {
            String fname = regsiteredList.get(i).getFirstname();
            String lname = regsiteredList.get(i).getLastname();
            String FullName = fname + " " + lname;
            names[i] = FullName;
        }



        Patient.MyAdapter adapter = new Patient.MyAdapter(this,names);
        patientList.setAdapter(adapter);


        //doctorList.setOnItemClickListener();
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String name[];

        MyAdapter(Context c, String name[])
        {
            super(c,R.layout.patientdrow,name);
            this.context = c;
            this.name = name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.patientdrow,parent,false);
            TextView name = row.findViewById(R.id.patientName);
            name.setText(names[position]);
            return row;
        }
    }

}
