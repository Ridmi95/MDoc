package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdminViewSpecialization extends AppCompatActivity {

    private ListView spcializationList;
    private DBconnection dBconnection = new DBconnection(this);
    private String[] specialList;
    private static final String TAG = "Admin View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_specialization);

        spcializationList = findViewById(R.id.specializationList);
        List<DaoSpecialization> specializationList = dBconnection.getAllSpecialization();
        specialList = new String[specializationList.size()];

        for(int i = 0; i < specializationList.size(); i++)
        {
            String splist = specializationList.get(i).getSpecializationName();
            specialList[i] = splist;
            Log.i("data",splist);
        }

        AdminViewSpecialization.MyAdapter adapter = new AdminViewSpecialization.MyAdapter(this,specialList);
        spcializationList.setAdapter(adapter);


        spcializationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminViewSpecialization.this,Specialization.class);
                //intent.putExtra("SpecilizationName", )
            }
        });

    }


    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String name[];

        MyAdapter(Context c, String name[])
        {
            super(c,R.layout.specializationdrow,name);
            this.context = c;
            this.name = name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.specializationdrow,parent,false);
            TextView name = row.findViewById(R.id.specializationName);
            name.setText(specialList[position]);
            return row;
        }
    }

}



