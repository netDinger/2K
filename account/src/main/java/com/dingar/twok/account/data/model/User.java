package com.dingar.twok.account.data.model;

public class User {
    String userName;
    String uid;

    public User(String userName, String uid) {
        this.userName = userName;
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
