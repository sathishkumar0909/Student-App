package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class markdisplay extends AppCompatActivity {
    String selected;
    String regno;
ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdisplay);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        ListView markdisplay=findViewById(R.id.markdisplay);
        selected=getIntent().getStringExtra("selected");
        regno=getIntent().getStringExtra("regno");
        ArrayList <markclass> upload1=new ArrayList<>();
        pd = new ProgressDialog(this);
        pd.setMessage("Loading....");
        pd.show();

        db.child("user").child(regno).child("mark").child(selected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if (childSnapshot.hasChildren()) {
                    String key = childSnapshot.getKey();
                          String value = childSnapshot.child("mark").getValue(String.class);

                        upload1.add(new markclass(key, value));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        markadapter arrayAdapter=new markadapter(markdisplay.this,R.layout.markview,upload1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (upload1.isEmpty()){
                    Toast.makeText(markdisplay.this, "No records found", Toast.LENGTH_SHORT).show();
                }
                markdisplay.setAdapter(arrayAdapter);
                pd.dismiss();
            }
        },2000);


    }
}