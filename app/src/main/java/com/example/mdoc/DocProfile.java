package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class DocProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);
    }
    public void update(View v){

        Intent intent = new Intent(this, DoctorMainActivity.class);
        startActivity(intent);
    }

    public void qualnSpec(View v){

        Intent intent = new Intent(this, QualificationActivity.class);
        startActivity(intent);
    }
}
