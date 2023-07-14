package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        LinearLayout coursebtn=findViewById(R.id.coursebtn);
        LinearLayout attendancebtn=findViewById(R.id.attendancebtn);
        LinearLayout markbtn=findViewById(R.id.markbtn);
        LinearLayout facultybtn=findViewById(R.id.facultybtn);
        LinearLayout profilebtn=findViewById(R.id.profile);
        LinearLayout notificationbtn=findViewById(R.id.notification);
        LinearLayout examschedulebtn=findViewById(R.id.examschedulebtn);
        LinearLayout timetablebtn=findViewById(R.id.timetable);
        LinearLayout proctorbtn=findViewById(R.id.proctorbtn);
        LinearLayout logoutbtn=findViewById(R.id.logoutbtn);
        LinearLayout contactusbtn=findViewById(R.id.contactusbtn);
        TextView welcome=findViewById(R.id.welcome);
        ImageView userimage=findViewById(R.id.userimage);
        Picasso.get().load("https://wallpaperaccess.com/full/3851354.jpg").into(userimage);

        String regno=getIntent().getStringExtra("regno");

        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(regno)){
                    String name=snapshot.child(regno).child("name").getValue(String.class);
                    welcome.setText("Welcome "+name+" !!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilechange=new Intent(landingpage.this ,profile.class);
                profilechange.putExtra("username",regno);
                startActivity(profilechange);
                    }
                });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilechange=new Intent(landingpage.this ,profile.class);
                profilechange.putExtra("username",regno);
                startActivity(profilechange);
            }
        });

        coursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,courses.class);
                coursechange.putExtra("username",regno);
                startActivity(coursechange);
            }
        });
        markbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,markselection.class);
                coursechange.putExtra("regno",regno);
                startActivity(coursechange);
            }
        });
        facultybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,faculty.class);
                coursechange.putExtra("regno",regno);
                startActivity(coursechange);
            }
        });
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,notification.class);
                coursechange.putExtra("regno",regno);
                startActivity(coursechange);
            }
        });
        attendancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,attendance.class);
                coursechange.putExtra("regno",regno);
                startActivity(coursechange);
            }
        });
        examschedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coursechange=new Intent(landingpage.this ,Examschedule.class);
                coursechange.putExtra("regno",regno);
                startActivity(coursechange);
            }
        });
        timetablebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(landingpage.this ,timetable.class);
                intent.putExtra("regno",regno);
                startActivity(intent);
            }
        });
        proctorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(landingpage.this ,proctor.class);
                intent.putExtra("regno",regno);
                startActivity(intent);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(landingpage.this ,login.class);
                Toast.makeText(landingpage.this, "Logged Out Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        contactusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(landingpage.this ,contactus.class);
                startActivity(intent);
            }
        });


    }
}