package com.example.orchidmonitorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbConn extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Registration.db";
    private static final String TABLE_Users = "OrchidUsers";
    private static final String COLUMN_ID = "UserID";
    private static final String COLUMN_Name = "Name";
    private static final String COLUMN_Surname = "Surname";
    private static final String COLUMN_Email = "Email";
    private static final String COLUMN_Username = "Username";
    private static final String COLUMN_Password = "Password";


    public MyDbConn(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_table = "CREATE TABLE " +
                            TABLE_Users + "(" +
                            COLUMN_ID + " INTEGER PRIMARY KEY, " +
                            COLUMN_Name + " TEXT," +
                            COLUMN_Surname + " TEXT," +
                            COLUMN_Email + " TEXT," +
                            COLUMN_Username + " TEXT," +
                            COLUMN_Password + " TEXT" + ")";

        db.execSQL(CREATE_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNewUser(String name, String surname, String email, String username, String password){

        ContentValues values = new ContentValues();
        values.put(COLUMN_Name, name);
        values.put(COLUMN_Surname, surname);
        values.put(COLUMN_Email, email);
        values.put(COLUMN_Username, username);
        values.put(COLUMN_Password, password);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_Users, null, values);
        db.close();

    }

    public boolean checkLogin(String username, String password){

        boolean result = false;

        String query = "Select * FROM " + TABLE_Users +
                        " WHERE " + COLUMN_Username + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){

            if(cursor.getString(5).equals(password) ){
                result = true;
            }
        }
        else{
            result = false;
        }

        cursor.close();
        db.close();

        return result;

    }


}
