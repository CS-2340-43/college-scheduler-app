package com.example.collegeschedulerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * Use the {@link ExamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamFragment extends Fragment {
    private final String KEY = "exams";

    private ArrayList<Exam> exams = new ArrayList<>();
    private ListView listView;
    private ExamAdapter adapter;

    private TextView formHeader;
    private EditText examNameField;
    private EditText examDateField;
    private EditText examLocationField;

    private int editingIndex = -1; // Keep track of what item we are editing

    public ExamFragment() {}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, exams);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            ArrayList<Exam> prevExams = savedInstanceState.getParcelableArrayList(KEY);

            if (prevExams != null) {
                exams.addAll(prevExams);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void clearForm() {
        formHeader.setText("Add Exam");
        examNameField.setText("");
        examDateField.setText("");
        examLocationField.setText("");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Exam.
     */
    public static ExamFragment newInstance() {
        return new ExamFragment();
    }

    /**
     * Validates the form inputs
     *
     * @param en the todo name
     * @param ed the todo course
     * @param el the todo description
     * @return if the form fields are valid
     */
    private boolean validation(String en, String ed, String el) {
        boolean valid = true;

        if (en.length() == 0) {
            valid = false;
            // toast for name
        }

        if (ed.length() == 0) {
            valid = false;
            // toast for class
        }

        if (el.length() == 0) {
            valid = false;
            // toast for description
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
        String examName = examNameField.getText().toString();
        String examDate = examDateField.getText().toString();
        String examLocation = examLocationField.getText().toString();

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(examName, examDate, examLocation);

        // Exit if fields are invalid
        if (!isValid) return;

        Exam exam = new Exam(examName, examDate, examLocation);

        if (editingIndex < 0) {
            exams.add(exam);
        } else {
            exams.set(editingIndex, exam);
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
        formHeader.setText("Add Exam");
    };

    /**
     * Changes the add form to an edit form
     *
     * @param position the index of the element to load into the edit form
     */
    public void handleEditSelect(int position) {
        Exam exam = exams.get(position);

        // Set fields with selected courses data
        examNameField.setText(exam.getName());
        examDateField.setText(exam.getDate());
        examLocationField.setText(exam.getLocation());

        formHeader.setText("Edit Exam");
        editingIndex = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        // Set view global variables
        formHeader = view.findViewById(R.id.exam_form_header);
        examNameField = view.findViewById(R.id.exam_name);
        examDateField = view.findViewById(R.id.exam_date);
        examLocationField = view.findViewById(R.id.exam_location);

        // Initialize adapter to monitor changes to data
        if (adapter == null) { // TODO make new adapter
            adapter = new ExamAdapter(this, requireContext(), exams);
            listView = view.findViewById(R.id.exam_list);
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