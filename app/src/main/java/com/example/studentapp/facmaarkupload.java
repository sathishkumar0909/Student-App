package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class facmaarkupload extends AppCompatActivity {
String regno;
String selectedcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facmaarkupload);
        regno=getIntent().getStringExtra("regno");
        selectedcourse=getIntent().getStringExtra("selectedcourse");
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText examname=findViewById(R.id.examname);
        EditText markexam=findViewById(R.id.markofexam);
        Button upload=findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic=examname.getText().toString();
                String mark=markexam.getText().toString();
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child("user").child(regno).child("mark").child(selectedcourse).child(topic).child("mark").setValue(mark);
                        Toast.makeText(facmaarkupload.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });





    }
}