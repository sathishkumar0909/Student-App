package com.example.studentapp;

import static java.util.Arrays.compare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class courseselection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseselection);

        Intent intent=getIntent();
        String regno=intent.getStringExtra("regno");

        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        List<String> mycourselist=new ArrayList<>();

        Spinner course1,course2,course3,course4,course5,course6;
        course1=findViewById(R.id.course1);
        course2=findViewById(R.id.course2);
        course3=findViewById(R.id.course3);
        course4=findViewById(R.id.course4);
        course5=findViewById(R.id.course5);
        course6=findViewById(R.id.course6);
        Button submit=findViewById(R.id.submit);

        String [] test={"Android Programming","Computer Networks","Datamining","Game Programming","Information And System Security",
                "Java Programming","Knowledge Engineering","Operating System","Organizational Behaviour","Software Project Management","Software Reuse","Web Technologies"};

        db.child("courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getKey();
               mycourselist.add(value);

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

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.forspinner,test);
        course1.setAdapter(arrayAdapter);
        course2.setAdapter(arrayAdapter);
        course3.setAdapter(arrayAdapter);
        course4.setAdapter(arrayAdapter);
        course5.setAdapter(arrayAdapter);
        course6.setAdapter(arrayAdapter);



       course1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String st= test[i];
               db.child("user").child(regno).child("courses").child("9:00-9:50").setValue(st);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        course2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st2= test[i];
                db.child("user").child(regno).child("courses").child("9:55-10:45").setValue(st2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        course3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st3= test[i];
                db.child("user").child(regno).child("courses").child("10:50-11:40").setValue(st3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        course4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st4= test[i];
                db.child("user").child(regno).child("courses").child("2:00-2:50").setValue(st4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        course5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st5= test[i];
                db.child("user").child(regno).child("courses").child("2:55-3:45").setValue(st5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        course6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st6= test[i];
                db.child("user").child(regno).child("courses").child("3:50-4:40").setValue(st6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(courseselection.this,login.class);
                startActivity(intent1);
            }
        });


    }
}

