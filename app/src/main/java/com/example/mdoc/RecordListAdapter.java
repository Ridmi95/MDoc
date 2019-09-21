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

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<LanModel> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<LanModel> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewMolder{
        ImageView imageView;
        TextView txtName,txtAge;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewMolder holder = new ViewMolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtAge = row.findViewById(R.id.txtAge);
            holder.imageView = row.findViewById(R.id.imageView);
            row.setTag(holder);
        }
        else {
            holder = (ViewMolder)row.getTag();
        }

        LanModel lanModel = recordList.get(i);

        holder.txtName.setText(lanModel.getName());
        holder.txtAge.setText(lanModel.getAge());

        byte[] recordImage = lanModel.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
