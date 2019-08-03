package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class doc_app extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_app);
    }

    public void add(View view) {
        Intent intent = new Intent(this,listOfDoc.class);
        startActivity(intent);
    }

    public void get(View view) {

        Intent intent = new Intent(this,Appoinment.class);
        startActivity(intent);

    }
}
