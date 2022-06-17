package com.example.tamakitty20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class StoreDB extends SQLiteOpenHelper{

    public StoreDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static class storeEntry implements BaseColumns {
        public static final String TABLE_NAME = "Units";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PRICE = "Price";
        public static final String COLUMN_NAME_DESC = "Description";
        public static final String COLUMN_NAME_IMAGE = "Image";
        public static final String COLUMN_NAME_BIGDESC = "BigDesc";
        public static final String COLUMN_NAME_PRICEBTC = "PriceBTC";
        public static final String COLUMN_NAME_PRICEETH = "PriceETH";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "KittyStore.db";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + storeEntry.TABLE_NAME + " (" +
                    storeEntry._ID + " INTEGER PRIMARY KEY," +
                    storeEntry.COLUMN_NAME_NAME + " TEXT," +
                    storeEntry.COLUMN_NAME_PRICE + " TEXT," +
                    storeEntry.COLUMN_NAME_DESC + " TEXT," +
                    storeEntry.COLUMN_NAME_IMAGE + " BLOB," +
                    storeEntry.COLUMN_NAME_BIGDESC + " TEXT," +
                    storeEntry.COLUMN_NAME_PRICEBTC + " REAL," +
                    storeEntry.COLUMN_NAME_PRICEETH + " REAL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + storeEntry.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}


