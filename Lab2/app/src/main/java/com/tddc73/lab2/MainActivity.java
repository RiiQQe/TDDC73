package com.tddc73.lab2;

import android.app.ExpandableListActivity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView elv;
    MyExpandableListAdapter listAdapter;
    List<String> listHeader = new ArrayList<String>();
    HashMap<String, List<String>> listData = new HashMap<String, List<String>>();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elv = (ExpandableListView) findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.serchtext);

        populate();

        listAdapter = new MyExpandableListAdapter(this, listHeader, listData);

        elv.setAdapter(listAdapter);

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                // Change color on pressed item
                int index = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(i, i1));
                expandableListView.setItemChecked(index, true);

                // Get path of selected item
                // and set text field to path
                String child = listAdapter.getChild(i, i1).toString();
                String parent = listAdapter.getGroup(i).toString();
                String path = parent + "/" + child;
                editText.setText(path);
                return false;
            }
        });
    }

    private void populate() {
        listHeader.add("light");
        listHeader.add("Medium");
        listHeader.add("Dark");

        List<String> light = new ArrayList<String>();
        light.add("yellow");
        light.add("blue");

        List<String> medium = new ArrayList<String>();
        medium.add("white");
        medium.add("gray");

        List<String> dark = new ArrayList<String>();
        dark.add("black");
        dark.add("red");

        listData.put(listHeader.get(0), light);
        listData.put(listHeader.get(1), medium);
        listData.put(listHeader.get(2), dark);
    }
}
