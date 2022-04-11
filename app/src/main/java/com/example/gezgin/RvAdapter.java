package com.example.gezgin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gezgin.retrofit.ApiUtils;
import com.example.gezgin.retrofit.UserDAOInterface;
import com.example.gezgin.retrofit.UserResponse.CRUDResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CvHolder> {

    private ArrayList<Places> stringArrayList;
    private Context mContext;

    public RvAdapter(ArrayList<Places> stringArrayList,Context mContext) {
        this.stringArrayList = stringArrayList;
        this.mContext = mContext;
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
        holder.favcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shared shared = new Shared(mContext);
                int id = shared.getAccount_idpref();
                String places_name = stringArrayList.get(position).getPlace_name();
                String places_img = stringArrayList.get(position).getIcon();
                String places_rate = stringArrayList.get(position).getPlace_rate().toString();

                UserDAOInterface DAO = ApiUtils.getuserDaoInterface();
                DAO.set_places(places_name,places_img,places_rate,id).enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                        if (response.body().getSuccess() ==1){
                            Toast.makeText(mContext,"Favorilere Eklendi",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CRUDResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        try{
            stringArrayList.size();
        }catch (NullPointerException exception){
            Toast.makeText(mContext,"Yakınlarda Mekan Yok.",Toast.LENGTH_SHORT).show();
        }
        return stringArrayList.size();
    }

    public class CvHolder extends RecyclerView.ViewHolder {
        public TextView placenames, placerates;
        public ImageView placeimages;
        public ImageButton favcardview;

        public CvHolder(View view) {
            super(view);
            placenames = view.findViewById(R.id.favoritename);
            placerates = view.findViewById(R.id.favoriterates);
            placeimages = view.findViewById(R.id.favoriteimage);
            favcardview = view.findViewById(R.id.favcardview);
        }
    }
}
