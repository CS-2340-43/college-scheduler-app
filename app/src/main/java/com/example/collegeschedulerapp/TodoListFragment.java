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
 * Use the {@link CourseDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoListFragment extends Fragment {
    ArrayList<TodoList> todos = new ArrayList<>();
    ListView listView;
    TodoListAdapter adapter;

    TextView formHeader;
    EditText todoField;
    EditText detailsField;
    EditText dateField;

    private int editingIndex = -1; // Keep track of what item we are editing

    public TodoListFragment() {}

    private void clearForm() {
        todoField.setText("");
        detailsField.setText("");
        dateField.setText("");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClassDetails.
     */
    public static TodoListFragment newInstance() {
        return new TodoListFragment();
    }

    /**
     * Validates the form inputs
     *
     * @param cn the courses name
     * @param ci the course instructor
     * @param ct the course time
     * @return if the form fields are valid
     */
    private boolean validation(String cn, String ci, String ct) {
        boolean valid = true;

        if (cn.length() == 0) {
            valid = false;
            // toast for name
        }

        if (ci.length() == 0) {
            valid = false;
            // toast for instructor
        }

        if (ct.length() == 0) {
            valid = false;
            // toast for time
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
        String todoName = todoField.getText().toString();
        String todoDetails = detailsField.getText().toString();
        String todoDate = dateField.getText().toString();

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(todoName, todoDetails, todoDate);

        // Exit if fields are invalid
        if (!isValid) return;

        TodoList todo = new TodoList(todoName, todoDetails, todoDate);

        if (editingIndex < 0) {
            todos.add(todo);
        } else {
            todos.set(editingIndex, todo);
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
        TodoList todo = todos.get(position);

        // Set fields with selected courses data
        todoField.setText(todo.getTodo());
        detailsField.setText(todo.getDetails());
        dateField.setText(todo.getDate());

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
        View view = inflater.inflate(R.layout.fragment_class_details, container, false);

        // Set view global variables
//        formHeader = view.findViewById(R.id.todo_form_header);
//        todoField = view.findViewById(R.id.todo);
//        detailsField = view.findViewById(R.id.course_instructor);
//        dateField = view.findViewById(R.id.course_time);

        // Initialize adapter to monitor changes to data
        if (adapter == null) {
            //adapter = new CourseDetailsAdapter(this, requireContext(), todos);
            listView = view.findViewById(R.id.courses_list);
            listView.setAdapter(adapter);
        }

        // Initialize click listener for submit
        view.findViewById(R.id.submit).setOnClickListener(handleSubmit);

        // Initialize click listener for cancel
        view.findViewById(R.id.cancel).setOnClickListener(handleCancel);

        return view;

    }
}