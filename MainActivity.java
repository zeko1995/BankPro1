package com.example.user.bankpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etU,etP;
    BankDB obj;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etU=(EditText)findViewById(R.id.etUserName);
        etP=(EditText)findViewById(R.id.etPassword);
    }

    public void openRegister(View v)
    {
        Intent i=new Intent(MainActivity.this,RegisterAct.class);
        startActivity(i);
    }

    public void login(View v)
    {
        obj=new BankDB(this);
        db=obj.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from users where username=? and password=?",
                new String[]{etU.getText().toString(),etP.getText().toString()});
        if(cur.getCount()==0)
            Toast.makeText(this,"Invalid User",Toast.LENGTH_LONG).show();
        else
        {
            BankDB.username=etU.getText().toString();
            Intent i=new Intent(this,ProfileAct.class);
            startActivity(i);
        }
    }
}
