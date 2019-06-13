package com.downbeat.downbeat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.downbeat.downbeat.Models.BlogInformation;
import com.downbeat.downbeat.R;

import java.util.ArrayList;
import java.util.Objects;

public class BlogAdapter  extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BlogInformation> imageModelArrayList;

    public BlogAdapter(Context context , ArrayList<BlogInformation> imageModelArrayList){
        inflater = LayoutInflater.from(context);
        this.imageModelArrayList = imageModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_blog , parent , false);
        MyViewHolder holder = new MyViewHolder(view);

        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.blogTitle.setText(imageModelArrayList.get(position).getBlogTitle());
        holder.blogImage.setImageResource(imageModelArrayList.get(position).getImageUrl());


    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView blogImage;
        TextView blogTitle;

        MyViewHolder(View view) {
            super(view);

            blogImage = view.findViewById(R.id.blogImageView);
            blogTitle = view.findViewById(R.id.blogTextView);

        }
    }
}
