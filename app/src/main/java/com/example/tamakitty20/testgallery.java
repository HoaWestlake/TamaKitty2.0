package com.example.tamakitty20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class testgallery extends AppCompatActivity implements View.OnClickListener {

    FeedReaderContract feedhelper = new FeedReaderContract(this, FeedReaderContract.FeedEntry.DATABASE_NAME, null, FeedReaderContract.FeedEntry.DATABASE_VERSION);

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private CustomAdapter adapter;

    private List<Bitmap> imgid = new ArrayList<Bitmap>();
    private List<String> names = new ArrayList<String>();
    private List<String> desc = new ArrayList<String>();
    private List<String> prices = new ArrayList<String>();
    private List<String> test = new ArrayList<String>();

    byte[] Checkb;
    Bitmap b;
    String check, cHeck2, check3, check4;

    ImageView img;
    TextView name;
    TextView Sdesc;
    TextView price;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgallery);

        img = findViewById(R.id.img);
        name = (TextView) findViewById(R.id.name);
        Sdesc = findViewById(R.id.desc);
        price = findViewById(R.id.price);
        Log.w("tag", "Start of layout");

        recyclerView = (RecyclerView) findViewById(R.id.RV);
        layoutmanager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setAdapter(adapter);
        Log.w("tag", "Post setting up RV");

        SQLiteDatabase db = feedhelper.getReadableDatabase();
        Cursor mCursor =
                db.rawQuery("select * from Units", null);
        mCursor.moveToFirst();

        names.add(mCursor.getString(1));
        name.setText(mCursor.getString(1));
        prices.add(mCursor.getString(2));
        price.setText(mCursor.getString(2));
        desc.add(mCursor.getString(3));
        Sdesc.setText(mCursor.getString(3));
        Checkb = mCursor.getBlob(4);
        b = BitmapFactory.decodeByteArray(Checkb, 0, Checkb.length);
        imgid.add(b);
        img.setImageBitmap(b);

        img.setOnClickListener(this);
        while (mCursor.moveToNext())
        {
            check = mCursor.getString(1);
            names.add(check);
            prices.add(mCursor.getString(2));
            desc.add(mCursor.getString(3));
            Checkb = mCursor.getBlob(4);
            b = BitmapFactory.decodeByteArray(Checkb, 0, Checkb.length);
            imgid.add(b);
        }

        mCursor.close();
        db.close();



        Log.w("tag", "added info to arrays");
//        adapter = new CustomAdapter(imgid, names, desc, prices);
//        recyclerView.setLayoutManager(layoutmanager);
//        recyclerView.setAdapter(adapter);
        Log.w("tag", "RV launched Finished");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.img:
                test.add(name.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putStringArray("name", test.toArray(new String[0]));
                Log.i("tag", "Bundle done");
                intent = new Intent(this, testinfo.class);
                intent.putExtras(bundle);
                Log.i("tag", "Intent Done");
                startActivity(intent);
                Log.i("tag", "open activity");
                break;
        }
    }
}