package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class markselection extends AppCompatActivity {

    String regno;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markselection);
        ListView selectionlist=findViewById(R.id.markselectionlist);
        regno=getIntent().getStringExtra("regno");
        pd = new ProgressDialog(this);
        pd.setMessage("Loading....");
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
       ArrayList courses=new ArrayList();
        pd.show();
        db.child("user").child(regno).child("mark").addListenerForSingleValueEvent(new ValueEventListener() {
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
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.markselectionview,R.id.markselectiondisp, courses);
                selectionlist.setAdapter(adapter);
                pd.dismiss();
            }
        },2000);
        selectionlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(markselection.this,markdisplay.class);
                intent.putExtra("selected",courses.get(i).toString());
                intent.putExtra("regno",regno);
                startActivity(intent);
            }
        });
 }
}