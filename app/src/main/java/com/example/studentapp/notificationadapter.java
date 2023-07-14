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

public class notificationadapter extends ArrayAdapter<notificationclass> {
    private Context mContext;
    private int mResource;
    public notificationadapter(@NonNull Context context, int resource, @NonNull ArrayList<notificationclass> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
}
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutlnflater = LayoutInflater.from(mContext);
        convertView=layoutlnflater.inflate(mResource,parent,false);
        TextView sender=convertView.findViewById(R.id.sender);
        TextView msg=convertView.findViewById(R.id.msg);
        sender.setText(getItem(position).getSender());
        msg.setText(getItem(position).getMsg());
        return convertView;
    }
}
class notificationclass{
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public notificationclass(String sender, String msg) {
        this.sender = sender;
        this.msg = msg;
    }

    String sender;
    String msg;
}
