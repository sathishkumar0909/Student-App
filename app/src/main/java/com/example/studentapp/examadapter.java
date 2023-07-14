package com.example.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class examadapter extends ArrayAdapter<examclass> {
    private Context mContext;
    private int mResource;
    public examadapter(@NonNull Context context, int resource, @NonNull ArrayList<examclass> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutlnflater = LayoutInflater.from(mContext);
        convertView=layoutlnflater.inflate(mResource,parent,false);
        TextView examsub=convertView.findViewById(R.id.examsub);
        TextView examdate=convertView.findViewById(R.id.examdate);
        TextView examvenue=convertView.findViewById(R.id.examvenue);
        TextView examtime=convertView.findViewById(R.id.examtime);
        examsub.setText(getItem(position).getExamsub());
        examdate.setText(getItem(position).getDate());
        examvenue.setText(getItem(position).getVenue());
        examtime.setText(getItem(position).getTime());
        return convertView;
    }
}
class examclass{
    String examsub;
    String date;
    String venue;
    String time;

    public examclass(String examsub, String date, String venue, String time) {
        this.examsub = examsub;
        this.date = date;
        this.venue = venue;
        this.time = time;
    }

    public String getExamsub() {
        return examsub;
    }

    public void setExamsub(String examsub) {
        this.examsub = examsub;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
