package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Male_BMI_Chart extends AppCompatActivity {

    private LineChart mChart;
    private double height[] = new double[999];
    private double weight[] = new double[999];
    private int x = 0;

    private LineDataSet boundaryDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male__chart_);

        mChart = (LineChart) findViewById(R.id.chart1);

        MyDatabase dbHelper = new MyDatabase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Intent intent = getIntent();
        String strData = intent.getStringExtra("name");

        String sql = "SELECT bmi FROM person WHERE name =" + "'" + strData + "'";
        //String sql2 = "SELECT weight FROM person WHERE name =" + "'" + strData + "'";

        Cursor cursor = db.rawQuery(sql, null, null);
        //Cursor cursor2 = db.rawQuery(sql2, null, null);

        double dbData = cursor.getCount();
        //double dbData2 = cursor2.getCount();

        cursor.moveToFirst();
        //cursor2.moveToFirst();

        ArrayList<Entry> recordBMIEntries = new ArrayList<>();
        //ArrayList<Entry> weightEntries = new ArrayList<>();

        YAxis left = mChart.getAxisLeft();
        left.setAxisMinValue(0);
        left.setAxisMaxValue(50);
        left.setDrawTopYLabelEntry(true);

        YAxis right = mChart.getAxisRight();
        right.setAxisMinValue(0);
        right.setAxisMaxValue(50);
        right.setDrawTopYLabelEntry(true);


        //身長・体重グラフ表示用

        if (dbData != 0) {

            while (x < dbData) {

                height[x] = cursor.getDouble(cursor.getColumnIndex("bmi"));

                cursor.moveToNext();

                recordBMIEntries.add(new Entry((float) height[x], x));


                LineDataSet KAUPDataSet = new LineDataSet(recordBMIEntries, "カウプ指数");
                KAUPDataSet.setColor(Color.RED);
                KAUPDataSet.setDrawCircles(true);
                //heightDataSet.setDrawFilled(true); //塗りつぶし
                //heightDataSet.setFillAlpha(20); //塗りつぶしの濃さ
                //heightDataSet.setFillColor(Color.RED); //塗りつぶしの色

                String[] labels = {"出生時", "1ヵ月", "2ヵ月", "3ヵ月", "4ヵ月", "5ヵ月", "6ヵ月", "7ヵ月"
                        , "8ヵ月", "9ヵ月", "10ヵ月", "11ヵ月", "12ヵ月", "13ヵ月"};

                //データベース・グラフセット
                LineData lineData = new LineData(labels);
                lineData.addDataSet(KAUPDataSet);
                mChart.setData(lineData);
                x++;
            }
        }
        cursor.close();
        db.close();
    }
}
