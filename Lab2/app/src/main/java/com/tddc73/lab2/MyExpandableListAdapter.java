package com.tddc73.lab2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by micke on 2016-11-03.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {


    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;


    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    //Returns child given the specific position.
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    //Returns a list with positions. index one of text is the parent node, index two is the child.
    //Returns empty list if no parent is found. Returns list of size=1 if parent is found, size=2
    // if a child is found.
    public List<Integer> getPositions(String[] text) {
        ArrayList<Integer> list = new ArrayList<>();
        int pos;

        pos = this._listDataHeader.indexOf(text[1]);
        if (pos != -1){
            list.add(pos);
        }
        if(text.length > 2) {
            String s = this._listDataHeader.get(pos);
            List<String> tempList = this._listDataChild.get(s);
            int tempPos = tempList.indexOf(text[2]);

            if (tempPos != -1) {
                list.add(tempPos);
            }
        }
        return list;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        //Inflates the requested layout
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        //Sets child text.
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        //Inflates the requested layout.
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        //Sets title and typeface.
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public boolean getColor(String[] text) {

        Boolean parentExists = false;
        Boolean childExists = false;

        // See if parent exists
        String parentString = null;
        for (int i = 0; i < _listDataHeader.size(); i++) {
            String temp = _listDataHeader.get(i);
            if (temp.startsWith(text[1])) {
                parentExists = true;
                parentString = temp;
            }
        }
        if (text.length > 2 && parentString != null) {
            List<String> tempList = _listDataChild.get(parentString);
            for (int j = 0; j < tempList.size(); j++) {
                String tempStr = tempList.get(j);
                if (tempStr.startsWith(text[2])) childExists = true;
            }
        }

        if (text.length > 2 && parentExists && childExists) {
            return true;
        }
        else if (text.length == 2 && parentExists) {
            return true;
        }

        return false;

    }
}
