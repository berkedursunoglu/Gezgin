package com.example.gezgin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.gezgin.databinding.ActivityFavoritePageBinding;

import java.util.ArrayList;

public class FavoritePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoritePageBinding design = DataBindingUtil.setContentView(FavoritePage.this,R.layout.activity_favorite_page);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("deneme1");
        arrayList.add("deneme2");
        arrayList.add("deneme3");
        arrayList.add("deneme4");
        RvAdapterFavorite rvAdapterFavorite = new RvAdapterFavorite(arrayList);
        design.rvfavorite.setLayoutManager(new LinearLayoutManager(this));
        design.rvfavorite.setAdapter(rvAdapterFavorite);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FavoritePage.this,MainPage.class));
        finish();
    }
}