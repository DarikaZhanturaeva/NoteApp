package com.geeks.noteapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.data.model.NoteModel
import com.geeks.noteapp.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(noteModel: NoteModel?) {
            binding.apply {
                tvTime.text = noteModel?.time
                tvText.text = noteModel?.text
                tvTitle.text = noteModel?.title
                tvDate.text = noteModel?.date
                noteModel?.color?.let { itemView.setBackgroundColor(it) }

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.title == newItem.title && oldItem.text == newItem.text
        }
    }
}
