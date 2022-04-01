package com.example.gezgin.retrofit;

public class ApiUtils {

    public static String BASE_URL = "http://www.berkedr.online/";
    public static String BASE_URL_MAP = "https://maps.googleapis.com/";
    public static UserDAOInterface getuserDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(UserDAOInterface.class);
    }
    public static UserDAOInterface getmapDaoInterface(){
        return RetrofitClient.getClient(BASE_URL_MAP).create(UserDAOInterface.class);
    }
}
