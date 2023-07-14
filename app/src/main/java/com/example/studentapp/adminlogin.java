package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        EditText adminusername,adminpassword;
        adminusername=findViewById(R.id.adminusername);
        adminpassword=findViewById(R.id.adminpassword);
        Button adminlogin=findViewById(R.id.adminlogin);
        TextView contactus=findViewById(R.id.admincontactus);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((adminusername.getText().toString()).isEmpty()||(adminpassword.getText().toString()).isEmpty()){
                    Toast.makeText(adminlogin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.child("admin").child("management").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            String username = adminusername.getText().toString();
                            if (username.equals(snapshot.getKey())) {
                                String password = adminpassword.getText().toString();
                                if (password.equals(snapshot.getValue(String.class))){
                                    Toast.makeText(adminlogin.this, "Logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(adminlogin.this,admin_home.class));
                                }
                                else
                                    Toast.makeText(adminlogin.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(adminlogin.this, "User not exist", Toast.LENGTH_SHORT).show();
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
                }
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminlogin.this,contactus.class);
                startActivity(intent);
            }
        });
    }
}