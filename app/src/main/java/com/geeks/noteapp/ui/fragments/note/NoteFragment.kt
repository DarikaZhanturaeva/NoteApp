package com.geeks.noteapp.ui.fragments.note

import android.R.attr.text
import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.model.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.interfaces.OnClick
import com.geeks.noteapp.interfaces.OnClickItem
import com.geeks.noteapp.ui.adapter.NoteAdapter
import java.util.Date


class NoteFragment : Fragment(),OnClickItem,OnClick {

    private lateinit var binding: FragmentNoteBinding
    private var noteAdapter = NoteAdapter(this,this)
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

        noteAdapter = NoteAdapter(this,this)
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
            findNavController().navigate(
                com.geeks.noteapp.R.id.action_noteFragment_to_noteDetailFragment,
                /*null,
                navOptions {
                    anim {
                        enter = R.anim.slide_in_left
                        exit = R.anim.slide_out_right
                    }
                }*/
            )
        }
        imgShape.setOnClickListener {
            if (flag) {
                imgShape.setImageResource(com.geeks.noteapp.R.drawable.ic_shape)
                binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                flag = false
            } else {
                imgShape.setImageResource(com.geeks.noteapp.R.drawable.ic_menu)
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
                flag = true
            }
        }
    }

    private fun updateNoteList() {
        val notes = App().getInstance()?.noteDao()?.getAll()
        noteAdapter.submitList(notes)
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")
            setPositiveButton("Да") { dialog, which ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()

}

    override fun onClick(noteModel: NoteModel) {

    }
}