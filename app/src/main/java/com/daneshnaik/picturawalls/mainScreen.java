package com.daneshnaik.picturawalls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.bumptech.glide.Glide;
import com.daneshnaik.picturawalls.Adapters.mainpageAdapter;
import com.daneshnaik.picturawalls.classes.main_page;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mainScreen extends AppCompatActivity {
ImageView image1,image5;
FloatingActionButton floating_mainscreen;
RecyclerView recyclerView_mainscreen;
mainpageAdapter adapter;
ArrayList<main_page> mainPageArrayList;
FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        image1=findViewById(R.id.imageview_1);
        image5=findViewById(R.id.imageview_5);
        Glide.with(this).load(R.drawable.anime_gg).centerCrop()
                .into(image1);
        Glide.with(this).load(R.drawable.nature_gif).into(image5);

        recyclerView_mainscreen=findViewById(R.id.recycle_mainscreen);
        database=FirebaseDatabase.getInstance();

       mainPageArrayList=new ArrayList<>();
        adapter=new mainpageAdapter(this,mainPageArrayList);
        recyclerView_mainscreen.setAdapter(adapter);
//         recyclerView.setVerticalScrollBarEnabled(true);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView_mainscreen.setLayoutManager(layoutManager);

        database.getReference().child("Main_page").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainPageArrayList.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                   main_page mainPage=snapshot1.getValue(main_page.class);
                   mainPageArrayList.add(mainPage);


                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floating_mainscreen=findViewById(R.id.floating_mainscreen);
        floating_mainscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Image_adder.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });




    }
}