package com.example.appmusic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class UserData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION  = 1;
    private static final String TABLE_NAME  = "User";
    private static final String ID  = "Id";
    private static final String USER  = "User";
    private static final String PASSWORD  = "Password";


    public UserData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER + " TEXT," +
                PASSWORD + " TEXT)";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER,user.getUserName());
        values.put(PASSWORD,user.getPassword());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //check user có tồn tại trong cơ sở dữ liệu
    public boolean checkUserExist(String name, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{USER,PASSWORD}, USER + "=?" ,new String[]{String.valueOf(name)},null,null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        if (cursor.getCount() == 1 && pass.equals(cursor.getString(1))) {
            return true;
        }
        else {
            return false;
        }
    }
}
