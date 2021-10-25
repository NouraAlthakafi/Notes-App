package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvNotes: RecyclerView
    lateinit var notesArray: ArrayList <String>
    lateinit var etNote: EditText
    lateinit var btnSubmit: Button
    var aS = ""
    lateinit var dbhelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotes = findViewById(R.id.rvNotes)
        notesArray = arrayListOf()
        rvNotes.adapter = RVnotes(notesArray)
        rvNotes.layoutManager = LinearLayoutManager(this)
        etNote = findViewById(R.id.tvNote)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            try{
                aS = etNote.text.toString()
                dbhelper = DBHelper(applicationContext)
                var status = dbhelper.savedata(aS)
                var notes1All = dbhelper.retrieve(aS)
                notesArray.add(notes1All)
                rvNotes.adapter?.notifyDataSetChanged()
                etNote.text.clear()
                etNote.clearFocus()
            }catch(e: Exception){
                Toast.makeText(applicationContext, "There is no notes to retrieve!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}