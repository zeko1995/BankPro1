package com.example.user.bankpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListAct extends AppCompatActivity {

    ListView lv;
    BankDB obj;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv=(ListView)findViewById(R.id.list_view);

        obj=new BankDB(this);
        db=obj.getReadableDatabase();

        Cursor cur=db.rawQuery("select * from trans where username=?",
                new String[]{BankDB.username});
        ArrayList<String> list=new ArrayList<>();
        cur.moveToFirst();
        while(cur.isAfterLast()==false)
        {
            list.add(cur.getString(1) + "-" + cur.getString(2));
            cur.moveToNext();
        }

        ArrayAdapter adt=new ArrayAdapter(this,R.layout.abc,list);
        lv.setAdapter(adt);
    }
}
