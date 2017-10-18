package com.example.user.bankpro;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileAct extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv=(TextView)findViewById(R.id.tvWelcome);
        tv.setText("Welcome " + BankDB.username);
    }

    public void goToAct(View v)
    {
        if(v.getId()==R.id.ibChangePassword)
        {
            Intent i=new Intent(this,ChangePasswordAct.class);
            startActivity(i);
        }
        if(v.getId()==R.id.ibCredit)
        {
            Intent i=new Intent(this,CreditAct.class);
            startActivity(i);
        }
        if(v.getId()==R.id.ibOpr)
        {
            Intent i=new Intent(this,OperationAct.class);
            startActivity(i);
        }
        if(v.getId()==R.id.ibList)
        {
            Intent i=new Intent(this,ListAct.class);
            startActivity(i);
        }
        if(v.getId()==R.id.ibLogout)
        {
            BankDB.username=null;
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
