package com.example.tamakitty20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class FeedReaderContract extends SQLiteOpenHelper{

    public FeedReaderContract(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Units";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PRICE = "Price";
        public static final String COLUMN_NAME_DESC = "Description";
        public static final String COLUMN_NAME_IMAGE = "Image";
        public static final String COLUMN_NAME_BIGDESC = "BigDesc";
        public static final String COLUMN_NAME_PRICEBTC = "PriceBTC";
        public static final String COLUMN_NAME_PRICEETH = "PriceETH";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Kitty.db";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NAME + " TEXT," +
                    FeedEntry.COLUMN_NAME_PRICE + " TEXT," +
                    FeedEntry.COLUMN_NAME_DESC + " TEXT," +
                    FeedEntry.COLUMN_NAME_IMAGE + " BLOB," +
                    FeedEntry.COLUMN_NAME_BIGDESC + " TEXT," +
                    FeedEntry.COLUMN_NAME_PRICEBTC + " REAL," +
                    FeedEntry.COLUMN_NAME_PRICEETH + " REAL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
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


