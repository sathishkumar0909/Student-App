package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class register extends AppCompatActivity {
    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;
    String imageUrl;
    ProgressDialog pd;
    DatabaseReference db= FirebaseDatabase.getInstance().getReference();
    String proctorempid;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://jcomponent-1f902.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ArrayList proctor=new ArrayList<>();
        Random rm=new Random();



        EditText regno=findViewById(R.id.regno);
        EditText name=findViewById(R.id.name);
        EditText phone=findViewById(R.id.phone);
        EditText email=findViewById(R.id.email);
        EditText passsword=findViewById(R.id.password);
        EditText conpassword=findViewById(R.id.conpassword);
        Button registerbtn=findViewById(R.id.registerbtn);
        Button choosebtn=findViewById(R.id.choosebtn);
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");


        db.child("Proctor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    if (childSnapshot.hasChildren()) {
                        String nodeName = childSnapshot.getKey();
                        proctor.add(nodeName);
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
        int random=rm.nextInt(proctor.size());
        proctorempid= proctor.get(random).toString();
    }
},2000);


        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nametxt=name.getText().toString();
                String phonetxt=phone.getText().toString();
                String emailtxt=email.getText().toString();
                String passwordtxt=passsword.getText().toString();
                String conpasswordtxt=conpassword.getText().toString();
                String regnotxt=regno.getText().toString();

                //image

                ///imageover

                if (nametxt.isEmpty()||phonetxt.isEmpty()||emailtxt.isEmpty()||passwordtxt.isEmpty()||regnotxt.isEmpty()||conpasswordtxt.isEmpty()){
                    Toast.makeText(register.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

                else if (passwordtxt.equals(conpasswordtxt)) {
                    db.child("user").child(regnotxt).child("regno").setValue(regnotxt);
                    db.child("user").child(regnotxt).child("name").setValue(nametxt);
                    db.child("user").child(regnotxt).child("phone").setValue(phonetxt);
                    db.child("user").child(regnotxt).child("email").setValue(emailtxt);
                    db.child("user").child(regnotxt).child("proctor").setValue(proctorempid);
                    db.child("user").child(regnotxt).child("password").setValue(conpasswordtxt);
                    if(filePath != null) {
                        pd.show();
                        StorageReference childRef = storageRef.child("image.jpg");
                        UploadTask uploadTask = childRef.putFile(filePath);
                        uploadTask.continueWithTask(task -> {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return childRef.getDownloadUrl();
                        }).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                imageUrl = downloadUri.toString();
                                Toast.makeText(register.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else {
                            }
                        });
                    }
                    db.child("user").child(regnotxt).child("profile").setValue(imageUrl);

                    Toast.makeText(register.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(register.this,courseselection.class);
                    intent.putExtra("regno",regnotxt);
                   startActivity(intent);
                }
                else {
                    Toast.makeText(register.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
               // image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}