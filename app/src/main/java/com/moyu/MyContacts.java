package com.moyu;

import android.graphics.Bitmap;

public class MyContacts {
    private String name;
    private String phoneno;
    private String email;
    private Bitmap photo;

    MyContacts() {
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
