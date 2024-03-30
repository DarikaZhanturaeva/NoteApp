package com.geeks.noteapp

import android.app.Application
import androidx.room.Room
import com.geeks.noteapp.data.db.daos.AppDatabase
import com.geeks.noteapp.utils.Pref

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Pref
        sharedPreferences.unit(this)
        getInstance()
    }

    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}