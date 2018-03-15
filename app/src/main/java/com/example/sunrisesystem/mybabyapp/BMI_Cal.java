package com.example.sunrisesystem.mybabyapp;

import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by SUNRISE SYSTEM on 2018/03/15.
 */

public class BMI_Cal {

    public BMI_Cal(){

    }

    public static double[] bmiCal(double[] data) {

        //入力身長(cm)から入力身長(m)へと変換
        BigDecimal num1 = BigDecimal.valueOf(data[0]); //身長(cm)
        BigDecimal num2 = BigDecimal.valueOf(0.01);    //身長(m)

        //計算準備
        BigDecimal bdHeight = num1.multiply(num2);
        BigDecimal bdWeight = BigDecimal.valueOf(data[1]);

        //BMI計算式　BigDecimal使用
        BigDecimal bdBmi1 = bdHeight.multiply(bdHeight);
        BigDecimal bdBmi2 = bdWeight.divide(bdBmi1, 2, BigDecimal.ROUND_HALF_UP);
        data[2] = bdBmi2.doubleValue();


        //適正体重計算式
        BigDecimal num22 = BigDecimal.valueOf(22);
        BigDecimal pbWeight1 = bdBmi1.multiply(num22);
        //適正体重表示用　double変換
        double pbWeight = pbWeight1.doubleValue();

        //現在体重と適正体重差の計算式
        BigDecimal difWeight1 = bdWeight.subtract(pbWeight1);
        //体重差表示用　double変換
        double difWeight = difWeight1.doubleValue();

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

        return data;
    }
}
