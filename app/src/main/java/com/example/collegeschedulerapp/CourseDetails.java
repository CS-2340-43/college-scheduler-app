package com.example.collegeschedulerapp;

public class CourseDetails {
    private String name;
    private String instructor;
    private String time;
    boolean[] dates;

    public CourseDetails(String cn, String ci, String ct, boolean[] cd) {
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

    /**
     * Builds the displayed course dates string for list_item_course_detail
     *
     * @param courseDates an array of selected dates
     * @return a string to display with chosen dates
     */
    private String buildCourseDatesString(boolean[] courseDates) {
        String s = "";

        if (courseDates[0]) s += "M ";
        if (courseDates[1]) s += "TU ";
        if (courseDates[2]) s += "W ";
        if (courseDates[3]) s += "TH ";
        if (courseDates[4]) s += "F ";

        return s.substring(0, s.length() - 1);
    }

    public String getDates() {
        return buildCourseDatesString(dates);
    }

    public void setDates(boolean[] dates) {
        this.dates = dates;
    }

    public boolean[] getDatesArray() {
        return dates;
    }
}
