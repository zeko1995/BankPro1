package com.example.user.bankpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CreditAct extends AppCompatActivity {

    TextView tv;
    BankDB obj;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        tv=(TextView)findViewById(R.id.tvCredit);

        obj=new BankDB(this);
        db=obj.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from credit where username=?",
                new String[]{BankDB.username});
        cur.moveToFirst();
        tv.setText(cur.getString(1));

    }
}
