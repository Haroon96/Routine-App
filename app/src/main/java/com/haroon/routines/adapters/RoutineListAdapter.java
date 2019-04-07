package com.haroon.routines.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.haroon.routines.R;
import com.haroon.routines.models.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RoutineListAdapter extends ArrayAdapter<Task> {

    private Context context;
    private ArrayList<Task> routineList;

    public RoutineListAdapter(Context context, ArrayList<Task> routines){
        super(context, 0, routines);
        this.context = context;
        this.routineList = routines;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            Tag tag = new Tag();
            view = LayoutInflater.from(context).inflate(R.layout.routine_row, parent, false);

            tag.textView = view.findViewById(R.id.routinename);
            tag.dates = view.findViewById(R.id.routinedate);

            view.setTag(tag);
        }

        Task routine = routineList.get(position);

        Tag tag = (Tag)view.getTag();

        tag.textView.setText(routine.name);
        tag.dates.setText(String.format("%s - %s", sdf.format(routine.startTime), sdf.format(routine.endTime)));

        return view;
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    private class Tag {
        public TextView textView;
        public TextView dates;
    }

}
