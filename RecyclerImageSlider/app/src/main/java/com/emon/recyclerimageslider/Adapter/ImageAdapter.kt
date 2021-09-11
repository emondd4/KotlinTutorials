package com.emon.recyclerimageslider.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.emon.recyclerimageslider.R
import com.emon.recyclerimageslider.ViewModel.ImageViewModel
import com.squareup.picasso.Picasso

class ImageAdapter(
    private val requireActivity: FragmentActivity,
    private val images: Array<String>,
    private val viewModel: ImageViewModel
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var flag = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        if (flag == 0){
            viewModel.setImagePath(images[position])
            flag++
        }

        Picasso.get().load(images[position]).placeholder(R.drawable.ic_image).into(holder.image)

        holder.itemView.setOnClickListener {
            viewModel.setImagePath(images[position])
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.itemImageView)

    }

}