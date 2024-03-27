package com.geeks.noteapp.data.db.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}