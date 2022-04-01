package com.example.gezgin.infopage;

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
import androidx.navigation.Navigation;

import com.example.gezgin.R;
import com.example.gezgin.databinding.FragmentInfopageSecondBinding;

public class InfoPageSecond extends Fragment {
    private FragmentInfopageSecondBinding design;
    private Animation animation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_infopage_second,container,false);


        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.button_alpha_anim );
        design.imageView2.setAnimation(animation);
        design.fragmentsecondtw.setAnimation(animation);
        design.fragmentsecondfb.setAnimation(animation);

        design.fragmentsecondfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.secondtothird);
            }
        });


        return design.getRoot();
    }
}
