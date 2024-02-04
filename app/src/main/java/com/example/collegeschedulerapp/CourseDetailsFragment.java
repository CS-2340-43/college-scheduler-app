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
public class CourseDetailsFragment extends Fragment {
    ArrayList<CourseDetails> courses = new ArrayList<>();
    ListView listView;
    CourseDetailsAdapter adapter;
    EditText courseNameField;
    EditText courseInstructorField;
    EditText courseTimeField;
    CheckBox[] courseDatesField;

    public CourseDetailsFragment() {}

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
     * Builds the displayed course dates string for list_item_course_detail
     *
     * @param courseDates an array of selected dates
     * @return a string to display with chosen dates
     */
    private String buildCourseDatesString(boolean[] courseDates) {
        String s = "";

        if (courseDates[0]) s += "M ";
        if (courseDates[1]) s += "TU ";
        if (courseDates[2]) s += "W ";
        if (courseDates[3]) s += "TH ";
        if (courseDates[4]) s += "F ";

        return s.substring(0, s.length() - 1);
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
        boolean[] courseDatesList = {
                courseDatesField[0].isChecked(),
                courseDatesField[1].isChecked(),
                courseDatesField[2].isChecked(),
                courseDatesField[3].isChecked(),
                courseDatesField[4].isChecked(),
        };

        // Check if fields are valid, throw toasts if not
        boolean isValid = validation(courseName, courseInstructor, courseTime, courseDatesList);

        // Exit if fields are invalid
        if (!isValid) return;

        // Create course dates string only if needed
        String courseDates = buildCourseDatesString(courseDatesList);

        CourseDetails course = new CourseDetails(courseName, courseInstructor, courseTime, courseDates);
        courses.add(course);
        adapter.notifyDataSetChanged();

        Log.d("Number of courses", Integer.toString(adapter.getCount()));
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_details, container, false);

        // Set view global variables
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
            adapter = new CourseDetailsAdapter(requireContext(), courses);
            listView = view.findViewById(R.id.courses_list);
            listView.setAdapter(adapter);
        }

        // Initialize click listener for submit
        view.findViewById(R.id.submit).setOnClickListener(handleSubmit);
        return view;
    }
}