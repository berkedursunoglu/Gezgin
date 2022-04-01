package com.example.gezgin;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CvHolder> {

    private ArrayList<Places> stringArrayList;

    public RvAdapter(ArrayList<Places> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    @NonNull
    @Override
    public CvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_cardview, parent, false);
        return new CvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CvHolder holder, int position) {
        holder.placenames.setText(stringArrayList.get(position).getPlace_name());
        holder.placerates.setText(String.valueOf(stringArrayList.get(position).getPlace_rate()));
        Picasso.get().load(stringArrayList.get(position).getIcon()).into(holder.placeimages);

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class CvHolder extends RecyclerView.ViewHolder {
        public TextView placenames, placerates;
        public ImageView placeimages;

        public CvHolder(View view) {
            super(view);
            placenames = view.findViewById(R.id.placenames);
            placerates = view.findViewById(R.id.placerates);
            placeimages = view.findViewById(R.id.placeimage);
        }
    }
}
