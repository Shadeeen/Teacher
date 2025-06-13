package com.example.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teacher.ClassTeacherItem;

import java.util.List;

public class ClassTeacherAdapter extends BaseAdapter {
    private Context context;
    private List<ClassTeacherItem> classList;

    public ClassTeacherAdapter(Context context, List<ClassTeacherItem> classList) {
        this.context = context;
        this.classList = classList;
    }

    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.class_item_teacher, parent, false);
        }

        ClassTeacherItem currentClass = classList.get(position);

        TextView classNameTextView = convertView.findViewById(R.id.className);
        TextView numStudentsTextView = convertView.findViewById(R.id.numStudents);

        classNameTextView.setText(currentClass.getClassName());
        numStudentsTextView.setText(String.valueOf(currentClass.getNumStudents()));

        return convertView;
}
}
