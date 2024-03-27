package com.geeks.noteapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geeks.noteapp.data.model.NoteModel

@Dao
interface NoteDao {

    @Query(
        "SELECT * FROM noteModel")
        fun getAll(): List<NoteModel>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertNote(noteModel: NoteModel)

        @Delete
        fun deleteNote(noteModel: NoteModel)

}