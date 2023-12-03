package com.daneshnaik.picturawalls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mainScreen extends AppCompatActivity {
ImageView image1,image5;
FloatingActionButton floating_mainscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        image1=findViewById(R.id.imageview_1);
        image5=findViewById(R.id.imageview_5);
        Glide.with(this).load(R.drawable.anime_gg).centerCrop()
                .into(image1);
        Glide.with(this).load(R.drawable.nature_gif).into(image5);




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