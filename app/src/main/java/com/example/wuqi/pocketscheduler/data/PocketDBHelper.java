package com.example.wuqi.pocketscheduler.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.wuqi.pocketscheduler.data.Contract.PetsEntry;

/**
 * Created by RapHaeL on 2017/6/4.
 */

public class PocketDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pocket.db";
    public PocketDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PetsEntry.TABLE_NAME + " ("
                    + PetsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PetsEntry.COLUMN_NAME + " TEXT NOT NULL,"
                    + PetsEntry.COLUMN_BREED + " TEXT,"
                    + PetsEntry.COLUMN_GENDER + " INTEGER NOT NULL,"
                    + PetsEntry.COLUMN_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PetsEntry.TABLE_NAME;

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
