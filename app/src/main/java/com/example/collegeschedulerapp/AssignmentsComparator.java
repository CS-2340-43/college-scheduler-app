package com.example.collegeschedulerapp;

import java.util.Comparator;

public class AssignmentsComparator {

    public static Comparator<Assignments> sortByDueDateAscending() {
        return (assignment1, assignment2) -> {
            return assignment1.getDueDate().compareTo(assignment2.getDueDate());
        };
    }

    public static Comparator<Assignments> sortByClassNameAscending() {
        return (assignment1, assignment2) -> {
            return assignment1.getClassName().compareTo(assignment2.getClassName());
        };
    }
}

