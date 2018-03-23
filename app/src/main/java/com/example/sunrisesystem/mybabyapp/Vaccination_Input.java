package com.example.sunrisesystem.mybabyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Vaccination_Input extends AppCompatActivity {

    Calendar today_calendar;
    private int today_year;
    private int today_month;
    private int today_day;

    String kidsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination__input);
        today_calendar = Calendar.getInstance();

        MyDatabase vaccinationHelper = new MyDatabase(this);
        final SQLiteDatabase db_vaccitination = vaccinationHelper.getWritableDatabase();

        Intent intent = getIntent();
        kidsName = intent.getStringExtra("name");
        TextView vaccinationKidsText = (TextView) findViewById(R.id.vaccinationKidsName);
        vaccinationKidsText.setText(kidsName);

        View inputButton = (Button)findViewById(R.id.vaccinationInputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder vaccinationBuilder = new AlertDialog.Builder(Vaccination_Input.this);
                vaccinationBuilder.setTitle("予防接種");
                vaccinationBuilder.setMessage("登録しますか?");
                vaccinationBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentValues inputVaccination = new ContentValues();
                        //inputVaccination.put();

                    }
                })
                        .setNegativeButton("いいえ",null)
                        .show();
            }
        });
    }

    //予防接種実施日を入力
    public void onClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int dayYear, int dayMonth, int dayDay) {

                Vaccination_Input.this.today_year = dayYear;
                today_month = dayMonth;
                today_day = dayDay;
                EditText textVaccinationDay = Vaccination_Input.this.findViewById(R.id.vaccinationDay);
                textVaccinationDay.setText(String.format("%02d/%02d/%02d",dayYear,dayMonth + 1,dayDay));
            }
        },
                today_calendar.get(Calendar.YEAR),
                today_calendar.get(Calendar.MONTH),
                today_calendar.get(Calendar.DAY_OF_MONTH)
        );
        long today = today_calendar.getTimeInMillis();
        datePickerDialog.show();
    }
}
