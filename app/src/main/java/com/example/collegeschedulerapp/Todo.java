package com.example.collegeschedulerapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String name;
    private String className;
    private String description;
    private String timeDue;
    private String dateDue;

    public Todo(Parcel in) {
        name = in.readString();
        className = in.readString();
        description = in.readString();
        timeDue = in.readString();
        dateDue = in.readString();
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
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
        dest.writeString(description);
        dest.writeString(timeDue);
        dest.writeString(dateDue);
    }

    public Todo(String n, String c, String d, String td, String dd) {
        name = n;
        className = c;
        description = d;
        timeDue = td;
        dateDue = dd;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeDue() {
        return timeDue;
    }

    public void setTimeDue(String timeDue) {
        this.timeDue = timeDue;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
}
