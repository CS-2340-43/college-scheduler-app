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
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailsFragment extends Fragment {
    private final String KEY = "course_details";

    ArrayList<CourseDetails> courses = new ArrayList<>();
    ListView listView;
    CourseDetailsAdapter adapter;

    TextView formHeader;
    EditText courseNameField;
    EditText courseInstructorField;
    EditText courseTimeField;
    CheckBox[] courseDatesField;

    private int editingIndex = -1; // Keep track of what item we are editing

    public CourseDetailsFragment() {}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("SAVE STATE, COURSE COUNT: ", String.valueOf(courses.size()));

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, courses);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            ArrayList<CourseDetails> prevCourses = savedInstanceState.getParcelableArrayList(KEY);

            if (prevCourses != null) {
                courses.addAll(prevCourses);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void clearForm() {
        formHeader.setText("Add Course Detail");
        courseNameField.setText("");
        courseInstructorField.setText("");
        courseTimeField.setText("");
        courseDatesField[0].setChecked(false);
        courseDatesField[1].setChecked(false);
        courseDatesField[2].setChecked(false);
        courseDatesField[3].setChecked(false);
        courseDatesField[4].setChecked(false);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClassDetails.
     */
    public static CourseDetailsFragment newInstance() {
        return new CourseDetailsFragment();
    }

    /**
     * Validates the form inputs
     *
     * @param cn the courses name
     * @param ci the course instructor
     * @param ct the course time
     * @param courseDates an array of selected course dates
     * @return if the form fields are valid
     */
    private boolean validation(String cn, String ci, String ct, boolean[] courseDates) {
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

        boolean courseDateChosen = false;
        for (boolean b : courseDates) {
            if (b) {
                courseDateChosen = true;
                break;
            }
        }

        if (!courseDateChosen) {
            valid = false;
            // toast for courseDate
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
        String courseName = courseNameField.getText().toString();
        String courseInstructor = courseInstructorField.getText().toString();
        String courseTime = courseTimeField.getText().toString();
        boolean[] courseDates = {
                courseDatesField[0].isChecked(),
                courseDatesField[1].isChecked(),
                courseDatesField[2].isChecked(),
                courseDatesField[3].isChecked(),
                courseDatesField[4].isChecked(),
        };

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(courseName, courseInstructor, courseTime, courseDates);

        // Exit if fields are invalid
        if (!isValid) return;

        CourseDetails course = new CourseDetails(courseName, courseInstructor, courseTime, courseDates);

        if (editingIndex < 0) {
            courses.add(course);
        } else {
            courses.set(editingIndex, course);
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
        formHeader.setText("Add Course Details");
    };

    /**
     * Changes the add form to an edit form
     *
     * @param position the index of the element to load into the edit form
     */
    public void handleEditSelect(int position) {
        CourseDetails course = courses.get(position);

        // Set fields with selected courses data
        courseNameField.setText(course.getName());
        courseInstructorField.setText(course.getInstructor());
        courseTimeField.setText(course.getTime());

        boolean[] dates = course.getDatesArray();
        courseDatesField[0].setChecked(dates[0]);
        courseDatesField[1].setChecked(dates[1]);
        courseDatesField[2].setChecked(dates[2]);
        courseDatesField[3].setChecked(dates[3]);
        courseDatesField[4].setChecked(dates[4]);

        formHeader.setText("Edit Course Details");
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
        formHeader = view.findViewById(R.id.course_form_header);
        courseNameField = view.findViewById(R.id.course_name);
        courseInstructorField = view.findViewById(R.id.course_instructor);
        courseTimeField = view.findViewById(R.id.course_time);

        CheckBox monday = view.findViewById(R.id.monday);
        CheckBox tuesday = view.findViewById(R.id.tuesday);
        CheckBox wednesday = view.findViewById(R.id.wednesday);
        CheckBox thursday = view.findViewById(R.id.thursday);
        CheckBox friday = view.findViewById(R.id.friday);

        courseDatesField = new CheckBox[]{monday, tuesday, wednesday, thursday, friday};

        // Initialize adapter to monitor changes to data
        if (adapter == null) {
            adapter = new CourseDetailsAdapter(this, requireContext(), courses);
            listView = view.findViewById(R.id.courses_list);
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