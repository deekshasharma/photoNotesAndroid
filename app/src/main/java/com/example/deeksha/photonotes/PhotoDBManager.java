package com.example.deeksha.photonotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDBManager {

    private SQLiteDatabase sqLiteDatabase;
    private PhotoDbHelper photoDbHelper;
    private String[] allColumns = {PhotoDbHelper.PHOTO_ID, PhotoDbHelper.PHOTO_CAPTION, PhotoDbHelper.PHOTO_LOCATION};
    private String where = null;
    private String whereArgs[] = null;
    private String groupBy = null;
    private String having = null;
    private String order = null;


    public PhotoDBManager(Context context)
    {
        photoDbHelper = new PhotoDbHelper(context);
        try {
            open();
        } catch (SQLException e) {
            throw new IllegalStateException("can not open database: " + e.getMessage());
        }
    }

    public void open() throws SQLException
    {
        sqLiteDatabase = photoDbHelper.getWritableDatabase();
    }

    public void close()
    {
        photoDbHelper.close();
    }

    public long insertToDatabase(Photo photo)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PhotoDbHelper.PHOTO_CAPTION,photo.getPHOTO_CAPTION());

        contentValues.put(PhotoDbHelper.PHOTO_LOCATION, photo.getPHOTO_LOCATION().toString());
        long id = sqLiteDatabase.insert(PhotoDbHelper.TABLE_NAME,null, contentValues);
        Log.d("Inserted photo caption: ", photo.getPHOTO_CAPTION());
        Log.d("Inserted photo location: " ,photo.getPHOTO_LOCATION().toString());
        return id;
    }

    public List<Photo> getFromDB()
    {
        List<Photo> photoListFromDb = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(PhotoDbHelper.TABLE_NAME,allColumns,where,whereArgs,groupBy,having,order);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Photo photo = new Photo();
            photo.setPHOTO_ID(cursor.getInt(0));
            photo.setPHOTO_CAPTION(cursor.getString(1));

            photo.setPHOTO_LOCATION(Uri.parse(cursor.getString(2)));
            photoListFromDb.add(photo);
            cursor.moveToNext();
        }
        cursor.close();
        return photoListFromDb;
    }


    public void deletePhoto() {
        System.out.println("Photo table deleted: ");
        sqLiteDatabase.delete(photoDbHelper.TABLE_NAME, where,whereArgs);
    }



}
