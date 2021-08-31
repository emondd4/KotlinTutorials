package com.example.mvvmcoroutinesretrofitviewmodel.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutinesretrofitviewmodel.Models.Item
import com.example.mvvmcoroutinesretrofitviewmodel.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reycler_list_item.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var items = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reycler_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.title.text = items[position].name
        holder.description.text = items[position].description
        Picasso.get().load(items[position].owner.avatar_url).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setUpdateData(items : ArrayList<Item>){
        this.items  = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

}