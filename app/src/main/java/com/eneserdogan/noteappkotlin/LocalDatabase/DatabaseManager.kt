package com.eneserdogan.noteappkotlin.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eneserdogan.noteappkotlin.model.Note

@Database(entities = [Note::class],version = 1)
abstract class DatabaseManager:RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object{
        var INSTANCE : DatabaseManager ?=null

        fun getDatabaseManager(context: Context):DatabaseManager?{
            if (INSTANCE == null){
                INSTANCE =Room.databaseBuilder(
                    context.applicationContext,DatabaseManager::class.java,
                    "note-database"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}