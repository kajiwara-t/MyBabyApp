package com.example.sunrisesystem.mybabyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BMI_Select extends AppCompatActivity {

    ListView listView;

    private ArrayList<String> arrayList = new ArrayList<>();

    private LayoutInflater listInflater = null;

    Cursor listCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_select);
        listView = (ListView) findViewById(R.id.KidsList);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setItemChecked(0,true);


        MyDatabase myDatabase = new MyDatabase(this);
        SQLiteDatabase db = myDatabase.getWritableDatabase();

        listCursor = db.query(true,"person",new String[]{ "name","_id","height","weight"},
                null,null,"name",null,null,null);

        listCursor.moveToFirst();

        do{
            arrayList.add(listCursor.getString(0));
        } while (listCursor.moveToNext());


       listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,arrayList));


        //リストの行をクリックするとactivity_bmiに画面遷移
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long l) {
                ListView listSelectSet = (ListView) parent;
                final String listSelect = (String) listSelectSet.getItemAtPosition(position);

                final Button bmiButton = (Button)findViewById(R.id.bmiButton);
                bmiButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        listCursor.moveToFirst();
                        Intent bmiIntent = new Intent(BMI_Select.this,BMI_Activity.class);
                        bmiIntent.putExtra("name",listSelect);
                        startActivity(bmiIntent);
                    }
                });


                final Button vaccinationButton = (Button)findViewById(R.id.vaccitinationButton);
                vaccinationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listCursor.moveToFirst();
                        Intent vaccinationIntent = new Intent(BMI_Select.this,Vaccination_Input.class);
                        vaccinationIntent.putExtra("name",listSelect);
                        startActivity(vaccinationIntent);
                    }
                });
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
