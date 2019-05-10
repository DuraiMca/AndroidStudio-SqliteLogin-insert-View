package com.urban.admin.firstapp.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table employee(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,mobile number,email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getdata() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from employee", null);
        return cursor;
    }
    public boolean validateUser(String username, String password){
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + "employee"  + " WHERE "
                        + "email"+ "='" + username +"'AND "+"mobile"+"='"+password+"'" ,  null);
        if (c.getCount()>0)
            return true;
        return false;
    }

}

