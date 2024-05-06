package com.example.chapter_4.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter_4.Model.ListNote
import com.example.chapter_4.Model.Note
import com.example.chapter_4.Model.NoteAdapter
import com.example.chapter_4.Model.SharedPreference
import com.example.chapter_4.R
import com.example.chapter_4.databinding.FragmentHomeBinding
import com.example.chapter_4.db.NoteDatabase
import com.example.chapter_4.db.dao.NoteDao
import com.example.chapter_4.repository.NoteRepository
import com.example.chapter_4.viewmodel.NoteViewModel
import com.example.chapter_4.viewmodel.NoteViewModelFactory


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPreferece: SharedPreference
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreferece = SharedPreference(requireContext())

        val username = mySharedPreferece.getUsername()

        binding.btnLogout.setOnClickListener(){
            mySharedPreferece.clearUserData()

            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.tv1.text = "Welcome, $username"

        val noteViewModel = setupViewModel()

        noteViewModel.allNotes().observe(viewLifecycleOwner, Observer { notes ->
            val adapter = NoteAdapter(
                notes,
                showEditNote = { note -> showEditNoteDialog(note) },
                showDeleteNote = { note -> showDeleteNoteDialog(note) }
            )

            binding.recyclerViewNotes.adapter = adapter
            binding.recyclerViewNotes.layoutManager = LinearLayoutManager(requireContext())
        })

        val btnAdd = view.findViewById<Button>(R.id.btnAddNote)
        btnAdd.setOnClickListener {
            showAddNoteDialog()
        }

    }
    private fun setupViewModel(): NoteViewModel {
        val database = NoteDatabase.getInstance(requireContext())
        val repository = NoteRepository(database.noteDao)
        val viewModelFactory = NoteViewModelFactory(repository)
        return ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)
    }
    fun showAddNoteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_note, null)
        val editTextTitle = dialogView.findViewById<android.widget.EditText>(R.id.titleTextField)
        val editTextContent = dialogView.findViewById<android.widget.EditText>(R.id.contentTextField)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Tambah Catatan")
            .setPositiveButton("Simpan") { dialog, which ->
                val title = editTextTitle.text.toString()
                val content = editTextContent.text.toString()

                saveAddNote(title, content)
            }
            .setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            .create()

        dialogBuilder.show()
    }

    private fun saveAddNote(title: String, content: String) {
        val noteViewModel = setupViewModel()
        noteViewModel.insert(Note(null, title, content))

    }

    fun showEditNoteDialog(note: Note) {
        val dialogView = layoutInflater.inflate(R.layout.edit_note, null)
        val editTitle = dialogView.findViewById<android.widget.EditText>(R.id.editTitleTextField)
        val editContent = dialogView.findViewById<android.widget.EditText>(R.id.editContentTextField)

        val noteId = note.noteId
        editTitle.setText(note.noteTitle)
        editContent.setText(note.noteContent)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Edit Catatan")
            .setPositiveButton("Simpan") { dialog, which ->
                val title = editTitle.text.toString()
                val content = editContent.text.toString()

                val noteViewModel = setupViewModel()
                noteViewModel.update(Note(noteId, title, content))
            }
            .setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            .create()

        dialogBuilder.show()
    }

    fun showDeleteNoteDialog(note: Note) {
        val dialogView = layoutInflater.inflate(R.layout.delete_note, null)

        val noteId = note.noteId
        val noteTitle = note.noteTitle
        val noteContent = note.noteContent

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Hapus Catatan")
            .create()

        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnDelete = dialogView.findViewById<Button>(R.id.btnDelete)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            val noteViewModel = setupViewModel()
            noteViewModel.delete(Note(noteId, noteTitle, noteContent))
            dialog.dismiss()
        }

        dialog.show()
    }


}