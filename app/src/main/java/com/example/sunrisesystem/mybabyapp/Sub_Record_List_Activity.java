package com.example.sunrisesystem.mybabyapp;


import android.database.Cursor;

public class Sub_Record_List_Activity {

    long id;
    private String childName;
    private String childHeight;
    private String childWeight;
    private String childBmi;
    private String recordNowYear;
    private String recordNowMonth;
    private String recordNowDay;
    private String recordMorF;

    public Sub_Record_List_Activity(String childName, String recordNowYear, String recordNowMonth,
                                    String recordNowDay, String childHeight, String childWeight,
                                    String recordMorF, String recordBmi) {
        this.childName = childName;
        this.recordNowYear = recordNowYear;
        this.recordNowMonth = recordNowMonth;
        this.recordNowDay = recordNowDay;
        this.childHeight = childHeight;
        this.childWeight = childWeight;
        this.childBmi = recordBmi;
        this.recordMorF=recordMorF;

    }

    public String getChildName(){
        return childName;
    }
    public void setChildName(String childName){
        this.childName = childName;
    }

    public String getRecordNowYear(){
        return recordNowYear;
    }
    public void setRecordNowYear(String recordNowYear){
        this.recordNowYear = recordNowYear;
    }

    public String getRecordNowMonth(){
        return recordNowMonth;
    }
    public void setRecordNowMonth(String recordNowMonth){
        this.recordNowMonth = recordNowMonth;
    }

    public String getRecordNowDay(){
        return recordNowDay;
    }
    public void setRecordNowDay(String recordNowDay){
        this.recordNowDay=recordNowDay;
    }

    public long getId() {
        return id;
    }

    public String getChildHeight(){
        return childHeight;
    }

    public void setChildHeight(){
        this.childHeight = childHeight;
    }

    public String getChildWeight(){
        return childWeight;
    }

    public void setChildWeight(){
        this.childWeight = childWeight;
    }

    public String getRecordBmi(){
        return childBmi;
    }

    public void setRecordBmi(){
        this.childBmi = childBmi;
    }

    public String getMorF(){
        return recordMorF;
    }

    public void setRecordMorF(){
        this.recordMorF = recordMorF;
    }
}
