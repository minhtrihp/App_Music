package com.example.appmusic.Database;

import android.content.pm.SigningInfo;

public class User {
    String UserName;
    String Password;

    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }
    public User() {
        this.UserName = UserName ;
        this.Password = Password ;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
