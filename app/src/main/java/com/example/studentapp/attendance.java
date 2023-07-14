package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class attendance extends AppCompatActivity {
String regno;
ProgressDialog pd;
    int count=0;
    String [] percentage={"percentage0","percentage1","percentage2","percentage3","percentage4","percentage5"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        regno=getIntent().getStringExtra("regno");
        ListView attendancelist=findViewById(R.id.attendancelist);
        ArrayList courses=new ArrayList();
        ArrayList percentage0=new ArrayList();
        ArrayList percentage1=new ArrayList();
        ArrayList percentage2=new ArrayList();
        ArrayList percentage3=new ArrayList();
        ArrayList percentage4=new ArrayList();
        ArrayList percentage5=new ArrayList();
        ArrayList<attendanceclass>attendance=new ArrayList<>();
        attendanceadapter adapter=new attendanceadapter(this,R.layout.attendanceview,attendance);

        db.child("user").child(regno).child("attendance").addListenerForSingleValueEvent(new ValueEventListener() {
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
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<courses.size();i++){
                    int finalI = i;
                    int finalI1 = i;
                    db.child("user").child(regno).child("attendance").child(courses.get(i).toString()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (finalI == 0) {
                                if (snapshot.getValue(String.class).equals("Present")) {
                                    percentage0.add(snapshot.getValue(String.class));
                                }
                            }
                            if (finalI == 1) {
                                if (snapshot.getValue(String.class).equals("Present")) {
                                    percentage1.add(snapshot.getValue(String.class));
                                }
                            }
                            if (finalI == 2) {
                                if (snapshot.getValue(String.class).equals("Present")) {
                                    percentage2.add(snapshot.getValue(String.class));
                                }
                            }
                            if (finalI == 3) {
                                if (snapshot.getValue(String.class).equals("Present")) {
                                    percentage3.add(snapshot.getValue(String.class));
                                }
                            }
                            if (finalI == 4) {
                                if (snapshot.getValue(String.class).equals("Present")) {
                                    percentage4.add(snapshot.getValue(String.class));
                                }
                                if (finalI == 5) {
                                    if (snapshot.getValue(String.class).equals("Present")) {
                                        percentage4.add(snapshot.getValue(String.class));
                                    }
                                }
                            }
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
                }pd.dismiss();
            }
        },2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                attendancelist.setAdapter(adapter);
                for (int i=0;i<courses.size();i++) {
                    String course=courses.get(i).toString();
                    if (i==0){
                        String value= String.valueOf(percentage0.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }if (i==1){
                        String value= String.valueOf(percentage1.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }if (i==2){
                        String value= String.valueOf(percentage2.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }if (i==3){
                        String value= String.valueOf(percentage3.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }if (i==4){
                        String value= String.valueOf(percentage4.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }if (i==5){
                        String value= String.valueOf(percentage5.size());
                        attendance.add(new attendanceclass(course, value, "5"));
                    }

                }
            }
        },3000);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("output",""+percentage0.size());
            }
        },3000);*/

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String country = percentage.get(0).toString();
                String value=""+country.charAt(5);
                String separator =":";
                int sepPos = country.indexOf(separator);
                Log.d("output",""+country.substring(sepPos + separator.length()));

            }
        },3000);*/

    }
}