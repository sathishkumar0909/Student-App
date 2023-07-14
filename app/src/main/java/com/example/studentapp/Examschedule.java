package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Examschedule extends AppCompatActivity {
String regno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examschedule);
        regno=getIntent().getStringExtra("regno");
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        ListView examschedulelist=findViewById(R.id.examschedulelist);
        ArrayList<String> courselist=new ArrayList<>();
        ArrayList<examclass> examschedule=new ArrayList<>();
        db.child("user").child(regno).child("courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(String.class);
                courselist.add(value);
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int  i=0;i<courselist.size();i++){
                    int finalI = i;
                    db.child("Schedule").child(courselist.get(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        String subject=courselist.get(finalI);
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Date=snapshot.child("Date").getValue(String.class);
                            String Venue=snapshot.child("Venue").getValue(String.class);
                            String Time=snapshot.child("Time").getValue(String.class);
                            examschedule.add(new examclass(subject,Date,Venue,Time));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        },2000);

        examadapter examadapter=new examadapter(Examschedule.this,R.layout.examscheduleview,examschedule);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                examschedulelist.setAdapter(examadapter);
            }
        },3000);

    }
}