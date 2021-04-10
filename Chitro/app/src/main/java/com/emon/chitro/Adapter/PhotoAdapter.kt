package com.emon.chitro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emon.chitro.MainActivity
import com.emon.chitro.ModelClass.PhotosModelClass
import com.emon.chitro.R
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class PhotoAdapter(private val totalResponse: ArrayList<PhotosModelClass?>?, private val mainActivity: MainActivity) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val view: View = LayoutInflater.from(mainActivity).inflate(R.layout.recycycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {


        Picasso.get().load(totalResponse?.get(position)?.previewURL).into(holder.image)
        holder.user.text = totalResponse?.get(position)?.user.toString()
        holder.view.text = totalResponse?.get(position)?.views.toString()
        holder.like.text = totalResponse?.get(position)?.likes.toString()
        holder.comment.text = totalResponse?.get(position)?.comments.toString()
        holder.download.text = totalResponse?.get(position)?.downloads.toString()

    }

    override fun getItemCount(): Int {
        return totalResponse?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.item_imageView)
        var user: TextView = itemView.findViewById(R.id.item_userName)
        var like: TextView = itemView.findViewById(R.id.item_likes)
        var comment: TextView = itemView.findViewById(R.id.item_comments)
        var view: TextView = itemView.findViewById(R.id.item_views)
        var download: TextView = itemView.findViewById(R.id.item_downloads)
    }

}