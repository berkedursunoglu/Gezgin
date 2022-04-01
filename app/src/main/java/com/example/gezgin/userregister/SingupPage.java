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
import com.example.gezgin.databinding.FragmentSingupPageBinding;

public class SingupPage extends Fragment {
    private FragmentSingupPageBinding design;
    private Shared shared;
    private AccountViewModel accountViewModel;
    private String inputpass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_singup_page, container, false);
        design.setSingup(this);
        shared = new Shared(getActivity().getApplicationContext());

        return design.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        super.onCreate(savedInstanceState);
    }


    public void login_check(String name,String inputpass){
        accountViewModel.login_check(name,inputpass,getActivity().getApplicationContext());
    }
}
