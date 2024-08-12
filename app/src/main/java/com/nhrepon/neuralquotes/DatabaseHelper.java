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
    db.execSQL("create table quotes (id integer primary key autoincrement, quote text, author text, category text, image text)");
    db.execSQL("create table bookmarks (id integer primary key autoincrement, quote text, author text, category text, image text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists quotes");
    db.execSQL("drop table if exists bookmarks");
    }

//Add Quotes into database //////////////////////////////////////////////
    public void addQuotes(String quote, String author, String category, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("quote", quote);
        contentValues.put("author", author);
        contentValues.put("category", category);
        contentValues.put("image", image);

        db.insert("quotes", null, contentValues);

    }


    //Add Bookmarks into database /////////////////////////////////////////////
    public void addBookmarks(String quote, String author, String category, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("quote", quote);
        contentValues.put("author", author);
        contentValues.put("category", category);
        contentValues.put("image", image);

        db.insert("bookmarks", null, contentValues);

    }




    //Get all quotes ////////////////////////////////////////////////////////
    public Cursor getAllQuote(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from quotes ORDER BY id DESC", null);

        return cursor;

    }




    //Get all quotes ////////////////////////////////////////////////////////
    public Cursor getBookmark(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bookmarks", null);

        return cursor;

    }





}










