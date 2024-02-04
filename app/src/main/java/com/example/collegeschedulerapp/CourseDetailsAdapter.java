package com.example.collegeschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseDetailsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CourseDetails> courseList;

    public CourseDetailsAdapter(Context c, ArrayList<CourseDetails> cl) {
        context = c;
        courseList = cl;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public CourseDetails getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_course_detail, parent, false);
        }

        // Get the current course
        CourseDetails currentCourse = courseList.get(position);

        // Bind data to views
        TextView courseNameTextView = convertView.findViewById(R.id.course_name_list_item);
        TextView instructorTextView = convertView.findViewById(R.id.course_instructor_list_item);
        TextView timeTextView = convertView.findViewById(R.id.course_time_list_item);
        TextView dateTextView = convertView.findViewById(R.id.course_dates_list_item);

        courseNameTextView.setText(currentCourse.getName());
        instructorTextView.setText(currentCourse.getInstructor());
        timeTextView.setText(currentCourse.getTime());
        dateTextView.setText(currentCourse.getDates());

        // Add delete button listener

        Button deleteButton = convertView.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(v -> {
            courseList.remove(getItem(position));
            notifyDataSetChanged();
        });

        return convertView;
    }
}
