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
    public static final String COLUMN_MORF = "mORf";



    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //子供成長テーブル作成
        db.execSQL("CREATE TABLE person"
                + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "search text, "                          //検索番号
                + "name text not null, "                  //名前
                + "birthYear text, "                       //誕生年
                + "birthMonth text, "                      //誕生月
                + "birthDay text, "                        //誕生日
                + "height text, "                          //身長
                + "weight text, "                          //体重
                + "bmi text, "                             //カウプ指数・ローレル指数
                + "nowYear text, "                         //検診日（年）
                + "nowMonth text, "                        //検診日（月）
                + "nowDay text,"                           //検診日（日）
                + "mORf text"                              //性別
                + ")");

        db.execSQL("CREATE TABLE vaccination"
                +"("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name text not null, "                   //受けた子供の名前
                + "vaccinationName, "                       //予防接種の名前
                + "required number, "                       //予防接種の必要回数
                + "number, "                                //予防接種を受けた回数
                + "nowYear, "                               //予防接種を受けた年
                + "nowMonth, "                              //予防接種を受けた月
                + "nowDay"                                  //予防接種を受けた日
                + ")");

//        //予防接種用テーブル作成
//        db.execSQL("CREATE TABLE vaccination"
//        +"("
//                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
//                + "name text not null, "                  //受けた子供の名前
//                + "HepatitisB, "                           //B型肝炎
//                + "RotaVirus, "                            //ロタウイルス
//                + "Hib, "                                  //ヒブ
//                + "Pneumococcal Vaccine, "                 //小児用肺炎球菌
//                + "DPTIPV, "                               //四種混合(DPT-IPV)
//                + "BCG, "                                  //BCG
//                + "MR, "                                   //MR(麻しん,風しん混合)
//                + "ChickenPoxvaricella, "                  //水痘
//                + "Mumps, "                                //おたふくかぜ
//                + "JapaneseEncephalitis, "                 //日本脳炎
//                + "Influenza, "                            //インフルエンザ
//                + "HepatitisA, "                           //A型肝炎
//                + "HPV, "                                  //HPV(ヒトパピローマウイルス)
//                + "NeisseriaMeningitidis"                  //髄膜炎菌
//                + ")");
    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){


        }
    }

