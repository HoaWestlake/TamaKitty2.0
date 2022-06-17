package com.example.tamakitty20;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.AccessControlContext;

public class LoginDB extends SQLiteOpenHelper{

    public static class LoginEntry implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_ID = "_id";// Mandatory
        public static final String COLUMN_NAME_NAME = "Username";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Login.db";
    }

    //private static final

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + LoginEntry.TABLE_NAME;

    public LoginDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("DBOpenHelper","before create");
        String SQL_CREATE_ENTRIES2 =
                "create table " + LoginEntry.TABLE_NAME + "(" +
                        LoginEntry.COLUMN_NAME_ID + " INTEGER primary key autoincrement," +
                        LoginEntry.COLUMN_NAME_NAME + " TEXT," +
                        LoginEntry.COLUMN_NAME_PASSWORD + " TEXT" + ")";
        db.execSQL(SQL_CREATE_ENTRIES2);
        Log.w("DBOpenHelper","CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void SaveData(){

    }


}

