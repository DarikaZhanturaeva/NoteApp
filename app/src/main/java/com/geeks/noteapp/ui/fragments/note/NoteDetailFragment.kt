package com.geeks.noteapp.ui.fragments.note

import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.model.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteDetailBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    //private var note: Long = -1L
    var color: Int = 0
    var timeText = ""
    var dateText = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = when (checkedId) {
                binding.radio1.id -> binding.radio1
                binding.radio2.id -> binding.radio2
                binding.radio3.id -> binding.radio3
                else -> binding.radio1
            }
            radioButton.let {
                when (it.tag) {
                    "red" -> color = R.color.red
                    "black" -> color = R.color.black2
                    "white" -> color = R.color.white2
                }
            }
        }

        val currentDate = Calendar.getInstance().time
        val dateFormat: DateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
        dateText = dateFormat.format(currentDate)
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeText = timeFormat.format(currentDate)
        binding.tvTime.text = timeText
        binding.tvDate.text = dateText

        setupTextChangedListener()
        checkButtonVisibility()
        initListener()
        //changeData()
        //getArgument()
    }


    private fun setupTextChangedListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })

        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })
    }

    private fun checkButtonVisibility() {
        val titleText = binding.etTitle.text.toString().trim()
        val text = binding.etText.text.toString().trim()

        binding.tvSend.visibility = if (titleText.isNotEmpty() && text.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun initListener() {
        binding.tvSend.setOnClickListener {
            val noteModel = NoteModel(
                title = binding.etTitle.text.toString(),
                text = binding.etText.text.toString(),
                color = color,
                time = timeText,
                date = dateText
            )
            App.appDatabase?.noteDao()?.insertNote(noteModel)
            findNavController().navigate(
                R.id.noteFragment,
                /* null,
                 navOptions {
                     anim {
                         enter = android.R.anim.slide_in_left
                         exit = android.R.anim.slide_out_right
                     }
                 }*/
            )
        }
    }

    /*private fun changeData() {
        val notesList = mutableListOf<NoteModel>()
        val note = NoteModel()
        notesList.add(note)
    }*/

    /*private fun getArgument() {
        note = arguments?.getLong("noteId", -1L) ?: -1L
        if (note != -1L) {
            val notes = App().getInstance()?.noteDao()?.getNoteById(note)
            notes?.let { note ->
                binding.apply {
                    etTitle.setText(note.title)
                    etText.setText(note.text)

                    /*val notesList = mutableListOf<NoteModel>()
                    val note = NoteModel()
                    notesList.add(note)*/
                }
            }
        }
    }*/
}


