package com.nhrepon.neuralquotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "neuralQuotes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table quotes (id integer primary key autoincrement, quote text, author text, category text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists quotes");
    }

////////////////////////////////////////////////////////////////////////
    public void addQuotes(String quote, String author, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("quote", quote);
        contentValues.put("author", author);
        contentValues.put("category", category);

        db.insert("quotes", null, contentValues);

    }




    //////////////////////////////////////////////////////////////
    public Cursor getAllQuote(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from quotes", null);

        return cursor;

    }





}










