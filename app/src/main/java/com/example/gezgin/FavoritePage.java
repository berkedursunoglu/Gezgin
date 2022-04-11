package com.example.gezgin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.gezgin.MapPages.MainPage;
import com.example.gezgin.databinding.ActivityFavoritePageBinding;
import com.example.gezgin.retrofit.ApiUtils;
import com.example.gezgin.retrofit.UserDAOInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePage extends AppCompatActivity {
    RvAdapterFavorite rvAdapterFavorite;
    ArrayList<Place> placeFavArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoritePageBinding design = DataBindingUtil.setContentView(FavoritePage.this, R.layout.activity_favorite_page);
        Shared shared = new Shared(this);
        UserDAOInterface dao = ApiUtils.getuserDaoInterface();
        int id = shared.getAccount_idpref();
        dao.getfav_places(id).enqueue(new Callback<PlacesFav>() {
            @Override
            public void onResponse(Call<PlacesFav> call, Response<PlacesFav> response) {
                placeFavArrayList = (ArrayList<Place>) response.body().getPlaces();

                rvAdapterFavorite = new RvAdapterFavorite(placeFavArrayList,getApplicationContext());
                design.rvfavorite.setHasFixedSize(true);
                design.rvfavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                design.rvfavorite.setAdapter(rvAdapterFavorite);
                rvAdapterFavorite.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PlacesFav> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FavoritePage.this, MainPage.class));
        finish();
    }
}