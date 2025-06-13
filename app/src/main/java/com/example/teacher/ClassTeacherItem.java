package com.example.teacher;

public class ClassTeacherItem {

    private String className;
    private int numStudents;

    public ClassTeacherItem(String className, int numStudents) {
        this.className = className;
        this.numStudents = numStudents;
    }

    public String getClassName() {
        return className;
    }

    public int getNumStudents() {
        return numStudents;}}