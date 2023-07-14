package com.example.studentapp;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class codeedit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeedit);
/*        Button button=findViewById(R.id.button2);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.hasChildren()) {
                        String nodeName = childSnapshot.getKey();
                        Log.d("Node Name", nodeName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });



        DatabaseReference db= FirebaseDatabase.getInstance().getReference();

ArrayList<String>students=new ArrayList<>();


        db.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                    students.add(String.valueOf(map));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        for(String elements:students) {
            Log.d("output", elements);
        }

    }
});*/
       /* String[]proctor={"51707","51345","51456"};

        Random rm=new Random();
        int random=rm.nextInt(10);
        Toast.makeText(this, proctor[random], Toast.LENGTH_SHORT).show();*/
        DatabaseReference db=FirebaseDatabase.getInstance().getReference();
        db.child("delete").child("121").child("123").removeValue();


    }
}