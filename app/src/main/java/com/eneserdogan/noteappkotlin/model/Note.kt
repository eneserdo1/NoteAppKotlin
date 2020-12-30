package com.eneserdogan.noteappkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId:Int=0,

    @ColumnInfo(name = "title")
    var title:String="",

    @ColumnInfo(name = "description")
    var description:String="",

    @ColumnInfo(name = "date")
    var date:String= ""
)