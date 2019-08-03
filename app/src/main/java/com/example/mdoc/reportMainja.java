package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class reportMainja extends AppCompatActivity {

    ImageView search;
    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportmain);

        search = findViewById(R.id.srchbtn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reportMainja.this, search.class);
                startActivity(intent);
            }
        });

        view = findViewById(R.id.viewbtn);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reportMainja.this, com.example.myapplication5.view.class);
                startActivity(intent);
            }
        });
    }
}
