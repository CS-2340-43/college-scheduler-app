package com.example.collegeschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter{
    private ExamFragment fragment;
    private Context context;
    private ArrayList<Exam> examList;

    public ExamAdapter(ExamFragment f, Context c, ArrayList<Exam> cl) {
        fragment = f;
        context = c;
        examList = cl;
    }

    @Override
    public int getCount() {
        return examList.size();
    }

    @Override
    public Exam getItem(int position) {
        return examList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateExam(int position, Exam updatedExam) {
        examList.set(position, updatedExam);
        notifyDataSetChanged();
    }

    public void deleteExam(int position) {
        examList.remove(getItem(position));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_course_detail, parent, false);
        }

        // Get the current course
        Exam currentExam = examList.get(position);

        // Bind data to views
        TextView examNameTextView = convertView.findViewById(R.id.exam_name_list_item);
        TextView examDateTextView = convertView.findViewById(R.id.exam_date_list_item);
        TextView examLocationView = convertView.findViewById(R.id.exam_location_list_item);

        examNameTextView.setText(currentExam.getName());
        examDateTextView.setText(currentExam.getDate());
        examLocationView.setText(currentExam.getLocation());

        // Add delete button listener
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(v -> {
            deleteExam(position);
        });

        // add code for editButton listener triggering the open form

        Button editButton = convertView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            fragment.handleEditSelect(position);
        });

        return convertView;
    }
}
