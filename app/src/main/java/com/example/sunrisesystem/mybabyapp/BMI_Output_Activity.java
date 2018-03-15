package com.example.sunrisesystem.mybabyapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.sunrisesystem.mybabyapp.BMI_Cal.bmiCal;

public class BMI_Output_Activity extends AppCompatActivity {

    double data[] = new double[3];
    String name;


    //現在の日付を取得
    final Calendar calendar = Calendar.getInstance();
    final int nowYear = calendar.get(Calendar.YEAR);
    final int nowMonth = calendar.get(Calendar.MONTH) + 1;
    final int nowDay = calendar.get(Calendar.DAY_OF_MONTH);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_);

        MyDatabase helper = new MyDatabase(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        Intent intent = getIntent();
        data[0] = intent.getDoubleExtra("Height", 0);
        data[1] = intent.getDoubleExtra("Weight", 0);
        name = intent.getStringExtra("name");

        bmiCal(data);

        bmiResult(data);

        View outputButton = (Button) findViewById(R.id.output_button);
        outputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Output_Activity.this);
                builder.setTitle("計測結果");
                builder.setMessage("登録しますか？");
                builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentValues insertValues = new ContentValues();
                        insertValues.put("name", name);
                        insertValues.put("height", data[0]);
                        insertValues.put("weight", data[1]);
                        insertValues.put("bmi", data[2]);
                        insertValues.put("nowYear", nowYear);
                        insertValues.put("nowMonth", nowMonth);
                        insertValues.put("nowDay", nowDay);
                        long id = db.insert("person", name, insertValues);
                        Toast.makeText(getApplicationContext(), "登録しました", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("いいえ", null)
                        .show();
            }
        });
    }

        /*
        カウプ指数、ローレル指数ともにそのままでは計算できないので、計算の過程を分ける
        カウプ指数の計算式はBMIの計算式と同様なのでそのまま用いる。
        ただし、適正体重は異なる。
         */

    //カウプ指数・適正体重・適正体重との差の計算
    public void bmiResult(double data[]) {

//        //適正体重計算式
//        BigDecimal num22 = BigDecimal.valueOf(22);
//        BigDecimal pbWeight1 = bdbmi1.multiply(num22);
//        //適正体重表示用　double変換
//        double pbWeight = pbWeight1.doubleValue();
//
//        //現在体重と適正体重差の計算式
//        BigDecimal difWeight1 = bdWeight.subtract(pbWeight1);
//        //体重差表示用　double変換
//        double difWeight = difWeight1.doubleValue();

        //結果表示
        TextView textHeight = findViewById(R.id.outHeight); //身長結果
        textHeight.setText(String.valueOf(data[0]));

        TextView textWeight = findViewById(R.id.outWeight); //体重結果
        textWeight.setText(String.valueOf(data[1]));

        TextView textBmi = findViewById(R.id.outKaup); //カウプ結果
        textBmi.setText(String.format("%.2f", data[2]));

        TextView textName = findViewById(R.id.outName);
        textName.setText(String.format("%s", name));

        /* BigDecimalを用いないカウプ指数計算式
        //計算式
        double ex1 = data[0] * 0.01;
        double ex2 = ex1 * ex1;
        double ex3 = data[1] / ex2;
        //結果表示
        TextView textBmi = findViewById(R.id.bmiText);
        textBmi.setText(String.format("%.2f", ex3));
        */
    }

    //ローレル指数計算

    /*
     BigDecimalを用いていないので、何らかの数値で誤差が出る可能性あり
     元となるシステムにローレル指数を用いたグラフが見当たらないため、一旦凍結する
    public void keisan2(double data[]) {
        Intent intent = getIntent();
        data[0] = intent.getDoubleExtra("Height", 0); //入力された身長
        data[1] = intent.getDoubleExtra("Weight", 0); //入力された体重
        double Rohrer1 = data[0] * data[0] * data[0];
        double Rohrer2 = data[1] / Rohrer1;
        double Answer = Rohrer2 * 10000000;
        //結果表示
        TextView textBmi = findViewById(R.id.bmiText);
        textBmi.setText(String.format("%.2f", Answer));
    }*/
}
