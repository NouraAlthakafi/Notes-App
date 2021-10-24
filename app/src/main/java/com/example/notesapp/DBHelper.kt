package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "details.db", null, 1){
    var sqLiteDatabase : SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?){
        if(db != null){
            db.execSQL("create table notes (Note text)")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){}
    fun savedata(aS: String): Long{
        val cv = ContentValues()
        cv.put("Note", aS)
        var status = sqLiteDatabase.insert("notes", null, cv)
        return status
    }
}