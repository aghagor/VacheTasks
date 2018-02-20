package com.example.goro.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Goro on 20.02.2018.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "sqlitedata";
    private static final int DATABASE_VERSION = 3;

    public Database(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }
}
