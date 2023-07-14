package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileviewnew);
        ImageView userimagepro=findViewById(R.id.userimagepro);
        TextView regnopro,namepro,phonepro,emailpro;
        regnopro=findViewById(R.id.regnopro);
        namepro=findViewById(R.id.namepro);
        phonepro=findViewById(R.id.phonepro);
        emailpro=findViewById(R.id.emailpro);

        Intent intent=getIntent();
        String username=intent.getStringExtra("username");

        DatabaseReference db= FirebaseDatabase.getInstance().getReference();

        db.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(username)){
                    namepro.setText(snapshot.child(username).child("name").getValue(String.class));
                    regnopro.setText("Register Number: "+snapshot.child(username).child("regno").getValue(String.class));
                    phonepro.setText("Phone: "+snapshot.child(username).child("phone").getValue(String.class));
                    emailpro.setText("Mail: "+snapshot.child(username).child("email").getValue(String.class));
                    Picasso.get().load("https://wallpaperaccess.com/full/3851354.jpg").into(userimagepro);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}