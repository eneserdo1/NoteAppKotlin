package com.eneserdogan.noteappkotlin.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.noteappkotlin.LocalDatabase.DatabaseManager
import com.eneserdogan.noteappkotlin.model.Note

class AddNoteViewmodel:ViewModel() {

    var mutableState:MutableLiveData<Boolean> = MutableLiveData()
    var singleData:MutableLiveData<Note> = MutableLiveData()

    private var databaseManager:DatabaseManager?=null

    fun insertData(context: Context,note:Note):LiveData<Boolean>{
        databaseManager= DatabaseManager.getDatabaseManager(context)
        databaseManager?.noteDao()?.addNote(note)
        mutableState.postValue(true)

        return mutableState
    }

    fun updateData(context: Context,note: Note){
        databaseManager= DatabaseManager.getDatabaseManager(context)
        databaseManager?.noteDao()?.updateData(note.title,note.description,note.date,note.noteId.toString())
        Toast.makeText(context,"Güncellendi",Toast.LENGTH_LONG).show()

    }

    fun getSingleData(context: Context,currentId:String):LiveData<Note>{
        databaseManager= DatabaseManager.getDatabaseManager(context)
        val note=databaseManager?.noteDao()?.getSingleNote(currentId)!!
        singleData.postValue(note)

        return singleData
    }

}