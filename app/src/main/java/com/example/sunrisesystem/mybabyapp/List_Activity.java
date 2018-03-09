package com.example.sunrisesystem.mybabyapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class List_Activity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView1);


        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getWritableDatabase();

        Cursor c = db.query(true,"person",new String[]{ "name","_id","height","weight"},
                null,null,"name",null,null,null);


        listView.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                c, new String[]{
                MyDatabase.COLUMN_NAME,"_id",MyDatabase.COLUMN_HEIGHT,MyDatabase.COLUMN_WEIGHT},
                new int[]{android.R.id.text1}, 0));


        //リストの行をクリックすると子供の記録画面に移動
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ListView listView2 = (ListView) parent;
                Cursor item = (Cursor) listView2.getItemAtPosition(position);
                String search_name = item.getString(item.getColumnIndex("name"));
                double search_height = item.getDouble(item.getColumnIndex("height"));
                double search_weight = item.getDouble(item.getColumnIndex("weight"));
                Intent intent = new Intent(List_Activity.this, Record_List_Activity.class);
                intent.putExtra("name", search_name);
                intent.putExtra("height",search_height);
                intent.putExtra("weight",search_weight);
                startActivity(intent);

            }
        });
    }
}