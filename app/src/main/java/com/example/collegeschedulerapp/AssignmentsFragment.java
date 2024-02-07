package com.example.collegeschedulerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssignmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentsFragment extends Fragment {
    private final String KEY = "assignments";

    private ArrayList<Assignments> assignments = new ArrayList<>();
    private ListView listView;
    private AssignmentsAdapter adapter;

    private TextView formHeader;
    private EditText assignmentsNameField;
    private EditText assignmentsClassField;
    private EditText assignmentsDueDateField;

    private int editingIndex = -1; // Keep track of what item we are editing

    public AssignmentsFragment() {}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, assignments);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            ArrayList<Assignments> prevAssignments = savedInstanceState.getParcelableArrayList(KEY);

            if (prevAssignments != null) {
                assignments.addAll(prevAssignments);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void clearForm() {
        formHeader.setText("Add Assignment");
        assignmentsNameField.setText("");
        assignmentsClassField.setText("");
        assignmentsDueDateField.setText("");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClassDetails.
     */
    public static AssignmentsFragment newInstance() {
        return new AssignmentsFragment();
    }

    /**
     * Validates the form inputs
     *
     * @param tn the todo name
     * @param tc the todo course
     * @param tdd the todo date due
     * @return if the form fields are valid
     */
    private boolean validation(String tn, String tc, String tdd) {
        boolean valid = true;

        if (tn.length() == 0) {
            valid = false;
        }

        if (tc.length() == 0) {
            valid = false;
        }

        if (tdd.length() == 0) {
            valid = false;
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
        String assignmentName = assignmentsNameField.getText().toString();
        String assignmentClass = assignmentsClassField.getText().toString();
        String assignmentDueDate = assignmentsDueDateField.getText().toString();

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(assignmentName, assignmentClass, assignmentDueDate);

        // Exit if fields are invalid
        if (!isValid) return;

        Assignments assignment = new Assignments(assignmentName, assignmentClass, assignmentDueDate);

        if (editingIndex < 0) {
            assignments.add(assignment);
        } else {
            assignments.set(editingIndex, assignment);
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
        formHeader.setText("Add Assignment");
    };

    /**
     * Changes the add form to an edit form
     *
     * @param position the index of the element to load into the edit form
     */
    public void handleEditSelect(int position) {
        Assignments assignment = assignments.get(position);

        // Set fields with selected courses data
        assignmentsNameField.setText(assignment.getName());
        assignmentsClassField.setText(assignment.getClassName());
        assignmentsDueDateField.setText(assignment.getDueDate());
        formHeader.setText("Edit Assignments");
        editingIndex = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);

        // Set view global variables
        formHeader = view.findViewById(R.id.assignment_form_header);
        assignmentsNameField = view.findViewById(R.id.assignment_name);
        assignmentsClassField = view.findViewById(R.id.assignment_course);
        assignmentsDueDateField = view.findViewById(R.id.assignment_date_due);

        // Initialize adapter to monitor changes to data
        if (adapter == null) { // TODO make new adapter
            adapter = new AssignmentsAdapter(this, requireContext(), assignments);
            listView = view.findViewById(R.id.assignments_list);
            listView.setAdapter(adapter);
        }

        // Initialize click listener for submit
        view.findViewById(R.id.submit).setOnClickListener(handleSubmit);

        // Initialize click listener for cancel
        view.findViewById(R.id.cancel).setOnClickListener(handleCancel);

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}