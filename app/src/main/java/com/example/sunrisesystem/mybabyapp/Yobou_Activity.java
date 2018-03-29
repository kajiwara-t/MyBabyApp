package com.example.sunrisesystem.mybabyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Yobou_Activity extends AppCompatActivity {

    ListView vaccinationList;

    Cursor vaccinationCursor;

    String vaccinationNameText;

    String textChildName;

    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yobou_);

        vaccinationList = (ListView) findViewById(R.id.yobou_List);

        //予防接種の種類をListに表示
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

//        arrayAdapter.add("B型肝炎");
//        arrayAdapter.add("ロタウイルス");
//        arrayAdapter.add("ヒブ");
//        arrayAdapter.add("小児用肺炎球菌");
//        arrayAdapter.add("四種混合(DPT-IPV)");
//        arrayAdapter.add("BCG");
//        arrayAdapter.add("MR(麻しん、風しん混合)");
//        arrayAdapter.add("水痘(みずぼうそう)");
//        arrayAdapter.add("おたふくかぜ");
//        arrayAdapter.add("日本脳炎");
//        arrayAdapter.add("インフルエンザ");
//        arrayAdapter.add("A型肝炎");
//        arrayAdapter.add("HPV(ヒトパピローマウイルス)");
//        arrayAdapter.add("髄膜炎菌");

        vaccinationList.setAdapter(arrayAdapter);
        vaccinationList.setScrollingCacheEnabled(false);

        Intent intent = getIntent();
        textChildName = intent.getStringExtra("name");

        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase vaccinationDB = myDatabase.getWritableDatabase();

        vaccinationCursor = vaccinationDB.query(true, "vaccination", new String[]{"name", "_id",
                        "vaccinationName", "requiredNumber", "number", "vaccinationYear", "vaccinationMonth", "vaccinationDay"},
                "name='" + textChildName + "'", null, "vaccinationName",
                null, null, null
        );

        vaccinationList.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                vaccinationCursor, new String[]{
                MyDatabase.COLUMN_VACCINATIONNAME, "_id", MyDatabase.COLUMN_NAME2},
                new int[]{android.R.id.text1}, 0));


        final Cursor vaccinationCursor2 = vaccinationDB.rawQuery("SELECT SUM(number) FROM vaccination " +
                "WHERE name= 'textChildName' AND vaccinationName GROUP BY vaccinationName  ",null);

                vaccinationCursor2.moveToFirst();

                while (vaccinationCursor2.moveToNext()){
                    number = vaccinationCursor2.getPosition();
                }


        //リストの行をクリックすると、ダイアログ表示
        vaccinationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView vaccinationList2 = (ListView) parent;
                vaccinationList2.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Yobou_Activity.this);
                builder.setTitle("予防接種履歴");
                builder.setMessage(vaccinationCursor.getString(2)
                        + "\n"
                        + "\n"
                        + "必要回数"
                        + "\n"
                        + vaccinationCursor.getString(3)
                        + "\n"
                        + "\n"
                        + "受けた回数"
                        + "\n"
                        + number
                        + "\n"
                        + "\n"
                        + "接種日"
                        + vaccinationCursor.getString(5) + "/"
                        + vaccinationCursor.getString(6) + "/"
                        + vaccinationCursor.getString(7)
                        + "\n"
                        + "\n"
                );
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }
}
