package com.example.collegeschedulerapp;

public class CourseDetails {
    private String name;
    private String instructor;
    private String time;
    private String dates;

    public CourseDetails(String cn, String ci, String ct, String cd) {
        name = cn;
        instructor = ci;
        time = ct;
        dates = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
