package com.daneshnaik.picturawalls.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daneshnaik.picturawalls.R;
import com.daneshnaik.picturawalls.classes.main_page;
import com.daneshnaik.picturawalls.setWallaper;

import java.util.ArrayList;

public class mainpageAdapter extends RecyclerView.Adapter<mainpageAdapter.ViewHolder> {
    Context context;
    ArrayList<main_page> mainPages;
    public mainpageAdapter(Context context,ArrayList<main_page> mainPages){
        this.context=context;
        this.mainPages=mainPages;
    }
    @NonNull
    @Override
    public mainpageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_image_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mainpageAdapter.ViewHolder holder, int position) {
        main_page mainPage=mainPages.get(position);
        Glide.with(context).load(mainPage.getUrl()).placeholder(R.drawable.back_another).into(holder.single_imageview_holder);
        holder.single_holder_name.setText(mainPage.getImage_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, setWallaper.class);
                intent.putExtra("image",mainPage.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainPages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView single_imageview_holder;
        TextView single_holder_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            single_imageview_holder=itemView.findViewById(R.id.single_imageview_holder);
            single_holder_name=itemView.findViewById(R.id.single_holder_name);
        }
    }
}
