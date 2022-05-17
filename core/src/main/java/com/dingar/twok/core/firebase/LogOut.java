package com.dingar.twok.core.firebase;

import com.google.firebase.auth.FirebaseAuth;

public class LogOut {
    //Singleton
    private LogOut(){}
    private static LogOut instance;
    public  static LogOut getInstance(){
        if (instance == null)
            instance = new LogOut();
        return instance;
    }

    public void logoutUser(){
        FirebaseAuth.getInstance().signOut();
    }
}
