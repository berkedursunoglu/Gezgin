package com.example.gezgin.infopage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gezgin.R;
import com.example.gezgin.databinding.FragmentInfopageFirstBinding;

public class InfoPageFirst extends Fragment {
    private FragmentInfopageFirstBinding design;
    private Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_infopage_first,container,false);
        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.button_alpha_anim);
        design.imageView.setAnimation(animation);
        design.fragmentfirstfb.setAnimation(animation);
        design.fragmentfirsttw.setAnimation(animation);
        design.fragmentfirstfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.firsttosecond);
            }
        });
        return design.getRoot();
    }
}