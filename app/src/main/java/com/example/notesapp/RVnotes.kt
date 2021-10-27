package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ItemNoteBinding

class RVnotes(val activity: MainActivity, var notesArray: ArrayList<String>) : RecyclerView.Adapter<RVnotes.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aNote = notesArray[position]
        holder.binding.apply {
            tvNote.text = aNote
            ibEdit.setOnClickListener {
                activity.alertUbdate(aNote)
            }
            ibDelete.setOnClickListener{
                activity.delete(aNote)
            }
        }
    }

    override fun getItemCount(): Int {
        return notesArray.size
    }

}