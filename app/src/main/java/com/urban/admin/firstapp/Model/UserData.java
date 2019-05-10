package com.urban.admin.firstapp.Model;

public class UserData {
String id;
    String name;


    public UserData(String id, String name, long mobile, String email) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    long mobile;
    String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
