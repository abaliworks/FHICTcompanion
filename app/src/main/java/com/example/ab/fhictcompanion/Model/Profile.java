package com.example.ab.fhictcompanion.Model;

import android.graphics.Bitmap;

public class Profile {
    private Bitmap photo;
    private String displayName, fhictID, studentID, classTitle, department, mail;

    public Profile(String fhictID, String studentID) {
        this.fhictID = fhictID;
        this.studentID = studentID;
    }

    public Profile(Bitmap photo, String displayName, String fhictID, String studentID, String classTitle, String department, String mail) {
        this.photo = photo;
        this.displayName = displayName;
        this.fhictID = fhictID;
        this.studentID = studentID;
        this.classTitle = classTitle;
        this.department = department;
        this.mail = mail;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFhictID() {
        return fhictID;
    }

    public void setFhictID(String fhictID) {
        this.fhictID = fhictID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
