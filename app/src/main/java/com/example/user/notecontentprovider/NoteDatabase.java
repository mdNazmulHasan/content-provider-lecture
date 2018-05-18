package com.example.user.notecontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 1/8/2017.
 */

public class NoteDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_note";
    private static final int VERSION = 1;
    public static final String TABLE_NOTE = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MESSAGE = "message";
    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NOTE + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MESSAGE + " TEXT NOT NULL)";

    public NoteDatabase(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist "+TABLE_NOTE);
        onCreate(sqLiteDatabase);
    }
}
