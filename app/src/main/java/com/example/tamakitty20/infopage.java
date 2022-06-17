package com.example.tamakitty20;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class infopage extends AppCompatActivity {

    FeedReaderContract feedhelper = new FeedReaderContract(this, FeedReaderContract.FeedEntry.DATABASE_NAME, null, FeedReaderContract.FeedEntry.DATABASE_VERSION);

    private List<String> r = new ArrayList<String>();

    ImageView img;
    TextView name;
    TextView priceEU;
    TextView priceBTC;
    TextView priceETH;
    TextView desc;

    byte[] Checkb;
    Bitmap b;

    Bundle extras;

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);
        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        priceEU = findViewById(R.id.priceeu);
        priceBTC = findViewById(R.id.pricebtc);
        priceETH = findViewById(R.id.priceeth);
        desc = findViewById(R.id.desc);

        extras = getIntent().getExtras();
        r = extras.getStringArrayList("name");
        r.get(0);
        SetInformation(r.get(0));
    }
    public void SetInformation(String s)
    {
        SQLiteDatabase db = feedhelper.getReadableDatabase();
        Cursor c =
                db.rawQuery("select Username from Units where Username = '" + s + "'" , null);
        c.moveToFirst();
        name.setText(c.getString(1));
        priceEU.setText(c.getString(2) + "£");
        desc.setText(c.getString(5));
        Checkb = c.getBlob(4);
        b = BitmapFactory.decodeByteArray(Checkb, 0, Checkb.length);
        img.setImageBitmap(b);
        priceBTC.setText(c.getString(6) + "BTC");
        priceETH.setText(c.getString(7) + "ETH");

    }

}