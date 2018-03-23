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

public class Female_Chart_Activity extends AppCompatActivity {

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

        String sql = "SELECT height FROM person WHERE name =" + "'" + strData + "'";
        String sql2 = "SELECT weight FROM person WHERE name =" + "'" + strData + "'";

        Cursor cursor = db.rawQuery(sql, null, null);
        Cursor cursor2 = db.rawQuery(sql2, null, null);

        double dbData = cursor.getCount();
        double dbData2 = cursor2.getCount();

        cursor.moveToFirst();
        cursor2.moveToFirst();

        ArrayList<Entry> heightEntries = new ArrayList<>();
        ArrayList<Entry> weightEntries = new ArrayList<>();


        //女子幼児用成長曲線

        //女子身長・3パーセンタイル
        ArrayList<Entry> femaleHeight = new ArrayList<>();
        femaleHeight.add(new Entry(44.0f, 0)); //出生時
        femaleHeight.add(new Entry(50.0f, 1));
        femaleHeight.add(new Entry(53.3f, 2));
        femaleHeight.add(new Entry(56.0f, 3));
        femaleHeight.add(new Entry(58.2f, 4));
        femaleHeight.add(new Entry(60.1f, 5));
        femaleHeight.add(new Entry(61.7f, 6));
        femaleHeight.add(new Entry(63.1f, 7));
        femaleHeight.add(new Entry(64.4f, 8));
        femaleHeight.add(new Entry(65.5f, 9));
        femaleHeight.add(new Entry(66.5f, 10));
        femaleHeight.add(new Entry(67.4f, 11));
        femaleHeight.add(new Entry(68.3f, 12));
        LineDataSet femaleHeightSet = new LineDataSet(femaleHeight, "");
        femaleHeightSet.setColor(Color.parseColor("#ff7f50"));
        femaleHeightSet.setDrawCircles(false);


        //女子身長・97パーセンタイル
        ArrayList<Entry> femaleHeight2 = new ArrayList<>();
        femaleHeight2.add(new Entry(52.0f, 0)); //出生時
        femaleHeight2.add(new Entry(58.4f, 1));
        femaleHeight2.add(new Entry(61.7f, 2));
        femaleHeight2.add(new Entry(64.5f, 3));
        femaleHeight2.add(new Entry(66.8f, 4));
        femaleHeight2.add(new Entry(68.7f, 5));
        femaleHeight2.add(new Entry(70.4f, 6));
        femaleHeight2.add(new Entry(71.9f, 7));
        femaleHeight2.add(new Entry(73.2f, 8));
        femaleHeight2.add(new Entry(74.5f, 9));
        femaleHeight2.add(new Entry(75.6f, 10));
        femaleHeight2.add(new Entry(76.7f, 11));
        femaleHeight2.add(new Entry(77.8f, 12));
        LineDataSet femaleHeightSet2 = new LineDataSet(femaleHeight2, "");
        femaleHeightSet2.setColor(Color.parseColor("#ff7f50"));
        femaleHeightSet2.setDrawCircles(false);


        //女子体重・3パーセンタイル
        ArrayList<Entry> femaleWeight = new ArrayList<>();
        femaleWeight.add(new Entry(2.13f, 0)); //出生時
        femaleWeight.add(new Entry(3.39f, 1));
        femaleWeight.add(new Entry(4.19f, 2));
        femaleWeight.add(new Entry(4.84f, 3));
        femaleWeight.add(new Entry(5.35f, 4));
        femaleWeight.add(new Entry(5.74f, 5));
        femaleWeight.add(new Entry(6.06f, 6));
        femaleWeight.add(new Entry(6.32f, 7));
        femaleWeight.add(new Entry(6.53f, 8));
        femaleWeight.add(new Entry(6.71f, 9));
        femaleWeight.add(new Entry(6.86f, 10));
        femaleWeight.add(new Entry(7.02f, 11));
        femaleWeight.add(new Entry(7.16f, 12));
        LineDataSet femaleWeightSet = new LineDataSet(femaleWeight, "");
        femaleWeightSet.setColor(Color.parseColor("#87ceeb"));
        femaleWeightSet.setDrawCircles(false);


        //女子体重・97パーセンタイル
        ArrayList<Entry> femaleWeight2 = new ArrayList<>();
        femaleWeight2.add(new Entry(3.67f, 0)); //出生時
        femaleWeight2.add(new Entry(5.54f, 1));
        femaleWeight2.add(new Entry(6.67f, 2));
        femaleWeight2.add(new Entry(7.53f, 3));
        femaleWeight2.add(new Entry(8.18f, 4));
        femaleWeight2.add(new Entry(8.67f, 5));
        femaleWeight2.add(new Entry(9.05f, 6));
        femaleWeight2.add(new Entry(9.37f, 7));
        femaleWeight2.add(new Entry(9.63f, 8));
        femaleWeight2.add(new Entry(9.85f, 9));
        femaleWeight2.add(new Entry(10.06f, 10));
        femaleWeight2.add(new Entry(10.27f, 11));
        femaleWeight2.add(new Entry(10.48f, 12));
        LineDataSet femaleWeightSet2 = new LineDataSet(femaleWeight2, "");
        femaleWeightSet2.setColor(Color.parseColor("#87ceeb"));
        femaleWeightSet2.setDrawCircles(false);


        YAxis left = mChart.getAxisLeft();
        left.setAxisMinValue(0);
        left.setAxisMaxValue(200);
        left.setDrawTopYLabelEntry(true);

        YAxis right = mChart.getAxisRight();
        right.setAxisMinValue(0);
        right.setAxisMaxValue(100);
        right.setDrawTopYLabelEntry(true);


        //身長・体重グラフ表示用

        if (dbData != 0) {

            while (x < dbData && x < dbData2) {

                height[x] = cursor.getDouble(cursor.getColumnIndex("height"));
                weight[x] = cursor2.getDouble(cursor2.getColumnIndex("weight"));

                cursor.moveToNext();
                cursor2.moveToNext();

                heightEntries.add(new Entry((float) height[x], x));
                weightEntries.add(new Entry((float) weight[x], x));

                LineDataSet heightDataSet = new LineDataSet(heightEntries, "身長");
                heightDataSet.setColor(Color.RED);
                heightDataSet.setDrawCircles(true);
                //heightDataSet.setDrawFilled(true); //塗りつぶし
                //heightDataSet.setFillAlpha(20); //塗りつぶしの濃さ
                //heightDataSet.setFillColor(Color.RED); //塗りつぶしの色

                LineDataSet weightDataSet = new LineDataSet(weightEntries, "体重");
                weightDataSet.setColor(Color.BLUE);
                weightDataSet.setDrawCircles(true);
                //weightDataSet.setDrawFilled(true);
                //weightDataSet.setFillAlpha(20);
                //weightDataSet.setFillColor(Color.BLUE);

                String[] labels = {"出生時", "1ヵ月", "2ヵ月", "3ヵ月", "4ヵ月", "5ヵ月", "6ヵ月", "7ヵ月"
                        , "8ヵ月", "9ヵ月", "10ヵ月", "11ヵ月", "12ヵ月", "13ヵ月"};

                //データベース・グラフセット
                LineData lineData = new LineData(labels, heightDataSet);
                lineData.addDataSet(weightDataSet);

                //女子幼児成長線
                lineData.addDataSet(femaleHeightSet);
                lineData.addDataSet(femaleHeightSet2);
                lineData.addDataSet(femaleWeightSet);
                lineData.addDataSet(femaleWeightSet2);
                mChart.setData(lineData);
                x++;
            }
        }
        cursor.close();
        cursor2.close();
        db.close();
    }
}

