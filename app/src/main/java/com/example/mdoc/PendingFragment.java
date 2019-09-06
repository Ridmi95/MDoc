package com.example.mdoc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class PendingFragment extends Fragment {

    ListView pendingDoctorList;
    String names[];// = {"Doctor 1","Doctor 2","Doctor 3","Doctor 4","Doctor 1","Doctor 2","Doctor 3","Doctor 4","Doctor 1","Doctor 2","Doctor 3","Doctor 4"};
    private static final String TAG = "PendingFragment";
    private DBconnection dBconnection;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dBconnection = new DBconnection(getActivity().getApplicationContext());
        rootView = inflater.inflate(R.layout.fragement_dpending,container,false);
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        List<Daoregister> regsiteredList = dBconnection.getAllPaatients();
        names = new String[regsiteredList.size()];

        for(int i = 0; i< regsiteredList.size(); i++)
        {
            String fname = regsiteredList.get(i).getFirstname();
            String lname = regsiteredList.get(i).getLastname();
            String FullName = fname + " " + lname;
            names[i] = FullName;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,names);
        pendingDoctorList = rootView.findViewById(R.id.pendingDoctor);


        pendingDoctorList.setAdapter(adapter);

        pendingDoctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                customDialog("Pending Doctors","Do you want to  " + names[position] + " ?", "cancelMethod1","okMethod1");
            }
        });
    }

    private void declineMethod(){
        Log.d(TAG, "decline Method: Called.");
        toastMessage("Doctor is Declined");
    }

    private void approveMethod(){
        Log.d(TAG, "Approve Method: Called.");
        toastMessage("Doctor is Approved");

        


    }




    /*
        This the custom dialog box
        created
     */

    public void customDialog(String title, String message, final String cancelMethod, final String okMethod){
        final androidx.appcompat.app.AlertDialog.Builder builderSingle = new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
        builderSingle.setIcon(R.drawable.ic_info);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Decline",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Cancel Called.");
                        if(cancelMethod.equals("cancelMethod1")){
                            declineMethod();
                        }

                    }
                });

        builderSingle.setPositiveButton(
                "Approve",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: OK Called.");
                        if(okMethod.equals("okMethod1")){
                            approveMethod();
                        }

                    }
                });


        builderSingle.show();
    }


    public void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }


    }


