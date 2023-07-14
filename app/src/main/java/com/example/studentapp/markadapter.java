package com.example.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class markadapter extends ArrayAdapter<markclass> {
    private Context mContext;
    private int mResource;
    public markadapter(@NonNull Context context, int resource, @NonNull ArrayList<markclass> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutlnflater = LayoutInflater.from(mContext);
        convertView=layoutlnflater.inflate(mResource,parent,false);
        TextView topictxt=convertView.findViewById(R.id.titleofmark);
        TextView marktxt=convertView.findViewById(R.id.mark);
        topictxt.setText(getItem(position).getTopic());
        marktxt.setText(getItem(position).getMark());
        return convertView;
    }
}

    class markclass{
        public markclass(String topic, String mark) {
            this.topic = topic;
            this.mark = mark;
        }

        String topic;
        String mark;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }