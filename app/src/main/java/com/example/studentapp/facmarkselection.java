package com.example.studentapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class facmarkselection extends AppCompatActivity {
        String regno="";
        String selectedcourse="";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_facmarkselection);
            Button show=findViewById(R.id.submit);
            Spinner spinnerprofile=findViewById(R.id.markprofileselection);
            Spinner spinnercourse=findViewById(R.id.markcourseselection);
            ArrayList<String> users=new ArrayList<>();
            ArrayList<String> courses=new ArrayList<>();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.hasChildren()) {
                            String nodeName = childSnapshot.getKey();
                            users.add(nodeName);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("Error", databaseError.getMessage());
                }
            });
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, users);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerprofile.setAdapter(null);
            spinnerprofile.setSelected(false);
// Clear the old adapter and its views

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    spinnerprofile.setAdapter(adapter);
                }
            }, 3000);

            spinnerprofile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    regno= users.get(position).toString();
                    spinnercourse.setAdapter(null);
                    courses.clear();
                    ArrayAdapter<String> myarrayadapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, courses);
                    DatabaseReference db= FirebaseDatabase.getInstance().getReference();
                    db.child("user").child(regno).child("courses").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            String value=snapshot.getValue(String.class);
                            courses.add(value);
                            myarrayadapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            myarrayadapter.notifyDataSetChanged();

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
                    spinnercourse.setAdapter(myarrayadapter);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });
            //courses



            spinnercourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedcourse=courses.get(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(facmarkselection.this,facmaarkupload.class);
                    intent.putExtra("regno",regno);
                    intent.putExtra("selectedcourse",selectedcourse);
                    startActivity(intent);
                }
            });

        }
    }