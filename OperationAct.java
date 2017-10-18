package com.example.user.bankpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class OperationAct extends AppCompatActivity {

    EditText etAmount;
    RadioButton rdoD,rdoW;
    BankDB obj;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        etAmount=(EditText)findViewById(R.id.etAmount);
        rdoD=(RadioButton)findViewById(R.id.rdoDeposit);
        rdoW=(RadioButton)findViewById(R.id.rdoWith);
    }

    public void saveOpr(View v)
    {
        String strType="";
        String SQL1="";
        String SQL2="";
        if(rdoD.isChecked())
        {
            strType="D";
            SQL1="insert into trans values(?,?,?)";
            SQL2="update credit set balance=balance + ? where username=?";
        }
        else
        {
            strType="W";
            SQL1="insert into trans values(?,?,?)";
            SQL2="update credit set balance=balance - ? where username=?";
        }

        if(strType.equals("W")) {
            Cursor cur=db.rawQuery("select balance from credit where username=?",
                    new String[]{BankDB.username});
            cur.moveToFirst();
            int x=Integer.parseInt(etAmount.getText().toString());
            int y=Integer.parseInt(cur.getString(0));
            if(x>y)
                Toast.makeText(this,"Invalid Credit",Toast.LENGTH_LONG).show();
            else
            {
                obj = new BankDB(this);
                db = obj.getWritableDatabase();
                db.execSQL(SQL1, new String[]{BankDB.username, strType, etAmount.getText().toString()});
                db.execSQL(SQL2, new String[]{etAmount.getText().toString(), BankDB.username});
                Intent i = new Intent(this, CreditAct.class);
                startActivity(i);
            }
        }
        else {
            obj = new BankDB(this);
            db = obj.getWritableDatabase();
            db.execSQL(SQL1, new String[]{BankDB.username, strType, etAmount.getText().toString()});
            db.execSQL(SQL2, new String[]{etAmount.getText().toString(), BankDB.username});
            Intent i = new Intent(this, CreditAct.class);
            startActivity(i);
        }
    }
}
