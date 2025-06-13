package com.example.teacher;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentNames extends Fragment {

    private List<ClassDetailsItem> classDetailsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_student_names, container, false);

        FloatingActionButton addStudentFab = view.findViewById(R.id.floating);
        addStudentFab.setOnClickListener(v -> addAssignmentDialog());  // Open dialog on FAB click

        classDetailsList = new ArrayList<>();
        classDetailsList.add(new ClassDetailsItem("Ahmad Ali"));
        classDetailsList.add(new ClassDetailsItem("Ahmad Ali"));
        classDetailsList.add(new ClassDetailsItem("Ahmad Ali"));

        ListView classDetailsListView = view.findViewById(R.id.listViewNames);
        ClassDetailsAdapter classDetailsAdapter = new ClassDetailsAdapter(view.getContext(), classDetailsList);
        classDetailsListView.setAdapter(classDetailsAdapter);

        classDetailsListView.setOnItemClickListener((parent, view1, position, id) -> {
            ClassDetailsItem clickedClass = classDetailsList.get(position);
            showMarksDialog(clickedClass);
        });

        return view;
    }

    private void showMarksDialog(ClassDetailsItem clickedClass) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_input_marks, null);

        EditText midMarksEditText = dialogView.findViewById(R.id.midMarks);
        EditText assignmentMarksEditText = dialogView.findViewById(R.id.assignmentMarks);
        EditText finalMarksEditText = dialogView.findViewById(R.id.finalMarks);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter Marks for " + clickedClass.getName())
                .setView(dialogView)
                .setPositiveButton("Submit", (dialog, which) -> {
                    String midMarks = midMarksEditText.getText().toString();
                    String assignmentMarks = assignmentMarksEditText.getText().toString();
                    String finalMarks = finalMarksEditText.getText().toString();

                    if (!midMarks.isEmpty() && !assignmentMarks.isEmpty() && !finalMarks.isEmpty()) {
                        Toast.makeText(getContext(), "Marks Submitted for " + clickedClass.getName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });

        builder.create().show();
    }

    private void addAssignmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_assignment, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        TextView dateTextView = dialogView.findViewById(R.id.dateTextView);
        EditText nameEditText = dialogView.findViewById(R.id.assName);
        EditText descEditText = dialogView.findViewById(R.id.desc);
        Button addButton = dialogView.findViewById(R.id.addAss);

        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()) // Set today's date as default
                .build();

        dateTextView.setOnClickListener(v -> materialDatePicker.show(getParentFragmentManager(), "DATE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = materialDatePicker.getHeaderText();
            dateTextView.setText(selectedDate);  // Set selected date to TextView
        });

        addButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String desc = descEditText.getText().toString().trim();

            if (name.isEmpty() || desc.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            dialog.show();
        });

        dialog.show();
}
}
