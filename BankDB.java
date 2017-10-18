package com.example.user.bankpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 11/19/2016.
 */

public class BankDB extends SQLiteOpenHelper {

    public static String username;

    public BankDB(Context context) {
        super(context, "bank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username varchar(20),password varchar(20));");
        db.execSQL("create table credit(username varchar(20),balance number(6));");
        db.execSQL("create table trans(username varchar(20),trans_type varchar(1),amount number(4));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
