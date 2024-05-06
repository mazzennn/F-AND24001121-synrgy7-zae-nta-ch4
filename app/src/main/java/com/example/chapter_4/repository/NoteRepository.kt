package com.example.chapter_4.repository

import androidx.lifecycle.LiveData
import com.example.chapter_4.Model.Note
import com.example.chapter_4.db.dao.NoteDao

class NoteRepository(private val dao: NoteDao) {

        val allNotes: LiveData<List<Note>> = dao.getAllNotes()

        suspend fun insert(note: Note) {
            return dao.insertNote(note)
        }

        suspend fun update(note: Note) {
            return dao.updateNote(note)
        }

        suspend fun delete(note: Note) {
            return dao.deleteNote(note)
        }
}