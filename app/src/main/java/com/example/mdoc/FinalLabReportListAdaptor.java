package com.example.mdoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalLabReportListAdaptor extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<FinalLabReport> reportsList;

    public FinalLabReportListAdaptor(Context context, int layout, ArrayList<FinalLabReport> reportsList) {
        this.context = context;
        this.layout = layout;
        this.reportsList = reportsList;
    }

    public int getCount() {
        return reportsList.size();
    }


    public Object getItem(int position) {
        return reportsList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtID;
    }


    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtID = (TextView) row.findViewById(R.id.txtID);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        FinalLabReport finalLabReport = reportsList.get(position);

        holder.txtName.setText(finalLabReport.getName());
        holder.txtID.setText(finalLabReport.getID());

        byte[] reportImage = finalLabReport.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(reportImage, 0, reportImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
