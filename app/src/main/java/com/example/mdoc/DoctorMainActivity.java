package com.example.mdoc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DoctorMainActivity extends AppCompatActivity {

    DBconnection dbcon = new DBconnection(this);
    EditText txtfname, txtlastname, txtemail, txtxphone, txtmedreg, txtnic;
    Button btnupdate;
    private Spinner specSpinner;
    Daoregister register = new Daoregister();
    private List<String> spec = new ArrayList<String>();
    String email;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spec.add("cardiologist");
        spec.add("gynaecologist");
        spec.add("neurologist");
        spec.add("paediatrician");
        spec.add("orthopedist");

        ArrayAdapter<String> specadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spec);

        specadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sItems1 = findViewById(R.id.spinnerspec);
        sItems1.setAdapter(specadapter);

//        pref = getApplication().getSharedPreferences("doctorPreference",0);
//        SharedPreferences.Editor editor = pref.edit();
//        email = pref.getString("doctorEmail",null);

        pref = getApplication().getSharedPreferences("userPreference",0);
        SharedPreferences.Editor editor = pref.edit();
        email = pref.getString("userEmail",null);

        Log.i("Email",email);

        txtnic = findViewById(R.id.dtxtnic);
        txtfname = findViewById(R.id.dtxtfname);
        txtlastname = findViewById(R.id.dtxtlastname);
        txtemail = findViewById(R.id.dtxtemail);
        txtxphone = findViewById(R.id.dtxtphone);
        txtmedreg = findViewById(R.id.dtxtmedreg);
        specSpinner = findViewById(R.id.spinnerspec);
        btnupdate = findViewById(R.id.button);

        register = dbcon.getDocDetails(email);

        txtnic.setText(register.getNic());
        txtfname.setText(register.getFirstname());
        txtlastname.setText(register.getLastname());
        txtemail.setText(register.getEmail());
        txtxphone.setText(String.valueOf(register.getContactnum()));
        txtmedreg.setText(register.getMedicalregno());
        specSpinner.getSelectedItem().toString().trim();
        

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register.setNic(txtnic.getText().toString());
                register.setFirstname(txtfname.getText().toString());
                register.setLastname(txtlastname.getText().toString());
                register.setEmail(txtemail.getText().toString());
                register.setContactnum(Integer.parseInt(txtxphone.getText().toString()));
                register.setMedicalregno(txtmedreg.getText().toString());
                register.setStatus("Pending");

                if (dbcon.updateDoctor(register) > 0) {
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void submit(View v){
        Intent intent = new Intent(this, DocHomeActivity.class);
        startActivity(intent);
    }
}
