package com.downbeat.downbeat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.downbeat.downbeat.Models.MeditateInformation;
import com.downbeat.downbeat.R;

import java.util.ArrayList;

public class MeditateAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<MeditateInformation> imageModelArrayList;

    public MeditateAdapter(Context ctx , ArrayList<MeditateInformation> imageModelArrayList){
        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = inflater.inflate(R.layout.recycler_item_meditate , viewGroup , false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.iv.setImageResource(imageModelArrayList.get(i).getUrl());
        myViewHolder.text.setText(imageModelArrayList.get(i).getMeditateName());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView text;
    ImageView iv;

    public MyViewHolder(View itemView){
        super(itemView);
        text = itemView.findViewById(R.id.tv);
        iv = itemView.findViewById(R.id.iv);
    }

}
