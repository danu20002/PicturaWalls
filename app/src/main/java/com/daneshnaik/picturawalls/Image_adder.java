package com.daneshnaik.picturawalls;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daneshnaik.picturawalls.classes.main_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Image_adder extends AppCompatActivity {
TextInputEditText name_of_image_mainpage;
ImageView imageview_mianpage_image;
AppCompatButton add_btn_mainpage;

FirebaseStorage storage;
FirebaseDatabase database;
    Uri selectimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_adder);
        name_of_image_mainpage=findViewById(R.id.name_of_image);
        imageview_mianpage_image=findViewById(R.id.image_of_mainpage);
        add_btn_mainpage=findViewById(R.id.image_add_btn_mainpage);


        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        imageview_mianpage_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });


        add_btn_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_image=name_of_image_mainpage.getEditableText().toString().trim();
                if(selectimage!=null){
                    if(!name_image.isEmpty()){
                        StorageReference reference=storage.getReference().child("Mainpage").child(name_image);
                        reference.putFile(selectimage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageurl=uri.toString();
                                            String name_of_image=name_of_image_mainpage.getEditableText().toString().trim();
                                            main_page mainpages=new main_page(name_of_image,imageurl);
                                            database.getReference().child("Main_page").push().setValue(mainpages).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    startActivity(new Intent(Image_adder.this, mainScreen.class));
                                                    finish();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Image_adder.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Image_adder.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Image_adder.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        Toast.makeText(Image_adder.this, "Please Give Name", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Image_adder.this, "Add Image Please", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getData() != null) {
                imageview_mianpage_image.setImageURI(data.getData());
                selectimage = data.getData();
            }
        }
    }

}