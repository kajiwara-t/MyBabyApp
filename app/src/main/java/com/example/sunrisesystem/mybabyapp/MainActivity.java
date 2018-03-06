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

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {


    //カレンダー用
    Calendar cal;
    private int birth_year;
    private int birth_month;
    private int birth_day;

    //性別選択用
    private RadioGroup rg1;
    private RadioButton Male, Female;
    int mORf = 0;




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
                        && birthHeight.getText().toString().equals("")==false
                        && birthWeight.getText().toString().equals("")==false) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("登録確認")
                            .setMessage("登録しますか？")
                            .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String name = nameText.getText().toString();
                                    String date = birthText.getText().toString();
                                    String height = birthHeight.getText().toString();
                                    String weight= birthWeight.getText().toString();
                                    ContentValues insertValues = new ContentValues();
                                    insertValues.put("name", name);
                                    insertValues.put("birthYear", birth_year);
                                    insertValues.put("birthMonth", birth_month + 1);
                                    insertValues.put("birthDay", birth_day);
                                    insertValues.put("height",height);
                                    insertValues.put("weight",weight);
                                    long id = db.insert("person", name, insertValues);
                                    Toast.makeText(getApplicationContext(), "登録しました", Toast.LENGTH_SHORT).show();
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

                /*
                //データベース ALL Delete用
                db.delete("person",null,null);
                //使う場合はif(name.equals)から下を一旦　"//"　で囲む
                */

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "削除する名前を入力してください",
                            Toast.LENGTH_SHORT).show();
                } else {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("削除確認")
                            .setMessage("削除しますか？")
                            .setPositiveButton("はい", new DialogInterface.OnClickListener() {

                                String name = nameText.getText().toString();
                                //String date= dayText.getText().toString();

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


        //データベースを閲覧する
        View lockButton = (Button) findViewById(R.id.lockButton);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
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
