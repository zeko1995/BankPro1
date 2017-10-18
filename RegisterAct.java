package com.example.user.bankpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAct extends AppCompatActivity {

    EditText etU,etP,etC;
    BankDB obj;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etU=(EditText)findViewById(R.id.etRegUserName);
        etP=(EditText)findViewById(R.id.etRegPassword);
        etC=(EditText)findViewById(R.id.etRegConfirm);
    }

    public void register(View v)
    {
        if(etP.getText().toString().equals(etC.getText().toString()))
        {
            obj=new BankDB(this);
            db=obj.getReadableDatabase();
            Cursor cur=db.rawQuery("select * from users where username=?",
                    new String[]{etU.getText().toString()});
            if(cur.getCount()>0)
                Toast.makeText(this,"User already exist",Toast.LENGTH_LONG).show();
            else
            {
                db=obj.getWritableDatabase();
                db.execSQL("insert into users values(?,?);",
                        new String[]{etU.getText().toString(),etP.getText().toString()});
                db.execSQL("insert into credit values(?,?);",
                        new String[]{etU.getText().toString(),"0"});
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
            }
        }
        else
        {
            Toast.makeText(this,"Please check your password",Toast.LENGTH_LONG).show();
        }
    }
}
