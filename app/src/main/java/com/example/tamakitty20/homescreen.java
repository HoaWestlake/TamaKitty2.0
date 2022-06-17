package com.example.tamakitty20;


import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class homescreen extends AppCompatActivity implements View.OnClickListener {

    Button mini, collection, shop;
    ImageView img;
    StoreDB storehelper = new StoreDB(this, StoreDB.storeEntry.DATABASE_NAME, null, StoreDB.storeEntry.DATABASE_VERSION);
    FeedReaderContract feedhelper = new FeedReaderContract(this, FeedReaderContract.FeedEntry.DATABASE_NAME, null, FeedReaderContract.FeedEntry.DATABASE_VERSION);
    Bitmap b;
    Intent intent , intent2;
    String Name, Price, Desc;
    String Check;
    byte[] Checkb;
    Bundle bundle;
    public homescreen() {
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        intent = new Intent(this, testgallery.class);
        intent2 = new Intent(this, buy.class);
        mini = findViewById(R.id.mini);
        collection = findViewById(R.id.collection);
        shop = findViewById(R.id.shop);
        mini.setOnClickListener(this);
        collection.setOnClickListener(this);
        shop.setOnClickListener(this);


//========HARDCODING KITTIES========
//        b = BitmapFactory.decodeResource(getResources(), R.mipmap.rainbow);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
//        byte[] img = bos.toByteArray();
//
//        SQLiteDatabase db = storehelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(StoreDB.storeEntry.COLUMN_NAME_NAME, "Rainbow Kitty");
//        values.put(StoreDB.storeEntry.COLUMN_NAME_PRICE, "200");
//        values.put(StoreDB.storeEntry.COLUMN_NAME_DESC, "Rainbow Kitty");
//        values.put(StoreDB.storeEntry.COLUMN_NAME_IMAGE, img);
//        values.put(StoreDB.storeEntry.COLUMN_NAME_BIGDESC, "A kitty that is all colours of the Rainbow");
//        values.put(StoreDB.storeEntry.COLUMN_NAME_PRICEBTC, "0,012");
//        values.put(StoreDB.storeEntry.COLUMN_NAME_PRICEETH, "0,20");
//        Long newRowID = db.insert(StoreDB.storeEntry.TABLE_NAME, null, values);
//        Toast.makeText(getApplicationContext(), "new row:" + newRowID, Toast.LENGTH_SHORT).show();
//        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mini:
                if (collection.getVisibility() == View.VISIBLE)
                {
                    collection.setVisibility(View.INVISIBLE);
                    shop.setVisibility(View.INVISIBLE);
                }else
                {
                    collection.setVisibility(View.VISIBLE);
                    shop.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.collection:
                if (collection.getVisibility() == View.VISIBLE)
                {
                    startActivity(intent);
                }
                break;
            case R.id.shop:
                if (collection.getVisibility() == View.VISIBLE)
                {
                    startActivity(intent2);
                }
                break;
        }
    }

    public void deleteTitle()
    {
        SQLiteDatabase db = feedhelper.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, "id = '1'", null);
        db.close();

    }
}