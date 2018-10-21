package com.example.ab.fhictcompanion.Model;

public class Schedule {
    private String subject, subjectName, teacher, room, startDate, startTime, endTime;
    private int colorLine;

    public Schedule(String subject, String subjectName, String teacher, String room, String startDate, String startTime, String endTime, int colorLine) {
        this.subject = subject;
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.room = room;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.colorLine = colorLine;
    }

    public Schedule(String subject, String subjectName, String teacher, String room, String startDate, String startTime, String endTime) {
        this.subject = subject;
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.room = room;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getColorLine() {
        return colorLine;
    }

    public void setColorLine(int colorLine) {
        this.colorLine = colorLine;
    }
}

