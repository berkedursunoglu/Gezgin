package com.example.gezgin.userregister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gezgin.AccountViewModel;
import com.example.gezgin.R;
import com.example.gezgin.Shared;
import com.example.gezgin.databinding.FragmentSinginPageBinding;

public class SigninPage extends Fragment {
    private FragmentSinginPageBinding design;
    private Shared shared;
    private AccountViewModel accountViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_singin_page, container, false);
        design.setSignin(this);
        shared = new Shared(getActivity().getApplicationContext());
        return design.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    public void check(){
        accountViewModel.check(design.username.getText().toString()
                , design.password.getText().toString()
                , design.email.getText().toString()
                , getActivity().getApplicationContext());

        design.username.setText("");
        design.password.setText("");
        design.email.setText("");
    }
}

