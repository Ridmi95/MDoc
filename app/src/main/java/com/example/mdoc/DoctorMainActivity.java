package com.example.mdoc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorMainActivity extends AppCompatActivity {

    DBconnection dbcon = new DBconnection(this);
    EditText txtfname, txtlastname, txtemail, txtxphone, txtmedreg, txtnic;
    Button btnupdate;
    Daoregister register = new Daoregister();
    String email;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplication().getSharedPreferences("doctorPreference",0);
        SharedPreferences.Editor editor = pref.edit();
        email = pref.getString("doctorEmail",null);

        txtnic = findViewById(R.id.dtxtnic);
        txtfname = findViewById(R.id.dtxtfname);
        txtlastname = findViewById(R.id.dtxtlastname);
        txtemail = findViewById(R.id.dtxtemail);
        txtxphone = findViewById(R.id.dtxtphone);
        txtmedreg = findViewById(R.id.dtxtmedreg);

        register = dbcon.getProfileDetails(email);
        txtnic.setText(register.getNic());
        txtfname.setText(register.getFirstname());
        txtlastname.setText(register.getLastname());
        txtemail.setText(register.getEmail());
        txtxphone.setText(String.valueOf(register.getContactnum()));
        txtmedreg.setText(register.getMedicalregno());


//        btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                register.setFirstname(txtfname.getText().toString());
//                register.setLastname(txtlastname.getText().toString());
//                register.setEmail(txtemail.getText().toString());
//                register.setContactnum((Integer.parseInt(txtxphone.getText().toString())));
//                register.setMedicalregno(txtmedreg.getText().toString());
//
//                dbcon.updateProfileDetails(register);
//                Toast.makeText(DoctorMainActivity.this, "Data updated successfully!", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void submit(View v){
        Intent intent = new Intent(this, DocHomeActivity.class);
        startActivity(intent);
    }
}
