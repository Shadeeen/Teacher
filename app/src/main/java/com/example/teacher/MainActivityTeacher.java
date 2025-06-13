package com.example.teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.teacher.HomeTeacherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityTeacher extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        loadFragment(new HomeTeacherFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Show home layout, hide fragment container
                findViewById(R.id.mainViews).setVisibility(View.VISIBLE);
                findViewById(R.id.container).setVisibility(View.GONE);
                return true;

            } else if (id == R.id.nav_att) {
                selectedFragment = new AttendanceFragment();
            } else if (id == R.id.nav_sch) {
                selectedFragment = new TeacherSchedule();
            } else if (id == R.id.nav_cla) {
                selectedFragment = new TeacherClassesFragment();
            }

            if (selectedFragment != null) {
                findViewById(R.id.mainViews).setVisibility(View.GONE);
                findViewById(R.id.container).setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, selectedFragment)
                        .commit();
                return true;
            }

            return false;
        });
        schedule();
        Attendance();
        classes();
        profile();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void profile(){

        ImageView profile=findViewById(R.id.settings_icon);
        profile.setOnClickListener(e->{

            ProfileBottomTeacher bottomSheet = new ProfileBottomTeacher();
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

        });
    }

    public void schedule() {
        Button teachersScreen = findViewById(R.id.btnViewSchedule);
        teachersScreen.setOnClickListener(e -> {

            bottomNavigationView.setSelectedItemId(R.id.nav_sch);

            findViewById(R.id.mainViews).setVisibility(View.GONE);
            findViewById(R.id.container).setVisibility(View.VISIBLE);

            Fragment teacherFragment = new TeacherSchedule();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, teacherFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    public void Attendance(){
        Button subjectScreen = findViewById(R.id.btnAttendance);
        subjectScreen.setOnClickListener(e->{

            bottomNavigationView.setSelectedItemId(R.id.nav_att);


            findViewById(R.id.mainViews).setVisibility(View.GONE);
            findViewById(R.id.container).setVisibility(View.VISIBLE);

            Fragment attendanceFragment = new AttendanceFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, attendanceFragment)
                    .addToBackStack(null)
                    .commit();

        });

    }


    public void classes(){
        Button subjectScreen = findViewById(R.id.btnClasses);
        subjectScreen.setOnClickListener(e->{


            bottomNavigationView.setSelectedItemId(R.id.nav_cla);

            findViewById(R.id.mainViews).setVisibility(View.GONE);
            findViewById(R.id.container).setVisibility(View.VISIBLE);

            Fragment classFragment = new TeacherClassesFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, classFragment)
                    .addToBackStack(null)
                    .commit();

        });

    }
}