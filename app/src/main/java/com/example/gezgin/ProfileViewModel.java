package com.example.gezgin;

import androidx.lifecycle.ViewModel;

import com.example.gezgin.retrofit.ApiUtils;
import com.example.gezgin.retrofit.UserResponse.CRUDResponse;
import com.example.gezgin.retrofit.UserDAOInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    public void deleteacc(int userid) {
        UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
        DIF.delete_account(userid).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });
    }

}
