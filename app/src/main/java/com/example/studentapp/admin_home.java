package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        LinearLayout profileview=findViewById(R.id.profileview);
        LinearLayout markupload=findViewById(R.id.markbtn);
        LinearLayout notification=findViewById(R.id.notification);
        LinearLayout facattendance=findViewById(R.id.facattendancebtn);
        LinearLayout addproctor=findViewById(R.id.addproctor);
        LinearLayout examschedule=findViewById(R.id.examschedule);
        LinearLayout logout=findViewById(R.id.adminlogout);

        profileview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,profileselection.class));
            }
        });
        markupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,facmarkselection.class));
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,adminnotification.class));
            }
        });
        facattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(admin_home.this,facattendanceselection.class));
            }
        });
        addproctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,addproctor.class));
            }
        });
        examschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,adminexamschedule.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home.this,splashscreen.class));
            }
        });

    }
}