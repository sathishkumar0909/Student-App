package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class timetable extends AppCompatActivity {
    ProgressDialog pd;
    private ListView listView;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> itemList;
    String regno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable1);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading....");
        pd.show();
        regno=getIntent().getStringExtra("regno");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        ListView listView=findViewById(R.id.listview);
        itemList = new ArrayList<>();
//getting course and time
        db.child("user").child(regno).child("courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String time=snapshot.getKey();
                String subject=snapshot.getValue(String.class);
                itemList.add(new Item(subject,time));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    /*    // Add some sample items to the list
        itemList.add(new Item("John Doe", "android","9:30"));
        itemList.add(new Item("John Doe", "android","9:30"));*/


        // Initialize the adapter and set it to the list view
        adapter = new ArrayAdapter<>(this, R.layout.cardview,R.id.coursetitle, itemList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView time = view.findViewById(R.id.time);
                TextView coursetitle = view.findViewById(R.id.coursetitle);

                Item item = itemList.get(position);

                coursetitle.setText("Course :"+item.getCourse());
                time.setText("Time :"+item.getTime());
                return view;
            }
        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
pd.dismiss();
            }
        },2000);
    }
    private static class Item {
        //private String fname;
        private String course;
        private String time;

        public Item(String course,String time) {
           // this.fname = name;
            this.course = course;
            this.time= time;
        }

        /*public String getName() {
            return fname;
        }*/

        public String getCourse() {
            return course;
        }
        public String getTime() {
            return time;
        }
    }
}

