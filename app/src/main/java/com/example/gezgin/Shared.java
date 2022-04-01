package com.example.gezgin;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;
    private int register, account_idpref, skipinfo;
    private String username;

    public Shared(Context mContext) {
        this.mContext = mContext;
    }

    public String getUsername() {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return sharedPreferences.getString("username", "unkown");
    }

    public int getAccount_idpref() {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return sharedPreferences.getInt("account_id", 0);
    }

    public int getRegister() {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return sharedPreferences.getInt("register", 0);
    }

    public int getSkipinfo() {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return sharedPreferences.getInt("skipinfo", 0);
    }

    public void setRegister(int register) {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("register",register);
        editor.commit();
        this.register = register;
    }

    public void setAccount_idpref(int account_idpref) {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("account_id",account_idpref);
        editor.commit();
        this.account_idpref = account_idpref;
    }

    public void setSkipinfo(int skipinfo) {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("skipinfo",skipinfo);
        editor.commit();
        this.skipinfo = skipinfo;
    }

    public void setUsername(String username) {
        sharedPreferences = mContext.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();
        this.username = username;
    }
}
