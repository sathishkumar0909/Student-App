package com.example.studentapp;

import static android.Manifest.permission.CALL_PHONE;
import static android.app.PendingIntent.getActivity;
import static android.net.Uri.fromParts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class proctor extends AppCompatActivity {
    String regno;
    String proctor;
    String proctornum;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proctor);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        TextView empid,proctorname,proctorcabin,proctordesignation,proctorschool,proctormobile,proctoremail;
        ImageView proctorimg=findViewById(R.id.proctorimage);
                empid=findViewById(R.id.empid);
        proctorname=findViewById(R.id.proctorname);
        proctorcabin=findViewById(R.id.proctorcabin);
        proctordesignation=findViewById(R.id.proctordesignation);
        proctorschool=findViewById(R.id.proctorschool);
        proctormobile=findViewById(R.id.proctornumber);
        proctoremail=findViewById(R.id.proctoremail);
        Button proctorcall=findViewById(R.id.proctorcall);
        regno=getIntent().getStringExtra("regno");
        pd=new ProgressDialog(this);
        pd.setMessage("Loading....");
        pd.show();

        db.child("user").child(regno).child("proctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proctor=snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                db.child("Proctor").child(proctor).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        empid.setText(snapshot.child("empid").getValue(String.class));
                        proctorname.setText(snapshot.child("name").getValue(String.class));
                        proctorcabin.setText(snapshot.child("cabin").getValue(String.class));
                        proctordesignation.setText(snapshot.child("designation").getValue(String.class));
                        proctorschool.setText(snapshot.child("school").getValue(String.class));
                        proctormobile.setText(snapshot.child("mobile").getValue(String.class));
                        proctornum=snapshot.child("mobile").getValue(String.class);
                        proctoremail.setText(snapshot.child("email").getValue(String.class));
                        String url=snapshot.child("profile").getValue(String.class);
                        Picasso.get().load(url).into(proctorimg);
                        pd.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        },2000);

        proctorcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+proctornum));
                startActivity(callIntent);
            }
        });

    }
}