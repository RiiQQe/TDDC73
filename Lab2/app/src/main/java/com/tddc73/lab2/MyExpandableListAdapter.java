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

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    public List<Integer> getPositions(String[] text) {
        ArrayList<Integer> list = new ArrayList<>();
        int pos = -1;

        pos = this._listDataHeader.indexOf(text[0]);
        if (pos != -1){
            list.add(pos);
        }
        for(int i = 0; i < this._listDataHeader.size(); i++){
            String s = this._listDataHeader.get(i);
            List<String> tempList = this._listDataChild.get(s);
            int tempPos = tempList.indexOf(text[1]);
            if(tempPos != -1){
                list.add(i);
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

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

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

    public int getGroupPosition(String group) {
        int pos = -1;

        for(int i=0; i<this._listDataHeader.size(); i++) {
            if(group.toLowerCase().equals(this._listDataHeader.get(i).toString().toLowerCase()))
                pos = i;
        }

        return pos;
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
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

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

        // This could be done more dynamic..
        if (text.length > 2 && parentExists && childExists) {
            return true;
        }
        else if (text.length == 2 && parentExists) {
            return true;
        }

        return false;

    }
}
