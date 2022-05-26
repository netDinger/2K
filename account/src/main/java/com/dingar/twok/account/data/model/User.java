package com.dingar.twok.account.data.model;

import com.google.firebase.database.PropertyName;

public class User {

    @PropertyName("name")
    String name;
    @PropertyName("phone")
    String phone;
    String uid;

    public User(){}
    public User(String name, String phone, String uid) {
        this.name = name;
        this.phone = phone;
        this.uid = uid;
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
}
