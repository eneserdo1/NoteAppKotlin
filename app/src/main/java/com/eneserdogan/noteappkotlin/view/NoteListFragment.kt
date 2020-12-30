package com.eneserdogan.noteappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.eneserdogan.noteappkotlin.R
import com.eneserdogan.noteappkotlin.adapter.NoteAdapter
import com.eneserdogan.noteappkotlin.model.Note
import com.eneserdogan.noteappkotlin.viewmodel.NoteListViewmodel
import kotlinx.android.synthetic.main.fragment_note_list.*


class NoteListFragment : Fragment() {
    private lateinit var viewmodel : NoteListViewmodel
    private lateinit var adapter : NoteAdapter

    private var notes : ArrayList<Note> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=ViewModelProviders.of(this).get(NoteListViewmodel::class.java)

        initializeRecyclerview()
        observeData()
        buttonListener()
        searchviewListener()


    }

    private fun searchviewListener() {
        noteList_searchview
            .setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null){
                        viewmodel.filterList(p0,adapter)
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null){
                        viewmodel.filterList(p0,adapter)
                    }
                    return false
                }

            })
    }

    private fun buttonListener() {
        notelist_floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
    }

    private fun observeData() {
        viewmodel.getAllNotes(requireContext()).observe(viewLifecycleOwner, Observer<List<Note>> {noteList->
            adapter.setList(noteList)
        })
    }

    private fun initializeRecyclerview() {
        adapter = NoteAdapter()
        var layoutManager = GridLayoutManager(requireContext(),2)
        adapter.setList(notes)
        note_recyclerview.layoutManager = layoutManager
        note_recyclerview.adapter = adapter

    }

}