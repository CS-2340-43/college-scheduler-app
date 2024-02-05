package com.example.collegeschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    private TodoFragment fragment;
    private Context context;
    private ArrayList<Todo> todoList;

    public TodoAdapter(TodoFragment f, Context c, ArrayList<Todo> cl) {
        fragment = f;
        context = c;
        todoList = cl;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Todo getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateCourse(int position, Todo updatedTodo) {
        todoList.set(position, updatedTodo);
        notifyDataSetChanged();
    }

    public void deleteCourse(int position) {
        todoList.remove(getItem(position));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_todo, parent, false);
        }

        // Get the current course
        Todo todo = todoList.get(position);

        // Bind data to views
        TextView todoTitle = convertView.findViewById(R.id.todo_title_list_item);
        TextView todoDescription = convertView.findViewById(R.id.todo_description_list_item);
        TextView todoDueAt = convertView.findViewById(R.id.todo_due_at_list_item);

        todoTitle.setText(todo.getName() + " - " + todo.getClassName());
        todoDescription.setText(todo.getDescription());
        todoDueAt.setText("Due at: " + todo.getTimeDue() + " on: " + todo.getDateDue());

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
