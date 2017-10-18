package com.example.user.bankpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordAct extends AppCompatActivity {

    EditText etP,etC,etO;
    BankDB obj;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etP=(EditText)findViewById(R.id.etNewPassword);
        etC=(EditText)findViewById(R.id.etConfirmNewPassword);
        etO=(EditText)findViewById(R.id.etOldPassword);
    }

    public void changePassword(View v)
    {
        obj=new BankDB(this);

        db=obj.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from users where username=? and password=?",
                new String[]{BankDB.username,etO.getText().toString()});
        if(cur.getCount()==0)
            Toast.makeText(this,"Invalid Password",Toast.LENGTH_LONG).show();
        else {
            if (etP.getText().toString().equals(etC.getText().toString())) {

                db = obj.getWritableDatabase();
                db.execSQL("update users set password=? where username=?",
                        new String[]{etP.getText().toString(), BankDB.username});
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Please check your password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
