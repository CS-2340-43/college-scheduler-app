package com.example.collegeschedulerapp;

public class TodoList {
    private String todo;
    private String details;
    private String date;

    public TodoList(String cn, String ci, String ct) {
        todo = cn;
        details = ci;
        date = ct;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String instructor) { this.details = details; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
