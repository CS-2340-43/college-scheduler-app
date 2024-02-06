package com.example.collegeschedulerapp;

public class Exam {
    private String name;
    private String date;
    private String location;

    public Exam(String cn, String ci, String ct) {
        name = cn;
        date = ci;
        location = ct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
