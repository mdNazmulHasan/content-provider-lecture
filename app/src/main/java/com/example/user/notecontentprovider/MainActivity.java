package com.example.user.notecontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private static final Uri INSERT_URI = Uri.parse(MyContentProvider.CONTENT_URI+"/"+NoteDatabase.TABLE_NOTE);
    private static final Uri SHOWALL_URI = Uri.parse(MyContentProvider.CONTENT_URI+"/"+NoteDatabase.TABLE_NOTE);
    private static final Uri SHOWALL_SPECIFIC_URI = Uri.parse(MyContentProvider.CONTENT_URI+"/"+NoteDatabase.TABLE_NOTE+"/1");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.noteTV);
        ContentValues values = new ContentValues();
        values.put(NoteDatabase.COLUMN_MESSAGE,"hello android "+System.currentTimeMillis());
        Uri uri = getContentResolver().insert(INSERT_URI,values);
        tv.setText(uri.toString());
        showAll();
    }

    private void showAll() {
        Cursor cursor = getContentResolver().query(SHOWALL_URI,null,null,null,null);
        String notes = tv.getText()+"\n\n";
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String txt = cursor.getString(cursor.getColumnIndex(NoteDatabase.COLUMN_MESSAGE));
                notes += txt+"\n";
            }while(cursor.moveToNext());
            tv.setText(notes);
        }
    }
}
