package com.emon.embededroom.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emon.embededroom.Model.Person
import com.emon.embededroom.R
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var personList = emptyList<Person>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.firstName_txt.text = personList[position].firstName
        holder.itemView.lastName_txt.text = personList[position].lastName
        holder.itemView.age_txt.text = personList[position].age.toString()

        holder.itemView.streetName_txt.text = personList[position].address.streetName
        holder.itemView.streetNumber_txt.text = personList[position].address.streetNumber.toString()
    }

    fun setData(person: List<Person>){
        personList = person
        notifyDataSetChanged()
    }
}