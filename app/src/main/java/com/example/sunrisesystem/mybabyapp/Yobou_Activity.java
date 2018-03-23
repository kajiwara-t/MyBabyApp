package com.example.sunrisesystem.mybabyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Yobou_Activity extends AppCompatActivity {

    ListView vaccinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yobou_);

        vaccinationList = (ListView)findViewById(R.id.yobou_List);

        //予防接種の種類をListに表示
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        arrayAdapter.add( "B型肝炎");
        arrayAdapter.add("ロタウイルス");
        arrayAdapter.add("ヒブ");
        arrayAdapter.add("小児用肺炎球菌");
        arrayAdapter.add("四種混合(DPT-IPV)");
        arrayAdapter.add("BCG");
        arrayAdapter.add("MR(麻しん、風しん混合)");
        arrayAdapter.add("水痘(みずぼうそう)");
        arrayAdapter.add("おたふくかぜ");
        arrayAdapter.add("日本脳炎");
        arrayAdapter.add("インフルエンザ");
        arrayAdapter.add("A型肝炎");
        arrayAdapter.add("HPV(ヒトパピローマウイルス)");
        arrayAdapter.add("髄膜炎菌");

        vaccinationList.setAdapter(arrayAdapter);
        vaccinationList.setScrollingCacheEnabled(false);


        //リストの行をクリックすると、ダイアログ表示
        vaccinationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView vaccinationList2 = (ListView)parent;
                vaccinationList2.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Yobou_Activity.this);
                builder.setTitle("予防接種");
                builder.setMessage(arrayAdapter.getItem(position)
                        + "\n"
                        + "\n"
                        + "必要回数"
                        + "\n"
                        + "\n"
                        + "受けた回数"
                        + "\n"
                        + "\n"
                        + "接種日");
                builder.setPositiveButton("OK",null);
                builder.show();
            }
        });
    }
}
