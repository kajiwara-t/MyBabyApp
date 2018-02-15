package com.example.sunrisesystem.mybabyapp;

import android.app.Activity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Top_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("person", new String[]{"name", "birthYear", "birthMonth",
                "birthDay","height","weight","bmi"},null, null, null, null, null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d年 / %d月 / %d日 生まれ / 身長 %.2f cm / 体重 %.2f kg" +
                            " / カウプ指数 %.2f ", c.getString(0),
                    c.getInt(1), c.getInt(2), c.getInt(3), c.getDouble(4),c.getDouble(5) ,c.getDouble(6)));
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        db.close();
    }
}
