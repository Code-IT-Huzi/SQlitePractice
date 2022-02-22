package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class myDbAdapter extends SQLiteOpenHelper {
    public myDbAdapter(Context context) {
        super(context, "UserRecords", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table Records(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("Drop table if exists Records");

    }
    public boolean insertdata(String username, String password){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        Long result = DB.insert("Records", null, contentValues);
        if (result == -1) {
            return false;
        }
        else
            return true;

    }

    public boolean updatedata(String username, String password){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("select * from Records where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            int result = DB.update("Records", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else
                return true;
        } else
            return false;

    }
    public boolean deletedata(String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Records where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            int result = DB.delete("Records", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else
                return true;
        } else
            return false;

    }
    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Records", null);
        return cursor;
    }

}
