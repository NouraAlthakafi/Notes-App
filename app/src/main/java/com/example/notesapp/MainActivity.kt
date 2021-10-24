package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvNotes: RecyclerView
    lateinit var notesArray: ArrayList <String>
    lateinit var etNote: EditText
    lateinit var btnSubmit: Button
    var aS = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotes = findViewById(R.id.rvNotes)
        notesArray = arrayListOf()
        rvNotes.adapter = RVnotes(notesArray)
        rvNotes.layoutManager = LinearLayoutManager(this)
        etNote = findViewById(R.id.etNote)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            aS = etNote.text.toString()
            var dbhelper = DBHelper(applicationContext)
            var status = dbhelper.savedata(aS)
            rvNotes.adapter?.notifyDataSetChanged()
            etNote.text.clear()
            etNote.clearFocus()
        }
    }
}