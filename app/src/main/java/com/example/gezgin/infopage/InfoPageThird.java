package com.example.gezgin.infopage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.gezgin.R;
import com.example.gezgin.Shared;
import com.example.gezgin.databinding.FragmentInfopageThirdBinding;
import com.example.gezgin.userregister.UserPage;

public class InfoPageThird extends Fragment {
    private FragmentInfopageThirdBinding design;
    private Animation animation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_infopage_third,container,false);
        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.button_alpha_anim);

        design.fragmentthirdtw.setAnimation(animation);
        design.registerbutton.setAnimation(animation);
        design.imageView3.setAnimation(animation);

        design.registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), UserPage.class));
                getActivity().finish();
            }
        });
        return design.getRoot();
    }
}
