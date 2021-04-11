package com.emon.roomcrud.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.emon.roomcrud.Fragments.listFragmentDirections
import com.emon.roomcrud.R
import com.emon.roomcrud.Model.User
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {

        holder.id.text = userList[position].id.toString()
        holder.firstName.text = userList[position].firstName
        holder.lastName.text = userList[position].lastName
        holder.age.text = userList[position].age.toString()

        holder.item.setOnClickListener {

            val action = listFragmentDirections.actionListFragmentToUpdateFragment(userList[position])
            holder.itemView.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {

        return userList.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.idText)
        val firstName: TextView = itemView.findViewById(R.id.FirstText)
        val lastName: TextView = itemView.findViewById(R.id.SecondText)
        val age: TextView = itemView.findViewById(R.id.AgeText)
        val item: ConstraintLayout = itemView.findViewById(R.id.item_layout)

    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}