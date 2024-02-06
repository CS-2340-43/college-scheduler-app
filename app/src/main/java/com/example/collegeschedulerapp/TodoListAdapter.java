package com.example.collegeschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoListAdapter extends BaseAdapter {
    private TodoListFragment fragment;
    private Context context;
    private ArrayList<TodoList> todoLists;

    public TodoListAdapter(TodoListFragment f, Context c, ArrayList<TodoList> cl) {
        fragment = f;
        context = c;
        todoLists = cl;
    }

    @Override
    public int getCount() {
        return todoLists.size();
    }

    @Override
    public TodoList getItem(int position) {
        return todoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateTodo(int position, TodoList updatedTodo) {
        todoLists.set(position, updatedTodo);
        notifyDataSetChanged();
    }

    public void deleteTodo(int position) {
        todoLists.remove(getItem(position));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_course_detail, parent, false);
        }

        // Get the current course
        TodoList currentTodo = todoLists.get(position);

        // Bind data to views
        TextView todoTextView = convertView.findViewById(R.id.todo_list_item);
        TextView todoDetailsTextView = convertView.findViewById(R.id.todo_details_list_item);
        TextView todoDateView = convertView.findViewById(R.id.todo_date_list_item);

        todoTextView.setText(currentTodo.getTodo());
        todoDetailsTextView.setText(currentTodo.getDetails());
        todoDateView.setText(currentTodo.getDate());

        // Add delete button listener
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(v -> {
            deleteTodo(position);
        });

        // add code for editButton listener triggering the open form

        Button editButton = convertView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            fragment.handleEditSelect(position);
        });

        return convertView;
    }
}
