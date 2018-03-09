package com.example.sunrisesystem.mybabyapp;


public class Sub_Record_List_Activity {

    long id;
    private String childName;
    private String recordNowYear;
    private String recordNowMonth;
    private String recordNowDay;

    public Sub_Record_List_Activity(String childName, String recordNowYear, String recordNowMonth,
                                    String recordNowDay) {
        this.childName = childName;
        this.recordNowYear = recordNowYear;
        this.recordNowMonth = recordNowMonth;
        this.recordNowDay = recordNowDay;

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
}
