package com.example.chapter_4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chapter_4.db.dao.NoteDao
import com.example.chapter_4.repository.NoteRepository

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {


    @Suppress("Unchecked_cast")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}