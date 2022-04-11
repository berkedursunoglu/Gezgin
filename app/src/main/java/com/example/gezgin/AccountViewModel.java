package com.example.gezgin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.gezgin.MapPages.MainPage;
import com.example.gezgin.retrofit.ApiUtils;
import com.example.gezgin.retrofit.UserResponse.CRUDResponse;
import com.example.gezgin.retrofit.UserResponse.User;
import com.example.gezgin.retrofit.UserDAOInterface;
import com.example.gezgin.retrofit.UserResponse.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountViewModel extends ViewModel {

    public void passwordChange(int userid, String userpassword, String userpasschange, String username, Context context) {
        UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
        DIF.login_response(username).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                List<User> user = response.body().getUsers();
                String responsepass = user.get(0).getUserPassword();
                if (userpassword.equals(responsepass)) {
                    DIF.password_change(userid, userpasschange).enqueue(new Callback<CRUDResponse>() {
                        @Override
                        public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                            Toast.makeText(context, "Şifre başarıyla değiştirildi.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CRUDResponse> call, Throwable t) {
                        }
                    });
                } else {
                    Toast.makeText(context, "Mevcut şifreniz yanlış", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }

    public void usernameChange(int userid, String username, Context context) {
        UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
        DIF.username_change(userid, username).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                Toast.makeText(context, "Kullanıcı adı değişti", Toast.LENGTH_SHORT).show();
                Shared shared = new Shared(context);
                shared.setUsername(username);
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {
            }
        });
    }

    public void usernameControl(String name, Context mContext, int userid) {
        UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
        DIF.username_check(name).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                if (response.body().getSuccess() == 1) {
                    Toast.makeText(mContext, "Kullanıcı adı kullanımda", Toast.LENGTH_SHORT).show();
                } else {
                    usernameChange(userid, name, mContext);
                }
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {
            }
        });
    }

    public void check(String name, String pass, String mail, Context mContext) {
        Shared shared = new Shared(mContext);
        if (name.isEmpty() || pass.isEmpty() || mail.isEmpty()) {
            Toast.makeText(mContext, "Alanları Boş Bırakamazsınız", Toast.LENGTH_SHORT).show();
        } else {
            UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
            DIF.username_check(name).enqueue(new Callback<CRUDResponse>() {
                @Override
                public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                    int nameresponse = response.body().getSuccess();
                    if (nameresponse == 1) {
                        Toast.makeText(mContext, "Bu kullanıcı adı kullanımda", Toast.LENGTH_SHORT).show();
                    } else {
                        DIF.email_check(mail).enqueue(new Callback<CRUDResponse>() {
                            @Override
                            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                                int mailcheck = response.body().getSuccess();
                                if (mailcheck == 1) {
                                    Toast.makeText(mContext, "Bu e-mail kullanımda", Toast.LENGTH_SHORT).show();
                                } else {
                                    DIF.user_add(name, pass, mail).enqueue(new Callback<CRUDResponse>() {
                                        @Override
                                        public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                                            Log.e("kayıt basarili", response.toString());
                                        }

                                        @Override
                                        public void onFailure(Call<CRUDResponse> call, Throwable t) {

                                        }
                                    });
                                    Toast.makeText(mContext, "Kayıt olma başarılı", Toast.LENGTH_SHORT).show();
                                    shared.setUsername(name);
                                    shared.setRegister(1);
                                    Intent intent = new Intent(mContext, MainPage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent);
                                    ((Activity) mContext).finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<CRUDResponse> call, Throwable t) {
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<CRUDResponse> call, Throwable t) {

                }
            });

        }
    }

    public void login_check(String name, String inputpass, Context mContext) {
        Shared shared = new Shared(mContext);
        UserDAOInterface DIF = ApiUtils.getuserDaoInterface();
        DIF.login_response(name).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                List<User> users = response.body().getUsers();
                int account_id = Integer.parseInt(users.get(0).getUserId());
                String responsepass = users.get(0).getUserPassword();
                if (inputpass.equals(responsepass)) {
                    shared.setUsername(name);
                    shared.setAccount_idpref(account_id);
                    shared.setRegister(1);
                    Toast.makeText(mContext, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MainPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                } else {
                    Toast.makeText(mContext, "Kullanıcı adı veya şifre hatalı.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(mContext, "Böyle bir kullanıcı yok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

