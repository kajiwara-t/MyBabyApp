package com.example.sunrisesystem.mybabyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class BMI_Select extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView1);

        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getWritableDatabase();

        Cursor c = db.query(true,"person",new String[]{ "name","_id","height","weight"},
                null,null,"name",null,null,null);

        c.moveToFirst();

        listView.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                c, new String[]{
                MyDatabase.COLUMN_NAME,"_id",MyDatabase.COLUMN_HEIGHT,MyDatabase.COLUMN_WEIGHT},
                new int[]{android.R.id.text1}, 0));


        //リストの行をクリックするとactivity_bmiに画面遷移
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ListView listView2 = (ListView) parent;
                Cursor item = (Cursor) listView2.getItemAtPosition(position);
                String search_name = item.getString(item.getColumnIndex("name"));
                Intent intent = new Intent(BMI_Select.this, BMI_Activity.class);
                intent.putExtra("name", search_name);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                ListView bmiLongClick = (ListView) parent;
                Cursor bmiClickItem = (Cursor) bmiLongClick.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Select.this);
                builder.setTitle("削除確認");
                builder.setMessage("削除しますか？");
                builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("いいえ",null);
                builder.show();

                return true;
            }
        });
    }
}
