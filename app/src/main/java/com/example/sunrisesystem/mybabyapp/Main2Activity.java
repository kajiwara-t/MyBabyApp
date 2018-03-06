package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String text = intent.getStringExtra("name");
        Double textHeight = intent.getDoubleExtra("height",0);
        Double textWeight = intent.getDoubleExtra("weight",0);

        TextView textView = findViewById(R.id.textView5);
        textView.setText(text);

        TextView setHeightText = findViewById(R.id.textView9);
        setHeightText.setText(String.valueOf(textHeight));

        TextView setWeightText = findViewById(R.id.textView10);
        setWeightText.setText(String.valueOf(textWeight));
    }
}
