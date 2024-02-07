package com.example.collegeschedulerapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseDetails implements Parcelable {
    private String name;
    private String instructor;
    private String time;
    boolean[] dates;

    public CourseDetails(Parcel in) {
        name = in.readString();
        instructor = in.readString();
        time = in.readString();
        dates = in.createBooleanArray();
    }

    public static final Creator<CourseDetails> CREATOR = new Creator<CourseDetails>() {
        @Override
        public CourseDetails createFromParcel(Parcel in) {
            return new CourseDetails(in);
        }

        @Override
        public CourseDetails[] newArray(int size) {
            return new CourseDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(instructor);
        dest.writeString(time);
        dest.writeBooleanArray(dates);
    }

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
