package com.example.chapter_4.db.dao

import android.adservices.adid.AdId
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chapter_4.Model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>
    @Delete
    suspend fun deleteNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)
}