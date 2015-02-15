package com.example.deeksha.photonotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhotoDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "photoInfo.db";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "photo";
    public static final String PHOTO_ID = "id";
    public static final String PHOTO_CAPTION = "caption";
    public static final String PHOTO_LOCATION = "location";


    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" + PHOTO_ID + " integer primary key autoincrement, " +
            PHOTO_CAPTION + " text not null, " + PHOTO_LOCATION + " text not null)";

    public PhotoDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the photo table
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
