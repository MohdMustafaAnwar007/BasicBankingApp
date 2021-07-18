package com.example.bankerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "users_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into users_table values(1234567890,'Jack Grealish',10000.00,'jack@gmail.com','XXXXXXXXXXXX1234','ABCD000B986')");
        db.execSQL("insert into users_table values(3214567890,'Phil Foden',25000.67,'phil@gmail.com','XXXXXXXXXXXX13214','ABCD000B986')");
        db.execSQL("insert into users_table values(2345678901,'Jadon Sancho',5000.56,'jadon@gmail.com','XXXXXXXXXXXX2345','ABCD000B986')");
        db.execSQL("insert into users_table values(4567890123,'Mason Mount',1500.01,'mason@gmail.com','XXXXXXXXXXXX4567','ABCD000B986')");
        db.execSQL("insert into users_table values(5678901234,'Luke Shaw',2500.48,'luke@gmail.com','XXXXXXXXXXXX5678','ABCD000B986')");
        db.execSQL("insert into users_table values(6789012345,'Harry Kane',68000.16,'harry@gmail.com','XXXXXXXXXXXX6789','ABCD000B986')");
        db.execSQL("insert into users_table values(7890123456,'Marcus Rashford',5900.00,'marcus@gmail.com','XXXXXXXXXXXX7890','ABCD000B986')");
        db.execSQL("insert into users_table values(8901234567,'Ben White',990.22,'ben@gmail.com','XXXXXXXXXXXX8901','ABCD000B986')");
        db.execSQL("insert into users_table values(9012345678,'Dele Alli',800.46,'dele@gmail.com','XXXXXXXXXXXX9012','ABCD000B986')");
        db.execSQL("insert into users_table values(1245887776,'Danny Rose',7890.90,'danny@gmail.com','XXXXXXXXXXXX1245','ABCD000B986')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table except select * from users_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update users_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
