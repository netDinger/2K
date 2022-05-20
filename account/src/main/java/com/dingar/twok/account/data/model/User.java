package com.dingar.twok.account.data.model;

import com.google.firebase.database.PropertyName;

public class User {

    @PropertyName("name")
    String userName;
    @PropertyName("phone")
    String phoneNumber;
    String uid;

    public User(String userName, String phoneNumber, String uid) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.uid = uid;
    }

    @PropertyName("name")
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

    @PropertyName("phone")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
