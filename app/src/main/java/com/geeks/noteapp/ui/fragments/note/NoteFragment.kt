package com.geeks.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.model.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.extensions.getBackStackData
import com.geeks.noteapp.ui.adapter.NoteAdapter

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private var noteAdapter = NoteAdapter()
    private var flag = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteAdapter = NoteAdapter()
        val list = App.appDatabase?.noteDao()?.getAll()
        binding.rvNote.adapter = noteAdapter
        noteAdapter.submitList(list)
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        updateNoteList()
    }

    private fun initAdapter() = with(binding) {
        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.noteDetailFragment)
        }
        imgShape.setOnClickListener {
            if (flag) {
                imgShape.setImageResource(R.drawable.ic_shape)
                binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                flag = false
            } else {
                imgShape.setImageResource(R.drawable.ic_menu)
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
                flag = true
            }
        }
    }

    private fun updateNoteList() {
        val notes = App().getInstance()?.noteDao()?.getAll()
        noteAdapter.submitList(notes)
    }
}