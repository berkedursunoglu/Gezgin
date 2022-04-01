package com.example.gezgin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gezgin.databinding.ActivityProfileBinding;
import com.example.gezgin.userregister.UserPage;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding design;
    private ProfileViewModel profileViewModel;
    private int id;
    private Shared shared = new Shared(Profile.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design = DataBindingUtil.setContentView(Profile.this, R.layout.activity_profile);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        design.setProfile(this);
        design.userName.setText(shared.getUsername());
    }

    public int getId() {
        return shared.getAccount_idpref();
    }

    public void logOut() {
        shared.setRegister(0);
        startActivity(new Intent(Profile.this, UserPage.class));
        finish();
    }

    public void SnackBar(View view,int id){
        Snackbar snackbar = Snackbar.make(view, "Tüm verileriniz silinecektir kabul ediyor musunuz?", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setAction("Evet", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileViewModel.deleteacc(id);
                Toast.makeText(Profile.this, "Hesap Başarıyla Silindi",Toast.LENGTH_SHORT).show();
                shared.setRegister(0);
                startActivity(new Intent(Profile.this, UserPage.class));
                finish();
            }
        });
        snackbar.show();
    }

    public void intent(){
        startActivity(new Intent(Profile.this,AccountEdit.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Profile.this,MainPage.class));
        finish();
        super.onBackPressed();
    }
}