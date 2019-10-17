package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class DocHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);
    }

    public void myPatients(View v){

        Intent intent = new Intent(this, Doc_Patients_list.class);
        startActivity(intent);
    }

    public void myPlaces(View v){

        Intent intent =  new Intent(this, AddPlaces.class);
        startActivity(intent);
    }

    public void update(View v){

        Intent intent = new Intent(this, DoctorMainActivity.class);
        startActivity(intent);
    }
}
