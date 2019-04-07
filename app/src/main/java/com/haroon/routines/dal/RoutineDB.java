package com.haroon.routines.dal;

import com.haroon.routines.models.Task;

import java.util.ArrayList;
import java.util.Date;

public class RoutineDB {

    public static Task getNextTask() {
        Task r = new Task();
        r.name = "Routine #1";
        r.startTime = new Date();
        r.endTime = new Date();
        return r;
    }

    public static ArrayList<Task> getActivityList() {
        ArrayList<Task> routineList = new ArrayList<Task>();

        Task r = new Task();
        r.name = "Routine #1";
        r.startTime = new Date();
        Date d = new Date();
        d.setTime(d.getTime() + 10000);
        r.endTime = d;

        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);
        routineList.add(r);

        return routineList;
    }
}
