package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class BMI_Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__select);

        ListView listView = (ListView) findViewById(R.id.KidsList);

        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM person ", null);


        c.moveToFirst();

                String[] from = {"name"};
                int[] to = {android.R.id.text1};

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        android.R.layout.simple_list_item_1, c , from, to, 0);

                listView.setAdapter(adapter);




        //リストの行をクリックすると測定画面に移行
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ListView listView2 = (ListView) parent;
                Cursor item = (Cursor) listView2.getItemAtPosition(position);
                int search_id = item.getInt(item.getColumnIndex("_id"));
                String search_name = item.getString(item.getColumnIndex("name"));
                Intent intent = new Intent(BMI_Select.this, BMI_Activity.class);
                intent.putExtra("name", search_name);
                startActivity(intent);

            }
        });
    }
}
