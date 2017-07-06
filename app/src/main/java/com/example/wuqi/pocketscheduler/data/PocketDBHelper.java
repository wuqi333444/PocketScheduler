package com.example.wuqi.pocketscheduler.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.wuqi.pocketscheduler.data.Contract;

/**
 * Created by RapHaeL on 2017/6/4.
 */

public class PocketDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Pocket.db";
    public PocketDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_USERENTRY =
            "CREATE TABLE " + Contract.UserEntry.TABLE_NAME + " ("
                    + Contract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.UserEntry.COLUMN_USERID + " INTEGER NOT NULL);";

    private static final String SQL_CREATE_PROJECTENTRY =
            "CREATE TABLE " + Contract.ProjectEntry.TABLE_NAME + " ("
                    + Contract.ProjectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.ProjectEntry.COLUMN_PROJECTID + " INTEGER NOT NULL,"
                    + Contract.ProjectEntry.COLUMN_DESCRIPTION + " TEXT,"
                    + Contract.ProjectEntry.COLUMN_BEGINTIME + " TEXT,"
                    + Contract.ProjectEntry.COLUMN_CREATOR + " TEXT,"
                    + Contract.ProjectEntry.COLUMN_TYPE + "TEXT,"
                    + Contract.ProjectEntry.COLUMN_TITLE + " TEXT);";

    private static final String SQL_CREATE_EVENTENTRY =
            "CREATE TABLE " + Contract.EventEntry.TABLE_NAME + " ("
                    + Contract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.EventEntry.COLUMN_EVENTID + " INTEGER NOT NULL,"
                    + Contract.EventEntry.COLUMN_TITLE + " TEXT NOT NULL,"
                    + Contract.EventEntry.COLUMN_STARTTIME + " INTEGER NOT NULL,"
                    + Contract.EventEntry.COLUMN_ENDTIME + " INTEGER,"
                    + Contract.EventEntry.COLUMN_PROJECTID + " TEXT,"
                    + Contract.EventEntry.COLUMN_DESCRIPTION + " TEXT,"
                    + Contract.EventEntry.COLUMN_CREATORID + " INTEGER NOT NULL,"
                    + Contract.EventEntry.COLUMN_ISFINISHED + " BOOLEAN,"
                    + Contract.EventEntry.COLUMN_ISLONG + " BOOLEAN,"
                    + Contract.EventEntry.COLUMN_ISSHARED + " BOOLEAN,"
                    + Contract.EventEntry.COLUMN_TYPE + " INTEGER,"
                    + Contract.EventEntry.COLUMN_EDITPRIORITY + " INTEGER,"
                    + Contract.EventEntry.COLUMN_LOCATION + " TEXT);";

    private static final String SQL_CREATE_DRAFTBOXENTRY =
            "CREATE TABLE " + Contract.DraftboxEntry.TABLE_NAME + " ("
                    + Contract.DraftboxEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.DraftboxEntry.COLUMN_EVENTID + " INTEGER NOT NULL,"
                    + Contract.DraftboxEntry.COLUMN_TITLE + " TEXT NOT NULL,"
                    + Contract.DraftboxEntry.COLUMN_STARTTIME + " INTEGER NOT NULL,"
                    + Contract.DraftboxEntry.COLUMN_ENDTIME + " INTEGER,"
                    + Contract.DraftboxEntry.COLUMN_PROJECTID + " TEXT,"
                    + Contract.DraftboxEntry.COLUMN_DESCRIPTION + " TEXT,"
                    + Contract.DraftboxEntry.COLUMN_CREATORID + " INTEGER NOT NULL,"
                    + Contract.DraftboxEntry.COLUMN_ISFINISHED + " BOOLEAN,"
                    + Contract.DraftboxEntry.COLUMN_ISLONG + " BOOLEAN,"
                    + Contract.DraftboxEntry.COLUMN_ISSHARED + " BOOLEAN,"
                    + Contract.DraftboxEntry.COLUMN_TYPE + " INTEGER,"
                    + Contract.DraftboxEntry.COLUMN_EDITPRIORITY + " INTEGER,"
                    + Contract.DraftboxEntry.COLUMN_LOCATION + " TEXT);";

    private static final String SQL_CREATE_MEMBERENTRY =
            "CREATE TABLE " + Contract.MemberEntry.TABLE_NAME + " ("
                    + Contract.MemberEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.MemberEntry.COLUMN_MEMBERID + " INTEGER NOT NULL,"
                    + Contract.MemberEntry.COLUMN_NAME + " TEXT,"
                    + Contract.MemberEntry.COLUMN_ISFRIEND + " BOOLEAN);";

    private static final String SQL_CREATE_PROJECT_MEMBERS_PAIRENTRY =
            "CREATE TABLE " + Contract.Project_Members_pairEntry.TABLE_NAME + " ("
                    + Contract.Project_Members_pairEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.Project_Members_pairEntry.COLUMN_PROJECTID + " INTEGER NOT NULL,"
                    + Contract.Project_Members_pairEntry.COLUMN_MEMBERID + "INTEGER NOT NULL);";

    private static final String SQL_CREATE_EVENT_CANDIDATES_PAIRENTRY =
            "CREATE TABLE " + Contract.Event_Candidates_pairEntry.TABLE_NAME + " ("
                    + Contract.Event_Candidates_pairEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.Event_Candidates_pairEntry.COLUMN_EVENTID + " INTEGER NOT NULL,"
                    + Contract.Event_Candidates_pairEntry.COLUMN_CANDIDATEID + "INTEGER NOT NULL);";

    private static final String SQL_CREATE_EVENT_EDITABLE_PAIRENTRY =
            "CREATE TABLE " + Contract.Event_Editable_pairEntry.TABLE_NAME + " ("
                    + Contract.Event_Editable_pairEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.Event_Editable_pairEntry.COLUMN_EVENTID + " INTEGER NOT NULL,"
                    + Contract.Event_Editable_pairEntry.COLUMN_EDITABLEID + "INTEGER NOT NULL);";

    //sql for account based job
    private static final String SQL_CREATE_ACCOUNT =
            "CREATE TABLE " + Contract.AccountEntry.TABLE_NAME + " ("
                    + Contract.AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Contract.AccountEntry.COLUMN_USERNAME + " TEXT NOT NULL,"
                    + Contract.AccountEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_USERENTRY);
        db.execSQL(SQL_CREATE_PROJECTENTRY);
        db.execSQL(SQL_CREATE_EVENTENTRY);
        db.execSQL(SQL_CREATE_DRAFTBOXENTRY);
        db.execSQL(SQL_CREATE_MEMBERENTRY);
        db.execSQL(SQL_CREATE_PROJECT_MEMBERS_PAIRENTRY);
        db.execSQL(SQL_CREATE_EVENT_CANDIDATES_PAIRENTRY);
        db.execSQL(SQL_CREATE_EVENT_EDITABLE_PAIRENTRY);
        db.execSQL(SQL_CREATE_ACCOUNT);
    }

    private static final String SQL_DELETE_USERENTRY = "DROP TABLE IF EXISTS " + Contract.UserEntry.TABLE_NAME;
    private static final String SQL_DELETE_PROJECTENTRY = "DROP TABLE IF EXISTS " + Contract.ProjectEntry.TABLE_NAME;
    private static final String SQL_DELETE_EVENTENTRY = "DROP TABLE IF EXISTS " + Contract.EventEntry.TABLE_NAME;
    private static final String SQL_DELETE_DRAFTBOXENTRY = "DROP TABLE IF EXISTS " + Contract.DraftboxEntry.TABLE_NAME;
    private static final String SQL_DELETE_MEMBERENTRY = "DROP TABLE IF EXISTS " + Contract.MemberEntry.TABLE_NAME;
    private static final String SQL_DELETE_PROJECT_MEMBERS_PAIRENTRY = "DROP TABLE IF EXISTS " + Contract.Project_Members_pairEntry.TABLE_NAME;
    private static final String SQL_DELETE_EVENT_CANDIDATES_PAIRENTRY = "DROP TABLE IF EXISTS " + Contract.Event_Candidates_pairEntry.TABLE_NAME;
    private static final String SQL_DELETE_EVENT_EDITABLE_PAIRENTRY = "DROP TABLE IF EXISTS " + Contract.Event_Editable_pairEntry.TABLE_NAME;
    private static final String SQL_DELETE_ACCOUNT = "DROP TABLE IF EXISTS " + Contract.AccountEntry.TABLE_NAME;


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_USERENTRY);
        db.execSQL(SQL_DELETE_PROJECTENTRY);
        db.execSQL(SQL_DELETE_EVENTENTRY);
        db.execSQL(SQL_DELETE_DRAFTBOXENTRY);
        db.execSQL(SQL_DELETE_MEMBERENTRY);
        db.execSQL(SQL_DELETE_PROJECT_MEMBERS_PAIRENTRY);
        db.execSQL(SQL_DELETE_EVENT_CANDIDATES_PAIRENTRY);
        db.execSQL(SQL_DELETE_EVENT_EDITABLE_PAIRENTRY);
        db.execSQL(SQL_DELETE_ACCOUNT);
        onCreate(db);
    }

    public void registerAccount(SQLiteDatabase db, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put(Contract.AccountEntry.COLUMN_USERNAME,email);
        cv.put(Contract.AccountEntry.COLUMN_PASSWORD,password);
        db.insert(Contract.AccountEntry.TABLE_NAME,null,cv);
    }

//    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion) {
//        onUpgrade(db,oldVersion,newVersion);
//    }
}
