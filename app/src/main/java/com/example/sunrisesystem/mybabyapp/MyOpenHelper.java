package com.example.sunrisesystem.mybabyapp;

/**
 * Created by SUNRISE SYSTEM on 2018/01/31.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "person.db";
    private static final int DB_Version = 3;

    /*
    private final String CREATE_TABLE_PERSON2 =
            "create table person2(_id text, search text, name text not null, birthYear text," +
                    "birthMonth text, birthDay text, height text, weight text, bmi text," +
                    "dateYear text, dateMonth text, dateDay text);";
    */

    /*
    private static final String TABLE_PERSON = "person";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTH_YEAR = "birthYear";
    private static final String COLUMN_BIRTH_MONTH = "birthMonth";
    private static final String COLUMN_BIRTH_DAY = "birthDay";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_BMI = "bmi";
    private static final String COLUMN_DATE = "date";
    */

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            //一人目
            db.execSQL("CREATE TABLE person" +
                    "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",search text" +          //検索番号
                    ",name text not null" +  //名前
                    ",birthYear text" +       //誕生年
                    ",birthMonth text" +      //誕生月
                    ",birthDay text" +        //誕生日
                    ",height text" +          //身長
                    ",weight text" +          //体重
                    ",bmi text" +             //カウプ指数・ローレル指数
                    ",dateYear text" +        //検診日（年）
                    ",dateMonth text" +       //検診日（月）
                    ",dateDay text" +         //検診日（日）
                    ")");


            //二人目
            db.execSQL(
                    "CREATE TABLE person2" +
                            "(" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                            ",search text" +          //検索番号
                            ",name text not null" +  //名前
                            ",birthYear text" +       //誕生年
                            ",birthMonth text" +      //誕生月
                            ",birthDay text" +        //誕生日
                            ",height text" +          //身長
                            ",weight text" +          //体重
                            ",bmi text" +             //カウプ指数・ローレル指数
                            ",dateYear text" +        //検診日（年）
                            ",dateMonth text" +       //検診日（月）
                            ",dateDay text" +         //検診日（日）
                            ")");


            //三人目
            db.execSQL("CREATE TABLE person3" +
                    "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",search text" +          //検索番号
                    ",name text not null" +  //名前
                    ",birthYear text" +       //誕生年
                    ",birthMonth text" +      //誕生月
                    ",birthDay text" +        //誕生日
                    ",height text" +          //身長
                    ",weight text" +          //体重
                    ",bmi text" +             //カウプ指数・ローレル指数
                    ",dateYear text" +        //検診日（年）
                    ",dateMonth text" +       //検診日（月）
                    ",dateDay text" +         //検診日（日）
                    ")");

            //四人目
            db.execSQL("CREATE TABLE person4" +
                    "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",search text" +          //検索番号
                    ",name text not null" +  //名前
                    ",birthYear text" +       //誕生年
                    ",birthMonth text" +      //誕生月
                    ",birthDay text" +        //誕生日
                    ",height text" +          //身長
                    ",weight text" +          //体重
                    ",bmi text" +             //カウプ指数・ローレル指数
                    ",dateYear text" +        //検診日（年）
                    ",dateMonth text" +       //検診日（月）
                    ",dateDay text" +         //検診日（日）
                    ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion == 2 && newVersion == 3) {
            try {
                db.execSQL("CREATE TABLE person" +
                        "(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                        ",search text" +          //検索番号
                        ",name text not null" +  //名前
                        ",birthYear text" +       //誕生年
                        ",birthMonth text" +      //誕生月
                        ",birthDay text" +        //誕生日
                        ",height text" +          //身長
                        ",weight text" +          //体重
                        ",bmi text" +             //カウプ指数・ローレル指数
                        ",dateYear text" +        //検診日（年）
                        ",dateMonth text" +       //検診日（月）
                        ",dateDay text" +         //検診日（日）
                        ")");


                //二人目
                db.execSQL(
                        "CREATE TABLE person2" +
                                "(" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                                ",search text" +          //検索番号
                                ",name text not null" +  //名前
                                ",birthYear text" +       //誕生年
                                ",birthMonth text" +      //誕生月
                                ",birthDay text" +        //誕生日
                                ",height text" +          //身長
                                ",weight text" +          //体重
                                ",bmi text" +             //カウプ指数・ローレル指数
                                ",dateYear text" +        //検診日（年）
                                ",dateMonth text" +       //検診日（月）
                                ",dateDay text" +         //検診日（日）
                                ")");


                //三人目
                db.execSQL("CREATE TABLE person3" +
                        "(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                        ",search text" +          //検索番号
                        ",name text not null" +  //名前
                        ",birthYear text" +       //誕生年
                        ",birthMonth text" +      //誕生月
                        ",birthDay text" +        //誕生日
                        ",height text" +          //身長
                        ",weight text" +          //体重
                        ",bmi text" +             //カウプ指数・ローレル指数
                        ",dateYear text" +        //検診日（年）
                        ",dateMonth text" +       //検診日（月）
                        ",dateDay text" +         //検診日（日）
                        ")");

                //四人目
                db.execSQL("CREATE TABLE person4" +
                        "(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                        ",search text" +          //検索番号
                        ",name text not null" +  //名前
                        ",birthYear text" +       //誕生年
                        ",birthMonth text" +      //誕生月
                        ",birthDay text" +        //誕生日
                        ",height text" +          //身長
                        ",weight text" +          //体重
                        ",bmi text" +             //カウプ指数・ローレル指数
                        ",dateYear text" +        //検診日（年）
                        ",dateMonth text" +       //検診日（月）
                        ",dateDay text" +         //検診日（日）
                        ")");
            } catch (SQLiteException err){
                err.printStackTrace();
            }
        }
    }
}