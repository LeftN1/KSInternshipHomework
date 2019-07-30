package com.keepsolid.ksinternshiphomework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.keepsolid.ksinternshiphomework.database.BookDBSchema.BookTable;

public class BookDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "books.db";

    public BookDBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BookTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                BookTable.cols.ID + ", " +
                BookTable.cols.TITLE + ", " +
                BookTable.cols.AUTHORS + ", " +
                BookTable.cols.DESCRIPTION + ", " +
                BookTable.cols.URL + ", " +
                BookTable.cols.THUMBNAIL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
