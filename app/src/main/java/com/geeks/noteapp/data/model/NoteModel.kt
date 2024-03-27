package com.geeks.noteapp.data.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val text: String,
    val time: String,
    val date: String,
    val color: Int = Color.BLACK

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
