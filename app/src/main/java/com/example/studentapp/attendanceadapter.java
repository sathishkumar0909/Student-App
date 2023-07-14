package com.example.studentapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class attendanceadapter extends ArrayAdapter<attendanceclass> {
    private Context mContext;
    private int mResource;
    public attendanceadapter(@NonNull Context context, int resource, @NonNull ArrayList<attendanceclass> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutlnflater = LayoutInflater.from(mContext);
        convertView=layoutlnflater.inflate(mResource,parent,false);
        TextView coursename=convertView.findViewById(R.id.coursename);
        TextView percentage=convertView.findViewById(R.id.percentage);
        ProgressBar bar=convertView.findViewById(R.id.attendancebar);
        coursename.setText(getItem(position).getCourse());
        percentage.setText(getItem(position).getPercentage());
        bar.setProgress(Integer.parseInt(getItem(position).getPercentage()));   // Main Progress
        bar.setSecondaryProgress(Integer.parseInt(getItem(position).getPercentage()));
        bar.setMax(Integer.parseInt(getItem(position).getMax()));

        return convertView;
    }
}
class attendanceclass{
    String course;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public attendanceclass(String course, String percentage, String max) {
        this.course = course;
        this.percentage = percentage;
        this.max = max;
    }

    String percentage;
    String max;
}