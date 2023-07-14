package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText custname,custmail,custphone,custmsg;
        custname=findViewById(R.id.custname);
        custphone=findViewById(R.id.custphone);
        custmail=findViewById(R.id.custmail);
        custmsg=findViewById(R.id.custmsg);
        Button custsubmit=findViewById(R.id.contactussubmit);

        custsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child("contactus").child(custname.getText().toString()).child("name").setValue(custname.getText().toString());
                        db.child("contactus").child(custname.getText().toString()).child("phone").setValue(custphone.getText().toString());
                        db.child("contactus").child(custname.getText().toString()).child("mail").setValue(custmail.getText().toString());
                        db.child("contactus").child(custname.getText().toString()).child("msg").setValue(custmsg.getText().toString());
                        Toast.makeText(contactus.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}