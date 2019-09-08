package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorMainActivity extends AppCompatActivity {

    DBconnection dbcon = new DBconnection(this);
    EditText txtnic, txttitle, txtfname, txtlastname, txtemail, txtxphone, txtmedreg;
    Button btnupdate;
    DaoPlaceOfPractice pop = new DaoPlaceOfPractice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // txtnic = findViewById()
    }

    public void submit(View v){
        Intent intent = new Intent(this, DocHomeActivity.class);
        startActivity(intent);
    }
}
