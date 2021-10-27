package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "details.db", null, 1) {
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table notes (Note text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
    fun savedata(aS: String): Long {
        val cv = ContentValues()
        cv.put("Note", aS)
        var status = sqLiteDatabase.insert("notes", null, cv)
        return status
    }

    @SuppressLint("Range")
    fun retrieve(): ArrayList<String> {
        var notesArray = arrayListOf<String>()
        var c: Cursor = sqLiteDatabase.query("notes", null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                notesArray.add(c.getString(c.getColumnIndex("Note")))
            } while (c.moveToNext())
        }
        return notesArray
    }

    fun ubdateNote(aS: String, aSupdate: String): Int {
        sqLiteDatabase = writableDatabase
        val cv = ContentValues()
        cv.put("Note", aSupdate)
        var u = sqLiteDatabase.update("notes", cv, "Note = ?", arrayOf(aS))
        return u
    }

    fun deleteNote(aS: String) {
        sqLiteDatabase = writableDatabase
        sqLiteDatabase.delete("notes", "Note = ?", arrayOf(aS))
    }
}