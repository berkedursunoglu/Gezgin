package com.example.gezgin.userregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gezgin.MainPage;
import com.example.gezgin.R;
import com.example.gezgin.Shared;
import com.example.gezgin.databinding.ActivityUserPageBinding;

public class UserPage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private ActivityUserPageBinding design;
    private Shared shared = new Shared(UserPage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design = DataBindingUtil.setContentView(this, R.layout.activity_user_page);
        if (shared.getRegister() == 1){
            startActivity(new Intent(UserPage.this, MainPage.class));
            finish();
        }
        tablayout();
    }

    public void tablayout(){
        design.tabLayout.setupWithViewPager(design.userpageViewpager);
        PagerAdapter adapter = new com.example.gezgin.userregister.PagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ((com.example.gezgin.userregister.PagerAdapter) adapter).addFragment(new SigninPage(),"Kayıt Ol");
        ((com.example.gezgin.userregister.PagerAdapter) adapter).addFragment(new SingupPage(),"Giriş Yap");
        design.userpageViewpager.setAdapter(adapter);
    }

}