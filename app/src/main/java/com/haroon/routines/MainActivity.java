package com.haroon.routines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haroon.routines.adapters.RoutineListAdapter;
import com.haroon.routines.dal.RoutineDB;
import com.haroon.routines.models.Routine;
import com.haroon.routines.services.RoutineService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateListView();
        startService(new Intent(getBaseContext(), RoutineService.class));
    }

    public void populateListView() {
        ListView listView = (ListView)findViewById(R.id.listview);


        ArrayAdapter<Routine> adapter = new RoutineListAdapter(this, RoutineDB.getRoutineList());

        listView.setAdapter(adapter);
    }

}
