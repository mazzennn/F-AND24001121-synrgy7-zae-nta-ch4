package com.example.chapter_4.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter_4.R

class NoteAdapter(
    private var notes: List<Note>,
    private val showEditNote: (Note) -> Unit,
    private val showDeleteNote: (Note) -> Unit,

    ): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleNote: TextView = itemView.findViewById(R.id.title_note)
        val contentNote: TextView = itemView.findViewById(R.id.content_note)

        fun bind(note: Note) {
            titleNote.text = note.noteTitle
            contentNote.text = note.noteContent

            val editNote: Button = itemView.findViewById(R.id.btnEdit)
            val deleteNote: Button = itemView.findViewById(R.id.btnDelete)

            editNote.setOnClickListener {
                showEditNote(note)
            }
            deleteNote.setOnClickListener(){
                showDeleteNote(note)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val currentNote = notes[position]
//        holder.titleNote.text = currentNote.noteTitle
//        holder.contentNote.text = currentNote.noteContent
        holder.bind(currentNote)
    }

    override fun getItemCount() = notes.size
}