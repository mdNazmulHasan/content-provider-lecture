package com.example.user.notecontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private NoteDatabase noteDatabase;
    private SQLiteDatabase db;
    private static final UriMatcher sUriMatcher;
    public static final String AUTHORITY_URI = "com.example.user.notecontentprovider";
    private static final String STRING_URI = "content://"+AUTHORITY_URI;
    public static final Uri CONTENT_URI = Uri.parse(STRING_URI);
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY_URI,NoteDatabase.TABLE_NOTE,1);
        sUriMatcher.addURI(AUTHORITY_URI,NoteDatabase.TABLE_NOTE+"/#",2);
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = db.insert(NoteDatabase.TABLE_NOTE,null,values);
        Uri insertUri = Uri.parse(CONTENT_URI+NoteDatabase.TABLE_NOTE+"/"+rowId);
        return insertUri;
    }

    @Override
    public boolean onCreate() {
        noteDatabase = new NoteDatabase(getContext());
        db = noteDatabase.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)){
            case 1:
                Cursor cursor = db.query(NoteDatabase.TABLE_NOTE,projection,selection,selectionArgs,null,null,null);
                return cursor;
            case 2:
                String id = uri.getLastPathSegment();
                Cursor spCursor = db.query(NoteDatabase.TABLE_NOTE,projection,NoteDatabase.COLUMN_ID+"="+id,null,null,null,sortOrder);
                return spCursor;
            default:
                throw new IllegalArgumentException("Invalid uri for query");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
