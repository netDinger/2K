package com.dingar.twok.account.data.model;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.PropertyName;

public class User {

    @PropertyName("name")
    String name;
    @PropertyName("phone")
    String phone;
    String uid;
    @PropertyName(Static_Config.VERIFIED)
    boolean verified;

    public User(){}

    public User(String name, String phone, String uid, boolean verified) {
        this.name = name;
        this.phone = phone;
        this.uid = uid;
        this.verified = verified;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @PropertyName("phone")
    public String getPhone() {
        return phone;
    }

    @PropertyName("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
