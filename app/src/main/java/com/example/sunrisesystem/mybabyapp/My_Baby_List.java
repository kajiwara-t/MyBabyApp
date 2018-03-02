package com.example.sunrisesystem.mybabyapp;

/**
 * Created by SUNRISE SYSTEM on 2018/02/20.
 */

import android.util.Log;

public class My_Baby_List {
    protected int id;
    protected String name;
    protected String birthYear;
    protected String birthMonth;
    protected String birthDay;
    protected String height;
    protected String weight;
    protected String bmi;
    protected String dataYear;
    protected String dataMonth;
    protected String dataDay;

    public My_Baby_List(int id, String name,String birthYear,String birthMonth,String birthDay,
                        String height, String weight, String bmi, String dataYear, String dataMonth,
                        String dataDay){
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.dataYear = dataYear;
        this.dataMonth = dataMonth;
        this.dataDay = dataDay;

    }

    public int getId(){
        Log.d("取得したID",String.valueOf(id));
        return id;
    }

    public String getName(){
        return name;
    }

    public String getBirthYear(){
        return birthYear;
    }

    public String getBirthMonth(){
        return birthMonth;
    }

    public String getBirthDay(){
        return birthDay;
    }

    public String getHeight(){
        return height;
    }

    public String getWeight(){
        return weight;
    }

    public String getBmi(){
        return bmi;
    }

    public String getDataYear(){
        return dataYear;
    }
    public String getDataMonth(){
        return  dataMonth;
    }
    public String getDataDay(){
        return dataDay;
    }
}

