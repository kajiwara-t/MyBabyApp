package com.example.sunrisesystem.mybabyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Name_List_Activity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView1);


        MyDatabase myDatabase = new MyDatabase(this);
        final SQLiteDatabase db = myDatabase.getWritableDatabase();

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
                Intent intent = new Intent(Name_List_Activity.this, Record_List_Activity.class);
                intent.putExtra("name", search_name);
                intent.putExtra("height",search_height);
                intent.putExtra("weight",search_weight);
                startActivity(intent);
            }
        });

        //リストの行を長押しすると削除確認のダイアログ表示
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                ListView longClick = (ListView) parent;
                Cursor clickItem = (Cursor) longClick.getItemAtPosition(position);
                final String deleteName = clickItem.getString(clickItem.getColumnIndex("name"));

                AlertDialog.Builder builder = new AlertDialog.Builder(Name_List_Activity.this);
                        builder.setTitle("削除確認");
                        builder.setMessage("削除しますか？");
                        builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Name_List_Activity.this, "削除しました",
                                        Toast.LENGTH_SHORT).show();
                                db.delete("person","name=?",new String[]{deleteName});
                            }
                        });
                        builder.setNegativeButton("いいえ",null);
                        builder.show();

                return true;
            }
        });
    }
}