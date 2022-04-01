package com.example.gezgin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gezgin.databinding.ActivityMainPageBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainPageBinding design = DataBindingUtil.setContentView(MainPage.this, R.layout.activity_main_page);
        setSupportActionBar(design.mainpagetoolbar);
        placesFragment();
        Fragment fragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutmap, fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainpagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_profile) {
            startActivity(new Intent(MainPage.this, Profile.class));
            finish();
        }
        return true;
    }

    public void placesFragment() {
        Fragment fragment = new PlacesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, fragment).commit();
    }

}