package com.eneserdogan.noteappkotlin.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.noteappkotlin.LocalDatabase.DatabaseManager
import com.eneserdogan.noteappkotlin.adapter.NoteAdapter
import com.eneserdogan.noteappkotlin.model.Note

class NoteListViewmodel:ViewModel() {
    var databaseManager:DatabaseManager ?=null
    val notes : MutableLiveData<List<Note>> = MutableLiveData()

    fun getAllNotes(context: Context):LiveData<List<Note>>{
        databaseManager= DatabaseManager.getDatabaseManager(context)
        val noteList = databaseManager?.noteDao()?.getAllNotes()!!
        notes.postValue(noteList)

        return notes
    }

    fun filterList(term: String, adapter: NoteAdapter) {
        if (term != "") {
            println("term  $term")
            val list = adapter.originalList.filter {
                it.title.contains(term, true) || it.description.contains(
                    term,
                    true
                )
            }
            adapter.filterList = list
            adapter.notifyDataSetChanged()
            Log.d("filterList : ", list.toString())

        } else {
            adapter.filterList = adapter.originalList
            adapter.notifyDataSetChanged()
        }

    }

}