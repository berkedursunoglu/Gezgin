package com.example.gezgin.MapPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gezgin.FavoritePage;
import com.example.gezgin.Profile;
import com.example.gezgin.R;
import com.example.gezgin.databinding.ActivityMainPageBinding;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainPageBinding design = DataBindingUtil.setContentView(MainPage.this, R.layout.activity_main_page);
        setSupportActionBar(design.mainpagetoolbar);
        fragments();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainpagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(new Intent(MainPage.this, Profile.class));
                finish();
                break;
            case R.id.favorite:
                startActivity(new Intent(MainPage.this, FavoritePage.class));
                finish();
                break;
        }
        return true;
    }

    public void fragments() {
        Fragment fragmentmap = new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutmap, fragmentmap).commit();
        Fragment fragmentplace = new PlacesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, fragmentplace).commit();
    }
}
