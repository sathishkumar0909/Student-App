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

public class addproctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproctor);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText proname,procabin,prodesignation,promail,proempid,promobile,proschool;
        Button submit=findViewById(R.id.addproctorregisterbtn);
        proname=findViewById(R.id.proname);
        procabin=findViewById(R.id.procabin);
        prodesignation=findViewById(R.id.prodesignation);
        promail=findViewById(R.id.proemail);
        proempid=findViewById(R.id.proempid);
        promobile=findViewById(R.id.prophone);
        proschool=findViewById(R.id.proschool);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child("Proctor").child(proempid.getText().toString()).child("cabin").setValue(procabin.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("designation").setValue(prodesignation.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("email").setValue(promail.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("empid").setValue(proempid.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("mobile").setValue(promobile.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("name").setValue(proname.getText().toString());
                        db.child("Proctor").child(proempid.getText().toString()).child("school").setValue(proschool.getText().toString());
                        Toast.makeText(addproctor.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}