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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdminViewSpecialization extends AppCompatActivity {

    private ListView spcializationList;
    private DBconnection dBconnection = new DBconnection(this);
    private String[] specialList,DepartmentList,keyList;
    private Button searchSpecialzation;
    private EditText specializationSearchId;
    private static final String TAG = "Admin View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_specialization);


        searchSpecialzation = findViewById(R.id.btnSearchSpecialzation);
        specializationSearchId = findViewById(R.id.specializationSearchKey);

        spcializationList = findViewById(R.id.specializationList);
        List<DaoSpecialization> specializationList = dBconnection.getAllSpecialization();

        specialList = new String[specializationList.size()];
        DepartmentList = new String[specializationList.size()];
        keyList = new String[specializationList.size()];

        for(int i = 0; i < specializationList.size(); i++)
        {
            String splist = specializationList.get(i).getSpecializationName();
            String spDep = specializationList.get(i).getSpecializationDepartment();
            String key = String.valueOf(specializationList.get(i).getId());
            specialList[i] = splist;
            DepartmentList[i] = spDep;
            keyList[i] = key;
            Log.i("data",splist);
        }

        AdminViewSpecialization.MyAdapter adapter = new AdminViewSpecialization.MyAdapter(this,specialList,DepartmentList,keyList);
        spcializationList.setAdapter(adapter);


        spcializationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminViewSpecialization.this,Specialization.class);
                String name = (String) spcializationList.getItemAtPosition(i);
              //  String department = (String) findViewById(R.id.specializationKey).
                intent.putExtra("SpecilizationName", name);
             //   intent.putExtra("DepartmentName", department);

                intent.putExtra("position",i);
                Log.i("position",String.valueOf(i));

                startActivity(intent);
            }
        });


        searchSpecialzation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchkey = specializationSearchId.getText().toString().trim();

                List<DaoSpecialization> specializationList = dBconnection.getSearchedSpecialization(searchkey);
                specialList = new String[specializationList.size()];
                DepartmentList = new String[specializationList.size()];
                keyList = new String[specializationList.size()];

                for(int i = 0; i < specializationList.size(); i++)
                {
                    String splist = specializationList.get(i).getSpecializationName();
                    String spDep = specializationList.get(i).getSpecializationDepartment();
                    String key = String.valueOf(specializationList.get(i).getId());
                    specialList[i] = splist;
                    DepartmentList[i] = spDep;
                    keyList[i] = key;
                    Log.i("data",splist);
                }

                AdminViewSpecialization.MyAdapter adapter = new AdminViewSpecialization.MyAdapter(getApplicationContext(),specialList,DepartmentList,keyList);
                spcializationList.setAdapter(adapter);

            }
        });

    }
    //method to search specialization



    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String name[];
        String department[];
        String key[];

        MyAdapter(Context c, String name[],String[] deaprtment,String[] key)
        {
            super(c,R.layout.specializationdrow,name);
            this.context = c;
            this.name = name;
            this.department = deaprtment;
            this.key = key;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.specializationdrow,parent,false);
            TextView name = row.findViewById(R.id.specializationName);
            TextView department = row.findViewById(R.id.specializationDepartment);
            TextView key = row.findViewById(R.id.specializationKey);
            name.setText(specialList[position]);
            department.setText(DepartmentList[position]);
            key.setText(keyList[position]);
            return row;
        }
    }

}



