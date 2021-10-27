package com.example.notesapp

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvNotes: RecyclerView
    lateinit var notesArray: ArrayList<String>
    lateinit var etNote: EditText
    lateinit var btnSubmit: Button
    var aS = ""
    lateinit var dbhelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotes = findViewById(R.id.rvNotes)
        dbhelper = DBHelper(applicationContext)
        notesArray = dbhelper.retrieve()
        etNote = findViewById(R.id.tvNote)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            addingNotes()
        }
    }

    fun addingNotes() {
        try {
            aS = etNote.text.toString()
            var status = dbhelper.savedata(aS)
            notesArray.add(aS)
            Toast.makeText(
                applicationContext, "data saved successfully! " + status, Toast.LENGTH_SHORT
            ).show()
            rvChange()
            etNote.text.clear()
            etNote.clearFocus()
            rvNotes.scrollToPosition(notesArray.size - 1)
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "There is no notes to retrieve!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun alertUbdate(aS: String) {
        val builder1 = AlertDialog.Builder(this)
        val aSupdate = EditText(this)
        aSupdate.hint = "Update note"
        builder1.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
            dbhelper.ubdateNote(aS, aSupdate.text.toString())
            rvChange()
        })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val edit = builder1.create()
        edit.setTitle("Edit")
        edit.setView(aSupdate)
        edit.show()
    }

    fun delete(aS: String) {
        dbhelper.deleteNote(aS)
        rvChange()
    }

    fun rvChange(){
        rvNotes.adapter = RVnotes(this,dbhelper.retrieve())
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

}