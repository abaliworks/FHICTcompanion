package com.example.ab.fhictcompanion.Model;

import android.graphics.Bitmap;

public class People {

    private String id;
    private String displayName;

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    private String mail;
    private String office;
    private String telephoneNumber;
    private Bitmap photo;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMail() {
        return mail;
    }

    public String getOffice() {
        return office;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public People(Bitmap photo, String id, String displayName, String mail, String office, String telephoneNumber) {
        this.photo = photo;
        this.id = id;
        this.displayName = displayName;
        this.mail = mail;
        this.office = office;
        this.telephoneNumber = telephoneNumber;
    }


}
