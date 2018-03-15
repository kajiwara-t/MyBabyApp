package com.example.sunrisesystem.mybabyapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.sunrisesystem.mybabyapp.BMI_Cal.bmiCal;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {


    //誕生日用カレンダー
    Calendar cal;
    private int birth_year;
    private int birth_month;
    private int birth_day;

    //性別選択用
    private RadioGroup rg1;
    private RadioButton Male, Female;
    int mORf = 0;

    //現在の日付を取得
    final Calendar calendar = Calendar.getInstance();
    final int nowYear = calendar.get(Calendar.YEAR);
    final int nowMonth = calendar.get(Calendar.MONTH) + 1;
    final int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

    double data[] = new double[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        rg1 = (RadioGroup) findViewById(R.id.RadioGroup1);
        rg1.setOnCheckedChangeListener(this);

        cal = Calendar.getInstance();

        MyDatabase helper = new MyDatabase(this);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText birthText = findViewById(R.id.birthText);
        final EditText birthHeight = findViewById(R.id.birthHeight);
        final EditText birthWeight = findViewById(R.id.birthWeight);
        final SQLiteDatabase db = helper.getWritableDatabase();


        //データベースに登録する
        View saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((mORf >= 1) && nameText.getText().toString().equals("") == false
                        && birthText.getText().toString().equals("") == false
                        && birthHeight.getText().toString().equals("") == false
                        && birthWeight.getText().toString().equals("") == false) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("登録確認")
                            .setMessage("登録しますか？")
                            .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String name = nameText.getText().toString();
                                    String date = birthText.getText().toString();
                                    String height = birthHeight.getText().toString();
                                    String weight = birthWeight.getText().toString();

                                    data[0] = Double.parseDouble(height);
                                    data[1] = Double.parseDouble(weight);

                                    bmiCal(data);

                                    data[2] = data[2];

                                    int morf = mORf;
                                    ContentValues insertValues = new ContentValues();
                                    insertValues.put("name", name);
                                    insertValues.put("birthYear", birth_year);
                                    insertValues.put("birthMonth", birth_month + 1);
                                    insertValues.put("birthDay", birth_day);
                                    insertValues.put("height", height);
                                    insertValues.put("weight", weight);
                                    insertValues.put("mORf", mORf);
                                    insertValues.put("nowYear", nowYear);
                                    insertValues.put("nowMonth", nowMonth);
                                    insertValues.put("nowDay", nowDay);
                                    insertValues.put("bmi",data[2]);
                                    long id = db.insert("person", name, insertValues);
                                    Toast.makeText(getApplicationContext(), "登録しました",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("いいえ", null)
                            .show();

                } else {
                    Toast.makeText(getApplicationContext(), "未入力です", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //データベースから削除する
        View deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String date = birthText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "削除する名前を入力してください",
                            Toast.LENGTH_SHORT).show();
                } else {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("削除確認")
                            .setMessage("削除しますか？")
                            .setPositiveButton("はい", new DialogInterface.OnClickListener() {

                                String name = nameText.getText().toString();

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(MainActivity.this, "削除しました",
                                            Toast.LENGTH_SHORT).show();
                                    db.delete("person", "name=?", new String[]{name});
                                }
                            })
                            .setNegativeButton("いいえ", null)
                            .show();
                }
            }
        });
    }

    //性別を選択する
    public void onCheckedChanged(RadioGroup group, int ButtonId) {
        Male = findViewById(R.id.radioMale);
        Female = findViewById(R.id.radioFemale);
        if (group == rg1) {
            switch (ButtonId) {
                case R.id.radioMale:
                    mORf = 1;
                    break;

                case R.id.radioFemale:
                    mORf = 2;
                    break;

                default:
                    break;
            }
        }
    }

    //生年月日を入力する
    public void onClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                MainActivity.this.birth_year = year;
                birth_month = month;
                birth_day = dayOfMonth;
                EditText textBirth = MainActivity.this.findViewById(R.id.birthText);
                textBirth.setText(String.format("%02d/%02d/%02d", year, month + 1, dayOfMonth));
            }
        },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        long ms = cal.getTimeInMillis();
        datePickerDialog.show();
    }
}
