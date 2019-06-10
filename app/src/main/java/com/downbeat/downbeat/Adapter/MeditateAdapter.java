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

public class MeditateAdapter extends RecyclerView.Adapter<MeditateAdapter.ViewHolder> {

    private ArrayList<MeditateInformation> meditateInformations;
    private MeditateAdapterInterface meditateAdapterInterface;

    public MeditateAdapter(Context context, ArrayList<MeditateInformation> meditateInformations) {
        this.meditateInformations = meditateInformations;
        this.meditateAdapterInterface = (MeditateAdapterInterface) context;
    }

    @NonNull
    @Override
    public MeditateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_meditate, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditateAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(meditateInformations.get(i));
        viewHolder.tvAudioName.setText(meditateInformations.get(i).getName());
        viewHolder.imageView.setImageResource(R.drawable.meditatebg);

    }

    @Override
    public int getItemCount() {
        return meditateInformations.size();
    }

    public interface MeditateAdapterInterface {

        void onAudioSelect(int index);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAudioName;
        ImageView imageView;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvAudioName = itemView.findViewById(R.id.tvAudioName);
            imageView = itemView.findViewById(R.id.ivMediaImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meditateAdapterInterface.onAudioSelect(meditateInformations.indexOf(v.getTag()));
                }
            });
        }
    }


}