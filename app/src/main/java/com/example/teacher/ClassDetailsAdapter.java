package com.example.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClassDetailsAdapter extends BaseAdapter {

    private Context context;
    private List<ClassDetailsItem> classDetailsList;

    public ClassDetailsAdapter(Context context, List<ClassDetailsItem> classDetailsList) {
        this.context = context;
        this.classDetailsList = classDetailsList;
    }

    @Override
    public int getCount() {
        return classDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return classDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.fragment_class_details_item, parent, false);
        }

        ClassDetailsItem currentItem = classDetailsList.get(position);

        TextView studentNameTextView = convertView.findViewById(R.id.studentName);
        studentNameTextView.setText(currentItem.getName());

        return convertView;
}
}

