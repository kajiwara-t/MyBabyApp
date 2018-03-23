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

        final Intent intent = getIntent();
        String text = intent.getStringExtra("name");
        listView = (ListView) findViewById(R.id.nameList);

        final ArrayList<Sub_Record_List_Activity> list = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(Record_List_Activity.this);


        MyDatabase myDatabase = new MyDatabase(this);
        final SQLiteDatabase db = myDatabase.getWritableDatabase();

        c = db.query("person", new String[]{"name", "_id", "height", "weight", "nowYear",
                        "nowMonth", "nowDay","mORf","bmi"},
                "name='" + text + "'", null, null, null,
                null, null);

        boolean setTest = c.moveToLast();
        while (setTest) {
            list.add(new Sub_Record_List_Activity(c.getString(0), c.getString(4),
                    c.getString(5), c.getString(6), c.getString(2), c.getString(3),
            c.getString(7),c.getString(8)));
            setTest = c.moveToPrevious();
        }

        adapter.setSubList(list);
        listView.setAdapter(adapter);


        //リストを押下するとダイアログ表示
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                ListView listView1 = (ListView) parent;
                Sub_Record_List_Activity sub = (Sub_Record_List_Activity) listView1.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Record_List_Activity.this);
                builder.setTitle(sub.getChildName() + "ちゃんの記録");
                builder.setMessage("身長" + sub.getChildHeight() + "cm" + "\n" + "体重" + sub.getChildWeight() + "kg"
                + "\n"+ "カウプ指数" + sub.getRecordBmi());
                builder.setPositiveButton("OK", null);
                builder.show();

            }
        });


        //「身長・体重」ボタンを押下するとChart_Activityに画面遷移
        View chartButton = (Button) findViewById(R.id.MeasurementButton);
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c.moveToFirst();
                int mORf= Integer.parseInt(c.getString(7));

                if(mORf == 1){

                    Intent chartIntent = new Intent(Record_List_Activity.this, Male_Chart_Activity.class);
                    c.moveToFirst();
                    String chartKeyName = c.getString(0);
                    chartIntent.putExtra("name", chartKeyName);
                    startActivity(chartIntent);

                } else if(mORf == 2) {

                    Intent chartIntent = new Intent(Record_List_Activity.this, Female_Chart_Activity.class);
                    c.moveToFirst();
                    String chartKeyName = c.getString(0);
                    chartIntent.putExtra("name", chartKeyName);
                    startActivity(chartIntent);
                }
            }
        });

        //「カウプ指数」ボタンを押下するとChart_Activityに画面遷移
        View KAUPButton = (Button) findViewById(R.id.KAUPButton);
        KAUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c.moveToFirst();
                int mORf= Integer.parseInt(c.getString(7));

                if(mORf == 1){

                    Intent chartIntent = new Intent(Record_List_Activity.this, Male_BMI_Chart.class);
                    c.moveToFirst();
                    String chartKeyName = c.getString(0);
                    chartIntent.putExtra("name", chartKeyName);
                    startActivity(chartIntent);

                } else if(mORf == 2) {

                    Intent chartIntent = new Intent(Record_List_Activity.this, Female_BMI_Chart.class);
                    c.moveToFirst();
                    String chartKeyName = c.getString(0);
                    chartIntent.putExtra("name", chartKeyName);
                    startActivity(chartIntent);
                }
            }
        });

        //「予防接種」ボタンを押下するとYobou_Activityに画面遷移
        View vaccinationButton = (Button) findViewById(R.id.Yobou_button);
        vaccinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent chartIntent = new Intent(Record_List_Activity.this, Yobou_Activity.class);
                    c.moveToFirst();
                    String chartKeyName = c.getString(0);
                    chartIntent.putExtra("name", chartKeyName);
                    startActivity(chartIntent);
            }
        });
    }
}
