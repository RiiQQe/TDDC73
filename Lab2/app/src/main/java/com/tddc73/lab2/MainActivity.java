package com.tddc73.lab2;

import android.app.ExpandableListActivity;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView elv;
    ExpandableListAdapter ela;
    List<String> listHeader = new ArrayList<String>();
    HashMap<String, List<String>> listData = new HashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elv = (ExpandableListView) findViewById(R.id.list);

        populate();

        ela = new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return 0;
            }

            @Override
            public int getChildrenCount(int i) {
                return 0;
            }

            @Override
            public Object getGroup(int i) {
                return null;
            }

            @Override
            public Object getChild(int i, int i1) {
                return null;
            }

            @Override
            public long getGroupId(int i) {
                return 0;
            }

            @Override
            public long getChildId(int i, int i1) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int i) {

            }

            @Override
            public void onGroupCollapsed(int i) {

            }

            @Override
            public long getCombinedChildId(long l, long l1) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long l) {
                return 0;
            }
        };

        elv.setAdapter(ela);

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
