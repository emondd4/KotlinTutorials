package com.emondd4.customcardcarousel

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageView

class ViewPagerAdapter(private val imageList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setBackgroundResource(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

}