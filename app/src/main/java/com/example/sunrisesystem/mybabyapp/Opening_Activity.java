package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Opening_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_);

        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1.setImageResource(R.drawable.couple_baby_dakko);

        //登録ボタンの実装
        View saveButton = (Button) findViewById(R.id.op_button1);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Opening_Activity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        //測定ボタンの実装
        View bmiButton = (Button)findViewById(R.id.op_button2);
        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opening_Activity.this,BMI_Select.class);
                startActivity(intent);
            }
        });

        //閲覧ボタンの実装
        View searchButton = (Button)findViewById(R.id.op_button3);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opening_Activity.this,List_Activity.class);
                startActivity(intent);
            }
        });
    }
}
