package com.example.user.syncadaptercars24.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.syncadaptercars24.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mushareb Ali on 2/16/2017.
 * mushareba.ali@cars24.com
 */

public class CarsDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cars";
    private static final String TABLE_NAME = "user";


    // Contact table columns name

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DETAILS = "details";



    public CarsDatabase(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DETAILS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    /*insert new entry in database*/
   public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // Contact Name
        values.put(KEY_DETAILS, user.getDetials()); // Contact Details

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    /*Update user info*/
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_DETAILS, user.getDetials());

        // updating row
        return db.update(TABLE_NAME, values, KEY_NAME + " = ?",
                new String[] { String.valueOf(user.getName()) });
    }

    /*Get all details of user*/
    public List<User> getAllDetails() {
        List<User> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setDetials(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    public User getUserName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=new User();
        //String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        Cursor cursor;
        String Query ="SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_NAME +"=?";
        cursor = db.rawQuery(Query,new String [] {name});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                user = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));
            } while (cursor.moveToNext());

            cursor.close();
        }
       /* Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_NAME, KEY_DETAILS }, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
            cursor.moveToFirst();

            User user = new User(cursor.getColumnIndexOrThrow(KEY_ID),
                    cursor.getColumnName(1), cursor.getColumnName(2));*/
            return user;

    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=null;
        Cursor cursor;
        String Query ="SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_ID +"=?";
        cursor = db.rawQuery(Query,new String [] {String.valueOf(id)});


        if (cursor != null && cursor.moveToFirst())
        {
            do {
                user = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));
            } while (cursor.moveToNext());
            cursor.close();

        }
        // return user
        return user;
    }


    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getName()) });
        db.close();
    }

}
