package com.charityright.expandablerecycleritem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView

import android.widget.TextView
import android.animation.Animator

import android.animation.AnimatorListenerAdapter
import android.os.Handler
import android.os.Looper


class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    var selectedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
        }

        if (selectedIndex == position){
            holder.tvSubTitle.visibility = View.INVISIBLE
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                holder.tvSubTitle.visibility = View.VISIBLE
            },500)
        }else{
            holder.tvSubTitle.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.titleText)
        var tvSubTitle: TextView = itemView.findViewById(R.id.descriptionText)
        var downBtn: ImageView = itemView.findViewById(R.id.downBtn)

    }

}