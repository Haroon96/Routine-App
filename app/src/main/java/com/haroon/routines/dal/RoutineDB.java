package com.haroon.routines.dal;

import com.haroon.routines.models.Routine;

import java.util.ArrayList;
import java.util.Date;

public class RoutineDB {

    public static Routine getCurrentRoutine() {
        Routine r = new Routine();
        r.name = "Routine #1";
        r.startDate = new Date();
        r.endDate = new Date();
        return r;
    }

    public static ArrayList<Routine> getRoutineList() {
        ArrayList<Routine> routineList = new ArrayList<Routine>();

        Routine r = new Routine();
        r.name = "Routine #1";
        r.startDate = new Date();
        r.endDate = new Date();

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
