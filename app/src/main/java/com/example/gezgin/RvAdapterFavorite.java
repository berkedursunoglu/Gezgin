package com.example.gezgin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdapterFavorite extends RecyclerView.Adapter<RvAdapterFavorite.CvHolder> {
    private ArrayList<String > placesArrayList;

    public RvAdapterFavorite(ArrayList<String> placesArrayList) {
        this.placesArrayList = placesArrayList;
    }

    @NonNull
    @Override
    public CvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_cardview,parent,false);
        return new CvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CvHolder holder, int position) {
        holder.favoritename.setText(placesArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return placesArrayList.size();
    }

    public class CvHolder extends RecyclerView.ViewHolder{
        public TextView favoritename,favoriterates;
        public ImageView favoriteimage;


        public CvHolder(@NonNull View itemView) {
            super(itemView);
            favoriteimage = itemView.findViewById(R.id.favoriteimage);
            favoriterates = itemView.findViewById(R.id.favoriterates);
            favoritename = itemView.findViewById(R.id.favoritename);
        }
    }

}
