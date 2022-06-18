package com.projectx.roomexternaldbtest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectx.roomexternaldbtest.Model.NoteModel
import com.projectx.roomexternaldbtest.R

class NoteAdapter(private val noteList: List<NoteModel>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = noteList[position].title
        holder.description.text = noteList[position].description

    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.titleText)
        val description: TextView = itemView.findViewById(R.id.destinationText)


    }

}