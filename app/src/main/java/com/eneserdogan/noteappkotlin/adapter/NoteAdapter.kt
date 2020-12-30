package com.eneserdogan.noteappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eneserdogan.noteappkotlin.R
import com.eneserdogan.noteappkotlin.model.Note
import com.eneserdogan.noteappkotlin.view.NoteListFragmentDirections
import kotlinx.android.synthetic.main.recyclerview_note_item.view.*

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.MyHolder>() {
    var filterList= listOf<Note>()
    var originalList = listOf<Note>()

    fun setList(newList:List<Note>){
        this.originalList =newList
        this.filterList=newList
        notifyDataSetChanged()
    }
    class MyHolder(view: View):RecyclerView.ViewHolder(view) {

        fun bind(note:Note)= with(itemView){
            val id=note.noteId.toString().toIntOrNull()
            recyclerviewItem_title.text = note.title
            recyclerviewItem_description.text = note.description
            recyclerviewItem_date.text=note.date
            itemView.setOnClickListener {
                id?.let {
                    val action=NoteListFragmentDirections.actionNoteListFragmentToAddNoteFragment(it.toInt())
                    Navigation.findNavController(this).navigate(action)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.recyclerview_note_item,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return this.filterList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(this.filterList[position])
    }
}