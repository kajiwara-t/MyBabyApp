package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Male_Chart_Activity extends AppCompatActivity {

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



        //男子幼児用成長曲線

        //男子身長・3パーセンタイル
        ArrayList<Entry> maleHeight = new ArrayList<>();
        maleHeight.add(new Entry(44.0f, 0)); //出生時
        maleHeight.add(new Entry(50.9f, 1));
        maleHeight.add(new Entry(54.5f, 2));
        maleHeight.add(new Entry(57.5f, 3));
        maleHeight.add(new Entry(59.9f, 4));
        maleHeight.add(new Entry(61.9f, 5));
        maleHeight.add(new Entry(63.6f, 6));
        maleHeight.add(new Entry(65.0f, 7));
        maleHeight.add(new Entry(66.3f, 8));
        maleHeight.add(new Entry(67.4f, 9));
        maleHeight.add(new Entry(68.4f, 10));
        maleHeight.add(new Entry(69.4f, 11));
        maleHeight.add(new Entry(70.3f, 12));
        LineDataSet maleHeightSet = new LineDataSet(maleHeight, "");
        maleHeightSet.setColor(Color.GREEN);
        maleHeightSet.setDrawCircles(false);


        //男子身長・97パーセンタイル
        ArrayList<Entry> maleHeight2 = new ArrayList<>();
        maleHeight2.add(new Entry(52.6f, 0)); //出生時
        maleHeight2.add(new Entry(59.6f, 1));
        maleHeight2.add(new Entry(63.2f, 2));
        maleHeight2.add(new Entry(66.1f, 3));
        maleHeight2.add(new Entry(68.5f, 4));
        maleHeight2.add(new Entry(70.4f, 5));
        maleHeight2.add(new Entry(72.1f, 6));
        maleHeight2.add(new Entry(73.6f, 7));
        maleHeight2.add(new Entry(75.0f, 8));
        maleHeight2.add(new Entry(76.2f, 9));
        maleHeight2.add(new Entry(77.4f, 10));
        maleHeight2.add(new Entry(78.5f, 11));
        maleHeight2.add(new Entry(79.6f, 12));
        LineDataSet maleHeightSet2 = new LineDataSet(maleHeight2, "");
        maleHeightSet2.setColor(Color.GREEN);
        maleHeightSet2.setDrawCircles(false);



        //男子体重・3パーセンタイル
        ArrayList<Entry> maleWeight = new ArrayList<>();
        maleWeight.add(new Entry(2.13f, 0)); //出生時
        maleWeight.add(new Entry(3.39f, 1));
        maleWeight.add(new Entry(4.19f, 2));
        maleWeight.add(new Entry(4.84f, 3));
        maleWeight.add(new Entry(5.35f, 4));
        maleWeight.add(new Entry(5.74f, 5));
        maleWeight.add(new Entry(6.06f, 6));
        maleWeight.add(new Entry(6.32f, 7));
        maleWeight.add(new Entry(6.53f, 8));
        maleWeight.add(new Entry(6.71f, 9));
        maleWeight.add(new Entry(6.86f, 10));
        maleWeight.add(new Entry(7.02f, 11));
        maleWeight.add(new Entry(7.16f, 12));
        LineDataSet maleWeightSet = new LineDataSet(maleWeight, "");
        maleWeightSet.setColor(Color.DKGRAY);
        maleWeightSet.setDrawCircles(false);


        //男子体重・97パーセンタイル
        ArrayList<Entry> maleWeight2 = new ArrayList<>();
        maleWeight2.add(new Entry(3.67f, 0)); //出生時
        maleWeight2.add(new Entry(5.54f, 1));
        maleWeight2.add(new Entry(6.67f, 2));
        maleWeight2.add(new Entry(7.53f, 3));
        maleWeight2.add(new Entry(8.18f, 4));
        maleWeight2.add(new Entry(8.67f, 5));
        maleWeight2.add(new Entry(9.05f, 6));
        maleWeight2.add(new Entry(9.37f, 7));
        maleWeight2.add(new Entry(9.63f, 8));
        maleWeight2.add(new Entry(9.85f, 9));
        maleWeight2.add(new Entry(10.06f, 10));
        maleWeight2.add(new Entry(10.27f, 11));
        maleWeight2.add(new Entry(10.48f, 12));
        LineDataSet maleWeightSet2 = new LineDataSet(maleWeight2, "");
        maleWeightSet2.setColor(Color.DKGRAY);
        maleWeightSet2.setDrawCircles(false);




//        //塗りつぶし
        maleWeightSet2.setDrawFilled(true);
        maleWeightSet2.setFillAlpha(20); //塗りつぶしの濃さ
        maleWeightSet2.setFillColor(Color.DKGRAY);



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

                LineDataSet weightDataSet = new LineDataSet(weightEntries, "体重");
                weightDataSet.setColor(Color.BLUE);
                weightDataSet.setDrawCircles(true);

                String[] labels = {"出生時", "1ヵ月", "2ヵ月", "3ヵ月", "4ヵ月", "5ヵ月", "6ヵ月", "7ヵ月"
                        , "8ヵ月", "9ヵ月", "10ヵ月", "11ヵ月", "12ヵ月", "13ヵ月"};

                //データベース・グラフセット
                LineData lineData = new LineData(labels, heightDataSet);
                lineData.addDataSet(weightDataSet);

                //男児用成長曲線
                lineData.addDataSet(maleHeightSet);
                lineData.addDataSet(maleHeightSet2);
                lineData.addDataSet(maleWeightSet);
                lineData.addDataSet(maleWeightSet2);
                mChart.setData(lineData);
                x++;
            }
        }
        cursor.close();
        cursor2.close();
        db.close();
    }
}






