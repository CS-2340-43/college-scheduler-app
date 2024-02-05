package com.example.collegeschedulerapp;

public class Todo {
    private String name;
    private String className;
    private String description;
    private String timeDue;
    private String dateDue;

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
