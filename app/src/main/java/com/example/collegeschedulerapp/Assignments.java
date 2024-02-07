package com.example.collegeschedulerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Assignments implements Parcelable {
    private String name;
    private String className;
    private String dueDate;

    public Assignments(Parcel in) {
        name = in.readString();
        className = in.readString();
        dueDate = in.readString();
    }

    public static final Creator<Assignments> CREATOR = new Creator<Assignments>() {
        @Override
        public Assignments createFromParcel(Parcel in) {
            return new Assignments(in);
        }

        @Override
        public Assignments[] newArray(int size) {
            return new Assignments[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(className);
        dest.writeString(dueDate);
    }

    public Assignments(String name, String className, String dueDate) {
        this.name = name;
        this.className = className;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
