package com.geeks.noteapp.interfaces

import com.geeks.noteapp.data.model.NoteModel

interface OnClick {
    fun onClick(noteModel: NoteModel)
}