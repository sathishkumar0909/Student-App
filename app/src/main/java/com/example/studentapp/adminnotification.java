package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminnotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminnotification);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText facmsg=findViewById(R.id.facmsg);
        EditText facname=findViewById(R.id.facname);
        Button facupload=findViewById(R.id.facupload);

        facupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=facmsg.getText().toString();
                db.child("Notification").child(facname.getText().toString()).setValue(message);
                Toast.makeText(adminnotification.this, "Successfully Published", Toast.LENGTH_SHORT).show();
            }
        });
    }
}