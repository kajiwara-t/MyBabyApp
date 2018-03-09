package com.example.sunrisesystem.mybabyapp;


import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter{

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Sub_Record_List_Activity> subList;

    public CustomAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setSubList(ArrayList<Sub_Record_List_Activity> subList) {
        this.subList = subList;
    }

    @Override
    public int getCount() {
        return subList.size();
    }

    @Override
    public Object getItem(int position) {
        return subList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return subList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_sub_record_list,parent,false);


        TextView childName= (TextView)convertView.findViewById(R.id.babyText);
        childName.setText(subList.get(position).getChildName());

        TextView nowYearText = (TextView)convertView.findViewById(R.id.setTextNowYear);
        nowYearText.setText(subList.get(position).getRecordNowYear());

        TextView nowMonthText = (TextView)convertView.findViewById(R.id.setTextNowMonth);
        nowMonthText.setText(subList.get(position).getRecordNowMonth());

        TextView nowDayText = (TextView)convertView.findViewById(R.id.setTextNowDay);
        nowDayText.setText(subList.get(position).getRecordNowDay());

        return convertView;
    }
}
