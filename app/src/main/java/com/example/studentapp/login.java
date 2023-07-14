package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registerbtn=findViewById(R.id.registerbtn);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        Button loginbtn = findViewById(R.id.loginbtn);
        TextView facultyloginbtn = findViewById(R.id.facultylogin);

        DatabaseReference db=FirebaseDatabase.getInstance().getReference();

     /*   FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String a =myRef.get().toString();
        Log.d("Message",a);*/

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regnotxt=username.getText().toString();
                String passwordtxt=password.getText().toString();
                if (regnotxt.isEmpty()||passwordtxt.isEmpty()){
                    Toast.makeText(login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                        db.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(regnotxt)) {
                                    final String getpassword = snapshot.child(regnotxt).child("password").getValue(String.class);
                                    if (getpassword.equals(passwordtxt)) {
                                            Intent intent = new Intent(login.this, landingpage.class);
                                            intent.putExtra("regno", regnotxt);
                                            startActivity(intent);
                                            Toast.makeText(login.this, "Logged in", Toast.LENGTH_SHORT).show();



                                    } else {
                                        Toast.makeText(login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(login.this, "User not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }


        });
        facultyloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,adminlogin.class));
            }
        });

    }
}