package com.example.mdoc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class PendingFragment extends Fragment {

    private ListView pendingDoctorList;
    private String names[] = {"Doctor 1","Doctor 2"};
    private String docNic[] = {"1","2"};
    private static final String TAG = "PendingFragment";
    private Button searchPendingDoctors;
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
        List<Daoregister> pendindDoctors = dBconnection.getAllPendingDoctors();

        pendingDoctorList = rootView.findViewById(R.id.pendingDoctor);
        names = new String[pendindDoctors.size()];
        docNic = new String[pendindDoctors.size()];
        for(int i = 0; i< pendindDoctors.size(); i++)
        {
            String fname = pendindDoctors.get(i).getFirstname();
            String lname = pendindDoctors.get(i).getLastname();
            String docNic = pendindDoctors.get(i).getNic();
            String FullName = fname + " " + lname;
            names[i] = FullName;
            this.docNic[i] = docNic;
        }

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,names);
        //pendingDoctorList = rootView.findViewById(R.id.pendingDoctor);

        PendingFragment.MyAdapter adapter = new PendingFragment.MyAdapter(getActivity().getApplicationContext(),names, docNic);

        /*
        *
        AdminViewSpecialization.MyAdapter adapter = new AdminViewSpecialization.MyAdapter(this,specialList,DepartmentList,keyList);
        spcializationList.setAdapter(adapter);

        * */
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

        pendingDoctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) pendingDoctorList.getItemAtPosition(i);
                dBconnection.approveDoctorRequest(name);
                Log.d(TAG, "Approve Method: Called.");
                toastMessage("Doctor is Approved");

            }
        });


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



    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String name[];
        String nic[];


        MyAdapter(Context c, String name[],String[] nic)
        {
            super(c,R.layout.pendingddoctorsrow,name);
            this.context = c;
            this.name = name;
            this.nic = nic;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.pendingddoctorsrow,parent,false);
            TextView name = row.findViewById(R.id.pendingDoctor);
            TextView nic = row.findViewById(R.id.pendingDoctorNic);

            name.setText(names[position]);
            nic.setText(docNic[position]);

            return row;
        }
    }



    }


