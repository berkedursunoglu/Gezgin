package com.example.gezgin;

import android.content.Context;
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

public class RvAdapterFavorite extends RecyclerView.Adapter<RvAdapterFavorite.CvHolder> {
    private ArrayList<Place> placesArrayList;
    private Context mContext;

    public RvAdapterFavorite(ArrayList<Place> placesArrayList, Context mContext) {
        this.placesArrayList = placesArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_cardview,parent,false);
        return new CvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CvHolder holder, int position) {
        holder.favoritename.setText(placesArrayList.get(position).getPlacesName());
        holder.favoriterates.setText(placesArrayList.get(position).getPlacesRate());
        Picasso.get().load(placesArrayList.get(position).getPlacesImg()).into(holder.favoriteimage);
        holder.deletefav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                UserDAOInterface dao = ApiUtils.getuserDaoInterface();
                String places_id = placesArrayList.get(position).getPlacesId();
                dao.delete_fav(Integer.parseInt(places_id)).enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                        if (response.body().getSuccess() == 1){
                            Toast.makeText(mContext,"Favorilerden Kaldırıldı",Toast.LENGTH_SHORT).show();
                            placesArrayList.remove(position);
                            notifyItemRemoved(position);
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
        return placesArrayList.size();
    }

    public class CvHolder extends RecyclerView.ViewHolder{
        public TextView favoritename,favoriterates;
        public ImageView favoriteimage;
        public ImageButton deletefav;


        public CvHolder(@NonNull View itemView) {
            super(itemView);
            favoriteimage = itemView.findViewById(R.id.favoriteimage);
            favoriterates = itemView.findViewById(R.id.favoriterates);
            favoritename = itemView.findViewById(R.id.favoritename);
            deletefav = itemView.findViewById(R.id.deletefav);
        }
    }

}
