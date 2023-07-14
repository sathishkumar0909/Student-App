package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminexamschedule extends AppCompatActivity {

    String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminexamschedule);
        ArrayList courses=new ArrayList();
        Spinner selectedcourse=findViewById(R.id.adminexamcoursespin);
        EditText examdate=findViewById(R.id.adminexamdate);
        EditText examtime=findViewById(R.id.adminexamtime);
        EditText examvenue=findViewById(R.id.adminexamvenue);
        Button examupload=findViewById(R.id.adminexamupload);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db.child("courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if (childSnapshot.hasChildren()) {
                        String nodeName = childSnapshot.getKey();
                        courses.add(nodeName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter adapter=new ArrayAdapter(adminexamschedule.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,courses);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectedcourse.setAdapter(adapter);
            }
        },2000);
        selectedcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 course = courses.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        examupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               db.child("Schedule").child(course).child("Date").setValue(examdate.getText().toString());
               db.child("Schedule").child(course).child("Time").setValue(examtime.getText().toString());
               db.child("Schedule").child(course).child("Venue").setValue(examvenue.getText().toString());
                Toast.makeText(adminexamschedule.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}