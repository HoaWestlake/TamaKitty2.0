package com.example.tamakitty20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class buy extends AppCompatActivity implements View.OnClickListener {
    Button home, sell;
    ImageView img;
    TextView name, desc, priceEU, priceBTC, priceETH;
    Button buy;

    Intent intent, intent2;

    byte[] Checkb;
    Bitmap b;

    StoreDB storehelper = new StoreDB(this, StoreDB.storeEntry.DATABASE_NAME, null, StoreDB.storeEntry.DATABASE_VERSION);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        home = findViewById(R.id.B_home);
        home.setOnClickListener(this);
        sell = findViewById(R.id.B_sell);
        sell.setOnClickListener(this);

        img = findViewById(R.id.IV_pp);
        name = findViewById(R.id.T_Name);
        desc = findViewById(R.id.T_Sdesc);
        priceEU = findViewById(R.id.T_PriceEU);
        priceBTC = findViewById(R.id.T_PriceBTC);
        priceETH = findViewById(R.id.T_PriceETH);

        SQLiteDatabase db = storehelper.getReadableDatabase();
        Cursor c =
                db.rawQuery("select * from Units", null);
        c.moveToFirst();
        name.setText(c.getString(1));
        priceEU.setText(c.getString(2) + "€");
        desc.setText(c.getString(3));
        Checkb = c.getBlob(4);
        b = BitmapFactory.decodeByteArray(Checkb, 0, Checkb.length);
        img.setImageBitmap(b);
        priceBTC.setText(c.getString(6) + "BTC");
        priceETH.setText(c.getString(7) + "ETH");
        db.close();

    }

    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.B_home:
               intent = new Intent(this, homescreen.class);
               startActivity(intent);
               break;
           case R.id.B_sell:
               intent2 = new Intent(this, sell.class);
               startActivity(intent2);
               break;
       }
    }
}