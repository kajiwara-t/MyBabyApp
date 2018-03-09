package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Record_List_Activity extends AppCompatActivity {

    Cursor c;
    private ListView listView;
    private List<Sub_Record_List_Activity> items;
    protected Sub_Record_List_Activity sub_record_list_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        Intent intent = getIntent();
        String text = intent.getStringExtra("name");
        listView = (ListView) findViewById(R.id.nameList);

        final ArrayList<Sub_Record_List_Activity> list = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(Record_List_Activity.this);


        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getWritableDatabase();

        c = db.query("person", new String[]{"name", "_id", "height", "weight", "nowYear",
                        "nowMonth", "nowDay"},
                "name='" + text + "'", null, null, null,
                null, null);

        boolean setTest = c.moveToLast();
        while (setTest) {
            list.add(new Sub_Record_List_Activity(c.getString(0), c.getString(4),
                    c.getString(5), c.getString(6)));
            setTest = c.moveToPrevious();
        }

        adapter.setSubList(list);
        listView.setAdapter(adapter);

//        listView.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
//                c, new String[]{
//                MyDatabase.COLUMN_NAME, "_id", MyDatabase.COLUMN_HEIGHT, MyDatabase.COLUMN_WEIGHT},
//                new int[]{android.R.id.text1}, 0));


        //リストを押下するとダイアログ表示
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ListView recordList = (ListView) parent;

                c.moveToFirst();

                    String strName = c.getString(0);
                    String strHeight = c.getString(2);
                    String strWeight = c.getString(3);


                    AlertDialog.Builder builder = new AlertDialog.Builder(Record_List_Activity.this);
                    builder.setTitle(strName + "ちゃんの記録");
                    builder.setMessage("身長" + strHeight + "cm" + "　" + "体重" + strWeight + "kg");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
        });


        //「グラフ画面へ」ボタンを押下するとChart_Activityに画面遷移
        View chartButton = (Button) findViewById(R.id.MeasurementButton);
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chartIntent = new Intent(Record_List_Activity.this, Chart_Activity.class);
                c.moveToFirst();
                String chartKeyName = c.getString(0);
                chartIntent.putExtra("name", chartKeyName);
                startActivity(chartIntent);
            }
        });

    }
}
