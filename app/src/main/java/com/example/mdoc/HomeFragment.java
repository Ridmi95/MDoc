package com.example.mdoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout docLayout;
    private LinearLayout patient;
    private LinearLayout specialization;
    private TextView noOfDoctors,noOfPatients;
    private DBconnection dBconnection;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dBconnection = new DBconnection(getActivity().getApplicationContext());
        rootView = inflater.inflate(R.layout.fragement_dhome,container,false);
        noOfPatients = rootView.findViewById(R.id.oOfRegisteredPatients);


        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        noOfPatients.setText((String.valueOf(dBconnection.getTotalregisteredPatients())));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        docLayout = getActivity().findViewById(R.id.layoutdoctor);
        docLayout.setOnClickListener(this);

        patient = getActivity().findViewById(R.id.layoutpatient);
        patient.setOnClickListener(this);

        specialization = getActivity().findViewById(R.id.layoutspecialization);
        specialization.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId())
        {
            case R.id.layoutdoctor:
                intent = new Intent(getActivity(), Doctor.class);
                startActivity(intent);
                break;
            case R.id.layoutpatient:
                intent = new Intent(getActivity(), Patient.class);
                startActivity(intent);
                break;

            case R.id.layoutspecialization:
                intent = new Intent(getActivity(), Specialization.class);
                startActivity(intent);
                break;

        }


    }



}
