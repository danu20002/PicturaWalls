package com.daneshnaik.picturawalls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ProgressBar progress_main_activity;
    ImageView relativeLayout_main_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_main_activity=findViewById(R.id.progressbar_mainactivity);
        progress_main_activity.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress_main_activity.setVisibility(View.INVISIBLE);
                startActivity(new Intent(MainActivity.this, mainScreen.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        },2000);
        relativeLayout_main_activity=findViewById(R.id.relative_mina_activity);
        Glide.with(this).load(R.drawable.entry_gif).into(relativeLayout_main_activity);

    }
}