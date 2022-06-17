package com.example.tamakitty20;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tamakitty20.R;
import com.example.tamakitty20.homescreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText _i_Username;
    EditText _i_Password;
    Button _b_Login;

    EditText _i_NewUsername;
    EditText _i_NewPassword;
    EditText _i_ConfirmPassword;
    Button _b_SignUp;

    String Check, Check2;
    String Username, Password;
    Integer num;
    LoginDB loginhelper = new LoginDB(this, LoginDB.LoginEntry.DATABASE_NAME, null, LoginDB.LoginEntry.DATABASE_VERSION);

    private SQLiteDatabase db;

    List<String> items;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, homescreen.class);

        _i_Username = findViewById(R.id.UsernameInput);
        _i_Password = findViewById(R.id.PasswordInput);
        _b_Login = findViewById(R.id.B_Login);
        _b_Login.setOnClickListener(this);

        _i_NewUsername = findViewById(R.id.NewUsernameInput);
        _i_NewPassword = findViewById(R.id.NewPasswordInput);
        _i_ConfirmPassword = findViewById(R.id.ConfirmPasswordInput);
        _b_SignUp = findViewById(R.id.B_SignUp);
        _b_SignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.B_Login:
                SQLiteDatabase db = loginhelper.getReadableDatabase();
                Cursor mCursor =
                        db.rawQuery("select * from Users", null);
                mCursor.moveToFirst();
                Username = mCursor.getString(1);
                Password = mCursor.getString(2);
                if(Username.equals(_i_Username.getText().toString()) && Password.equals(_i_Password.getText().toString()))
                {
                    startActivity(intent);
                }else{
                    while(mCursor.moveToNext())
                    {
                        Username = mCursor.getString(1);
                        Password = mCursor.getString(2);
                        Log.w("tag",Username + " " + Password);
                        if(Username.equals(_i_Username.getText().toString()) && Password.equals(_i_Password.getText().toString()))
                        {
                            startActivity(intent);
                        }
                    }
                }
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                _i_Username.getText().clear();
                _i_Password.getText().clear();
                Log.w("tag","Connection failed");
                mCursor.close();
                db.close();
                break;


            case R.id.B_SignUp:

                if (_i_NewPassword.getText().toString().equals(_i_ConfirmPassword.getText().toString()))
                {

                    insertLogin(_i_NewUsername.getText().toString(),_i_NewPassword.getText().toString());
                    clearEditText();

                }else{

                    clearEditText();

                }

                break;

        }

    }

    public void insertLogin(String username, String password)
    {
        Toast.makeText(getApplicationContext(), "Passwords match", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = loginhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoginDB.LoginEntry.COLUMN_NAME_NAME, username);
        values.put(LoginDB.LoginEntry.COLUMN_NAME_PASSWORD, password);

        Long newRowID = db.insert(LoginDB.LoginEntry.TABLE_NAME, null, values);
        Toast.makeText(getApplicationContext(), "User Created:", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public long deleteLogin (long rowid)
    {
        long rowid2 = db.delete(LoginDB.LoginEntry.TABLE_NAME,  LoginDB.LoginEntry.COLUMN_NAME_ID + "=" + rowid, null);
        return rowid2;
    }

    public void clearEditText()
    {

        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        _i_NewUsername.getText().clear();
        _i_NewPassword.getText().clear();
        _i_ConfirmPassword.getText().clear();

    }
    public Integer UpdateLogin()
    {
        SQLiteDatabase db = loginhelper.getWritableDatabase();

        String title = "Password";
        ContentValues values = new ContentValues();
        values.put(LoginDB.LoginEntry.COLUMN_NAME_PASSWORD, title);
        String selection =LoginDB.LoginEntry.COLUMN_NAME_PASSWORD + " LIKE ?";
        String[] selectionArgs = { null };
        int count = db.update(
                LoginDB.LoginEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

//    @SuppressLint("Range")
//    public Cursor FetchLogin(String Namecheck) throws SQLException
//    {
//
//        SQLiteDatabase db = loginhelper.getReadableDatabase();
//        Cursor mCursor =
//                db.rawQuery("select * from Users;", null);
//        Log.w("tag","query correct");
//
////        mCursor.moveToFirst();
////        while (!mCursor.isAfterLast()) {
////            String name = mCursor.getString(mCursor.getColumnIndex("Username"));
////
////            items.add(name);
////            mCursor.moveToNext();
////        }
////        if (mCursor.moveToFirst()) {
//
////        }
////        mCursor.moveToFirst();
////        Check = mCursor.getString(mCursor.getColumnIndex("Username"));
////        Log.w("tag", Check);
////        if (mCursor != null) {
////            mCursor.moveToFirst();
////            Log.w("tag", "found username");
////        }else
////        {
////            Log.w("tag", "no username found");
////        }
//        if(mCursor.moveToNext())
//        {
//            String items = mCursor.getString(num);
//            Toast.makeText(this,"Done", Toast.LENGTH_SHORT).show();
//        }
////        Log.w("tag", items.toString());
//       // return mCursor;
//    }
}