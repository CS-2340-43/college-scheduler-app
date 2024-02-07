package com.example.collegeschedulerapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Exam implements Parcelable {
    private String name;
    private String date;
    private String location;


    public Exam(Parcel in) {
        name = in.readString();
        date = in.readString();
        location = in.readString();
    }

    public static final Parcelable.Creator<Exam> CREATOR = new Parcelable.Creator<Exam>() {
        @Override
        public Exam createFromParcel(Parcel in) {
            return new Exam(in);
        }

        @Override
        public Exam[] newArray(int size) {
            return new Exam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(location);
    }

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
