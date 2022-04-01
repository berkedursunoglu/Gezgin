package com.example.gezgin.retrofit;


import com.example.gezgin.retrofit.UserResponse.CRUDResponse;
import com.example.gezgin.retrofit.UserResponse.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDAOInterface {


    @GET("gezginapp/all_users.php")
    Call<UsersResponse> all_user();

    @POST("gezginapp/insert_users.php")
    @FormUrlEncoded
    Call<CRUDResponse> user_add(@Field("user_name") String user_name, @Field("user_password") String user_password, @Field("user_email") String user_email);

    @POST("gezginapp/login_response.php")
    @FormUrlEncoded
    Call<UsersResponse> login_response(@Field("username") String username);

    @POST("gezginapp/username_check.php")
    @FormUrlEncoded
    Call<CRUDResponse> username_check(@Field("username") String username);

    @POST("gezginapp/email_check.php")
    @FormUrlEncoded
    Call<CRUDResponse> email_check(@Field("useremail") String useremail);

    @POST("gezginapp/delete_account.php")
    @FormUrlEncoded
    Call<CRUDResponse> delete_account(@Field("userid") int userid);

    @POST("gezginapp/username_change.php")
    @FormUrlEncoded
    Call<CRUDResponse> username_change(@Field("userid") int userid, @Field("username")String username);

    @POST("gezginapp/password_change.php")
    @FormUrlEncoded
    Call<CRUDResponse> password_change(@Field("userid") int userid, @Field("userpassword")String userpassword);

}
