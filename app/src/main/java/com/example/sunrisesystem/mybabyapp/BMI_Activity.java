package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMI_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button record;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_);
        record = (Button) findViewById(R.id.record_button);
        record.setOnClickListener(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.reName);
        textView.setText(name);

    }

    //ボタン押下でBMI_Output_Activityへ遷移
    public void onClick(View view) {

        //身長・体重を入力
        final EditText heightText = findViewById(R.id.reHeight);
        final EditText weightText = findViewById(R.id.reWeight);

        //身長・体重のいずれかが未入力の場合
        if (heightText.getText().toString().equals("") == false &&
                weightText.getText().toString().equals("") == false) {

            //身長・体重の数値が入力された場合
            Intent intent = new Intent(this, Output_Activity.class);
            double height = Double.parseDouble(heightText.getText().toString());
            double weight = Double.parseDouble(weightText.getText().toString());

            //　入力身長が 0 以下　999より上の場合
            if ((height <= 0) || (height > 999)) {
                Toast.makeText(getApplicationContext(), "範囲外です", Toast.LENGTH_SHORT).show();

                //入力体重が　0 以下　999より上の場合
            } else if ((weight <= 0) || (weight > 999)) {
                Toast.makeText(getApplicationContext(), "範囲外です", Toast.LENGTH_SHORT).show();

                //入力数値が 1 ～ 999 の範囲内の場合
            } else if ((weight >= 1) && (weight <= 999)) {
                intent.putExtra("Height", height);
                intent.putExtra("Weight", weight);
                intent.putExtra("name",name);

                startActivity(intent);

            } else {
                //入力数値判定 if文用
            }

            //未入力の場合
        } else {
            Toast.makeText(getApplicationContext(), "未入力です", Toast.LENGTH_SHORT).show();
        }
    }
}
