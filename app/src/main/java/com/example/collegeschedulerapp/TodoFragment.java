package com.example.collegeschedulerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {
    private ArrayList<Todo> todos = new ArrayList<>();
    private ListView listView;
    private TodoAdapter adapter;

    private TextView formHeader;
    private EditText todoNameField;
    private EditText todoClassField;
    private EditText todoDescriptionField;
    private EditText todoTimeDueField;
    private EditText todoDateDueField;

    private int editingIndex = -1; // Keep track of what item we are editing

    public TodoFragment() {}

    private void clearForm() {
        formHeader.setText("Add Todo");
        todoNameField.setText("");
        todoClassField.setText("");
        todoDescriptionField.setText("");
        todoTimeDueField.setText("");
        todoDateDueField.setText("");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClassDetails.
     */
    public static TodoFragment newInstance() {
        return new TodoFragment();
    }

    /**
     * Validates the form inputs
     *
     * @param tn the todo name
     * @param tc the todo course
     * @param td the todo description
     * @param ttd the todo time due
     * @param tdd the todo date due
     * @return if the form fields are valid
     */
    private boolean validation(String tn, String tc, String td, String ttd, String tdd) {
        boolean valid = true;

        if (tn.length() == 0) {
            valid = false;
            // toast for name
        }

        if (tc.length() == 0) {
            valid = false;
            // toast for class
        }

        if (td.length() == 0) {
            valid = false;
            // toast for description
        }

        if (ttd.length() == 0) {
            valid = false;
            // toast for time due
        }

        if (tdd.length() == 0) {
            valid = false;
            // toast for date due
        }

        return valid;
    }

    /**
     * Handle form submission
     *
     * @param view The select buttons view
     */
    View.OnClickListener handleSubmit = (View view) -> {
        // Gather inputs
        String todoName = todoNameField.getText().toString();
        String todoClass = todoClassField.getText().toString();
        String todoDescription = todoDescriptionField.getText().toString();
        String todoTimeDue = todoTimeDueField.getText().toString();
        String todoDateDue = todoDateDueField.getText().toString();

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(todoName, todoClass, todoDescription, todoTimeDue, todoDateDue);

        // Exit if fields are invalid
        if (!isValid) return;

        Todo todo = new Todo(todoName, todoClass, todoDescription, todoTimeDue, todoDateDue);

        if (editingIndex < 0) {
            todos.add(todo);
        } else {
            todos.set(editingIndex, todo);
            editingIndex = -1;
        }

        // Clear the form
        clearForm();

        // Notify data change
        adapter.notifyDataSetChanged();
    };

    View.OnClickListener handleCancel = (View view) -> {
        clearForm();
        editingIndex = -1;
        formHeader.setText("Add Todo");
    };

    /**
     * Changes the add form to an edit form
     *
     * @param position the index of the element to load into the edit form
     */
    public void handleEditSelect(int position) {
        Todo todo = todos.get(position);

        // Set fields with selected courses data
        todoNameField.setText(todo.getName());
        todoClassField.setText(todo.getClassName());
        todoDescriptionField.setText(todo.getDescription());
        todoTimeDueField.setText(todo.getTimeDue());
        todoDateDueField.setText(todo.getDateDue());

        formHeader.setText("Edit Todo");
        editingIndex = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        // Set view global variables
        formHeader = view.findViewById(R.id.todo_form_header);
        todoNameField = view.findViewById(R.id.todo_name);
        todoClassField = view.findViewById(R.id.todo_course);
        todoDescriptionField = view.findViewById(R.id.todo_description);
        todoTimeDueField = view.findViewById(R.id.todo_time_due);
        todoDateDueField = view.findViewById(R.id.todo_date_due);

        // Initialize adapter to monitor changes to data
        if (adapter == null) { // TODO make new adapter
            adapter = new TodoAdapter(this, requireContext(), todos);
            listView = view.findViewById(R.id.todos_list);
            listView.setAdapter(adapter);
        }

        // Initialize click listener for submit
        view.findViewById(R.id.submit).setOnClickListener(handleSubmit);

        // Initialize click listener for cancel
        view.findViewById(R.id.cancel).setOnClickListener(handleCancel);

        return view;

    }
}