package com.example.gezgin.infopage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.gezgin.R;
import com.example.gezgin.Shared;
import com.example.gezgin.userregister.UserPage;

public class MainActivity extends AppCompatActivity {

    private Shared shared = new Shared(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionCheck();
        if (shared.getSkipinfo() == 1){
            startActivity(new Intent(MainActivity.this,UserPage.class));
            finish();
        }shared.setSkipinfo(1);
    }

    public void permissionCheck(){
        int request = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (request != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
    }
}