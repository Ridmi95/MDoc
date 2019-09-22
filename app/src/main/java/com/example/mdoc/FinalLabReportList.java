package com.example.mdoc;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FinalLabReportList extends AppCompatActivity {

    GridView gridView;
    ArrayList<FinalLabReport> list;
    FinalLabReportListAdaptor adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new FinalLabReportListAdaptor(this, R.layout.report_item, list);
        gridView.setAdapter((ListAdapter) adapter);

        // get all data from sqlite
        Cursor cursor = null;
        try {
             cursor = FinalLabMainActivity.dBconnection.getData("SELECT * FROM RECORD");
        }catch(NullPointerException ex){
            Toast toast = Toast.makeText(getApplicationContext(),"No Data ", Toast.LENGTH_LONG);
            toast.show();
        }
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String rID = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new FinalLabReport(name, rID, image, id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(FinalLabReportList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = FinalLabMainActivity.dBconnection.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(FinalLabReportList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = FinalLabMainActivity.dBconnection.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
            ImageView imageViewReport;
            private void showDialogUpdate(Activity activity, final int position){

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.update_report_activity);
                dialog.setTitle("Update");

                imageViewReport = (ImageView) dialog.findViewById(R.id.imgViewReport);
                final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
                final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtID);
                Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

                // set width for dialog
                int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
                // set height for dialog
                int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                imageViewReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // request photo library
                        ActivityCompat.requestPermissions(
                                FinalLabReportList.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                888
                        );
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            FinalLabMainActivity.dBconnection.updateData(
                                    edtName.getText().toString().trim(),
                                    edtPrice.getText().toString().trim(),
                                    FinalLabMainActivity.imageViewToByte(imageViewReport),
                                    position
                            );
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception error) {
                            Log.e("Update error", error.getMessage());
                        }
                        updateFoodList();
                    }
                });
            }

            private void showDialogDelete(final int idFood){
                final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(FinalLabReportList.this);

                dialogDelete.setTitle("Warning!!");
                dialogDelete.setMessage("Are you sure you want to this delete?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            FinalLabMainActivity.dBconnection.deleteData(idFood);
                            Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                        } catch (Exception e){
                            Log.e("error", e.getMessage());
                        }
                        updateFoodList();
                    }
                });

                dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogDelete.show();
            }

            private void updateFoodList(){
                // get all data from sqlite
                Cursor cursor = FinalLabMainActivity.dBconnection.getData("SELECT * FROM RECORD");
                list.clear();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String ID = cursor.getString(2);
                    byte[] image = cursor.getBlob(3);

                    list.add(new FinalLabReport(name, ID, image, id));
                }
                adapter.notifyDataSetChanged();
            }


            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

                if(requestCode == 888){
                    if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 888);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                FinalLabReportList.super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }


            protected void onActivityResult(int requestCode, int resultCode, Intent data) {

                if(requestCode == 888 && resultCode == RESULT_OK && data != null){
                    Uri uri = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageViewReport.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                FinalLabReportList.super.onActivityResult(requestCode, resultCode, data);
            }
        }
