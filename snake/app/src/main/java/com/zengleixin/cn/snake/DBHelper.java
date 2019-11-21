package com.zengleixin.cn.snake;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table users (id integer primary key autoincrement,name varchar(50),password varchar(50),grade varchar(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}