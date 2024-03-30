package com.geeks.noteapp.interfaces

import com.geeks.noteapp.data.model.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
}