package com.example.collegeschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentsAdapter extends BaseAdapter {
    private AssignmentsFragment fragment;
    private Context context;
    private ArrayList<Assignments> assignmentsList;

    public AssignmentsAdapter(AssignmentsFragment f, Context c, ArrayList<Assignments> cl) {
        fragment = f;
        context = c;
        assignmentsList = cl;
    }

    @Override
    public int getCount() {
        return assignmentsList.size();
    }

    @Override
    public Assignments getItem(int position) {
        return assignmentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateCourse(int position, Assignments updatedAssignments) {
        assignmentsList.set(position, updatedAssignments);
        notifyDataSetChanged();
    }

    public void deleteCourse(int position) {
        assignmentsList.remove(getItem(position));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_assignments, parent, false);
        }

        // Get the current course
        Assignments assignment = assignmentsList.get(position);

        // Bind data to views
        TextView assignmentTitle = convertView.findViewById(R.id.assignment_title_list_item);
        TextView assignmentCourse = convertView.findViewById(R.id.assignment_class_list_item);
        TextView assignmentDueAt = convertView.findViewById(R.id.assignment_due_date_list_item);

        assignmentTitle.setText(assignment.getName());
        assignmentCourse.setText(assignment.getClassName());
        assignmentDueAt.setText("Due at: " + assignment.getDueDate());

        // Add delete button listener
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(v -> {
            deleteCourse(position);
        });

        // Add edit button listener
        Button editButton = convertView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            fragment.handleEditSelect(position);
        });

        // Return new view
        return convertView;
    }
}
