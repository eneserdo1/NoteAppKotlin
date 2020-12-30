package com.eneserdogan.noteappkotlin.LocalDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eneserdogan.noteappkotlin.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE noteId=:currentId")
    fun getSingleNote(currentId: String): Note

    @Insert
    fun addNote(note: Note)

    @Query("DELETE FROM notes WHERE noteId=:currentId")
    fun deleteNote(currentId:String)

    @Query("UPDATE notes SET title=:title,description=:description,date=:date WHERE noteId=:currentId ")
    fun updateData(title:String,description:String,date:String,currentId:String)
}