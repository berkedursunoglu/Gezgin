package com.example.gezgin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gezgin.databinding.ActivityAccountEditBinding;
import com.example.gezgin.retrofit.UserDAOInterface;

public class AccountEdit extends AppCompatActivity {
    private ActivityAccountEditBinding design;
    private UserDAOInterface DIF;
    private AccountViewModel viewModel;
    private Shared shared = new Shared(AccountEdit.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design = DataBindingUtil.setContentView(AccountEdit.this, R.layout.activity_account_edit);
        design.setAccountEdit(this);
        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    public void changedPassword(){
        if ( design.changepass.getText().toString().equals(design.changepass1.getText().toString())){
            viewModel.passwordChange(shared.getAccount_idpref()
                    , design.currentpass.getText().toString()
                    , design.changepass.getText().toString()
                    , shared.getUsername()
                    , AccountEdit.this);
        } else{
            Toast.makeText(AccountEdit.this
                    , "Yeni şifreler birbiriyle uyuşmuyor"
                    , Toast.LENGTH_SHORT).show();
        }
        design.currentpass.setText("");
        design.changepass.setText("");
        design.changepass1.setText("");

    }

    public void changedUsername(){
        if (design.usernameedittext.getText().toString().trim().isEmpty()) {
            Toast.makeText(AccountEdit.this, "Boş bırakılamaz.", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.usernameControl(design.usernameedittext.getText().toString().trim(), AccountEdit.this, shared.getAccount_idpref());
            design.usernameedittext.setText("");
        }
    }
}