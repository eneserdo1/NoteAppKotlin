package com.eneserdogan.noteappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.eneserdogan.noteappkotlin.R
import com.eneserdogan.noteappkotlin.model.Note
import com.eneserdogan.noteappkotlin.viewmodel.AddNoteViewmodel
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment : Fragment() {
    private lateinit var viewmodel: AddNoteViewmodel
    private var currentId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            currentId = AddNoteFragmentArgs.fromBundle(it).currentId
        }
        viewmodel = ViewModelProviders.of(this).get(AddNoteViewmodel::class.java)
        if (currentId == 0) {
            noteAddFragmnet_updateButton.visibility = View.GONE
            noteAddFragmnet_saveButton.visibility = View.VISIBLE
        } else {
            noteAddFragmnet_updateButton.visibility = View.VISIBLE
            noteAddFragmnet_saveButton.visibility = View.GONE
            getData()
        }
        buttonListener()
    }
    private fun getData(){
        viewmodel.getSingleData(requireContext(),currentId.toString()).observe(viewLifecycleOwner,
            androidx.lifecycle.Observer<Note> {currentNote->
                noteAddFragment_title.setText(currentNote.title)
                noteAddFragment_description.setText(currentNote.description)

            })
    }


    private fun buttonListener() {
        noteAddFragmnet_saveButton.setOnClickListener {
            val dateFormat = SimpleDateFormat("dd/M/yyyy")
            val currentDate = dateFormat.format(Date())
            var note = Note()
            note.description = noteAddFragment_description.text.toString()
            note.title = noteAddFragment_title.text.toString()
            note.date = currentDate.toString()
            viewmodel.insertData(requireContext(), note)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    if (it) {
                        Toast.makeText(context, "Kayıt Başarılı", Toast.LENGTH_LONG).show()
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_addNoteFragment_to_noteListFragment)
                    } else {
                        Toast.makeText(context, "Kayıt Başarısız", Toast.LENGTH_LONG).show()

                    }
                })


        }
        noteAddFragmnet_updateButton.setOnClickListener {
            val dateFormat = SimpleDateFormat("dd/M/yyyy")
            val currentDate = dateFormat.format(Date())
            var note = Note()
            note.description = noteAddFragment_description.text.toString()
            note.title = noteAddFragment_title.text.toString()
            note.date = currentDate.toString()
            note.noteId=currentId
            viewmodel.updateData(requireContext(),note)
            Navigation.findNavController(it).navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }
    }

}