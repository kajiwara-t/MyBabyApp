package com.example.sunrisesystem.mybabyapp;

/**
 * Created by SUNRISE SYSTEM on 2018/01/31.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    //データベース名
    private static final String DB_NAME = "person.db";

    //データベースのバージョン指定
    private static final int DB_Version = 1;


//    private final String CREATE_TABLE_PERSON =
//            "create table person(_id text, search text, name text not null, birthYear text," +
//                    "birthMonth text, birthDay text, height text, weight text, bmi text," +
//                    "dateYear text, dateMonth text, dateDay text);";


    public static final String TABLE_PERSON = "person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BIRTH_YEAR = "birthYear";
    public static final String COLUMN_BIRTH_MONTH = "birthMonth";
    public static final String COLUMN_BIRTH_DAY = "birthDay";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_BMI = "bmi";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_NOW_YEAR = "nowYear";
    public static final String COLUMN_NOW_MONTH = "nowMonth";
    public static final String COLUMN_NOW_DAY = "nowDay";


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //テーブル作成
        db.execSQL("CREATE TABLE person"
                + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "search text, "          //検索番号
                + "name text not null, "   //名前
                + "birthYear text, "        //誕生年
                + "birthMonth text, "       //誕生月
                + "birthDay text, "         //誕生日
                + "height text, "           //身長
                + "weight text, "           //体重
                + "bmi text, "              //カウプ指数・ローレル指数
                + "nowYear text, "         //検診日（年）
                + "nowMonth text, "        //検診日（月）
                + "nowDay text "          //検診日（日）
                + ")");

    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){


        }
    }

