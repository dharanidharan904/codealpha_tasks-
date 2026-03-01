package com.example.flashcardapp;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "FlashcardDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE flashcards(id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT, answer TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS flashcards");
        onCreate(db);
    }

    public void addFlashcard(String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("question", question);
        cv.put("answer", answer);
        db.insert("flashcards", null, cv);
    }

    public Cursor getFlashcards() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM flashcards", null);
    }

    public void deleteFlashcard(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("flashcards", "id=?", new String[]{String.valueOf(id)});
    }

    public void updateFlashcard(int id, String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("question", question);
        cv.put("answer", answer);
        db.update("flashcards", cv, "id=?", new String[]{String.valueOf(id)});
    }
}
