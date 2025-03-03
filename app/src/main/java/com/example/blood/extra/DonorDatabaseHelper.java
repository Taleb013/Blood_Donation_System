package com.example.blood.extra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DonorDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "blood_donors.db";
    private static final int DATABASE_VERSION = 1;

    public DonorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE donors (id INTEGER PRIMARY KEY AUTOINCREMENT, phone TEXT, dob TEXT, weight TEXT, address TEXT, gender TEXT, blood_group TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS donors");
        onCreate(db);
    }
}
