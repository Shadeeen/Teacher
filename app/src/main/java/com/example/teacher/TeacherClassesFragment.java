package com.example.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class TeacherClassesFragment extends Fragment {
    public TeacherClassesFragment() {}

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_classes, container, false);

        listView = view.findViewById(R.id.listView);

        List<ClassTeacherItem> classItems = new ArrayList<>();
        classItems.add(new ClassTeacherItem("Math 101", 30));
        classItems.add(new ClassTeacherItem("Physics 201", 25));
        classItems.add(new ClassTeacherItem("Computer Science 301", 40));

        ClassTeacherAdapter classAdapter = new ClassTeacherAdapter(getContext(), classItems);
        listView.setAdapter(classAdapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            ClassTeacherItem clickedClass = classItems.get(position);
            StudentNames classDetailsFragment = new StudentNames();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.container, classDetailsFragment);
            ft.addToBackStack("schedule");
            ft.commit();
        });

        return view;
}
}
