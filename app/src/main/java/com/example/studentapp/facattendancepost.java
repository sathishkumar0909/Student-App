package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class facattendancepost extends AppCompatActivity {
    String regno;
    String selectedcourse;
    String mark="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facattendancepost);
        regno=getIntent().getStringExtra("regno");
        selectedcourse=getIntent().getStringExtra("selectedcourse");
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText examname=findViewById(R.id.date);
        Spinner status=findViewById(R.id.status);
        Button upload=findViewById(R.id.uploadatt);
        String [] statuslist={"Present","Absent"};
        ArrayAdapter adapter=new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,statuslist);
        status.setAdapter(adapter);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mark=statuslist[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic=examname.getText().toString();
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child("user").child(regno).child("attendance").child(selectedcourse).child(topic).setValue(mark);
                        Toast.makeText(facattendancepost.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });





    }
}