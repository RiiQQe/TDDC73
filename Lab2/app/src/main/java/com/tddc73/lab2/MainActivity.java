package com.tddc73.lab2;

import android.app.ExpandableListActivity;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        //Adds values to listData
        populate();

        //Creates a new adapter with listHeader and listData
        listAdapter = new MyExpandableListAdapter(this, listHeader, listData);

        //Sets the elv adapter
        elv.setAdapter(listAdapter);

        //OnClickListener for the expandable listview
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

        //TextChangeListener for the editText
        editText.addTextChangedListener(new TextWatcher() {
            String text;
            List<Integer> posList;
            int index = -1;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text = editText.getText().toString();


                // Possible yo use full path
                if (text.contains("/")) {
                    String[] kalle = text.split("/");
                    if (kalle.length > 1)
                        text = kalle[1];
                    else text = kalle[0];
                }

                //Returns positions for
                posList = listAdapter.getPositions(text);
                Boolean color = listAdapter.getColor(text);

                //If posList only contains 1 value, it corresponds to the top layer nodes.
                //Expands the group searched for in the editText.
                if(posList.size() == 1){
                    collapseAllGroups();
                    elv.expandGroup(posList.get(0));
                }
                else if ( posList.size() == 2){
                    collapseAllGroups();
                    elv.expandGroup(posList.get(0));
                    index = elv.getFlatListPosition(ExpandableListView.
                            getPackedPositionForChild(posList.get(0), posList.get(1)));
                    elv.setItemChecked(index, true);
                }
                // Set background color of input field
                if (posList.size() > 0 || text.length() == 0 || color) {
                    editText.setBackgroundColor(getResources().getColor(R.color.inputStandard));
                } else {
                    editText.setBackgroundColor(getResources().getColor(R.color.red));
                    //Uncheck last checked item if background is red
                    if(index != -1)
                        elv.setItemChecked(index, false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void collapseAllGroups(){
        for(int i = 0; i < listHeader.size(); i++){
            elv.collapseGroup(i);
        }
    }

    private void populate() {
        listHeader.add("light");
        listHeader.add("medium");
        listHeader.add("dark");

        List<String> light = new ArrayList<String>();
        light.add("yellow");
        light.add("blue");

        List<String> medium = new ArrayList<String>();
        medium.add("white");
        medium.add("gray");
        medium.add("yellow2");

        List<String> dark = new ArrayList<String>();
        dark.add("black");
        dark.add("red");

        listData.put(listHeader.get(0), light);
        listData.put(listHeader.get(1), medium);
        listData.put(listHeader.get(2), dark);
    }
}
