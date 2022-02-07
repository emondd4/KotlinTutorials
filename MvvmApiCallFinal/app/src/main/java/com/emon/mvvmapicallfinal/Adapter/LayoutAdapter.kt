package com.emon.mvvmapicallfinal.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.emon.mvvmapicallfinal.Model.BaseResponse
import com.emon.mvvmapicallfinal.R
import com.emon.mvvmapicallfinal.databinding.RecyclerItemBinding

class LayoutAdapter(private val baseResponse: BaseResponse) : RecyclerView.Adapter<LayoutAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutAdapter.ViewHolder {
        return ViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LayoutAdapter.ViewHolder, position: Int) {
        holder.binding.imageView.load(baseResponse[position]?.url) {
            crossfade(true)
            placeholder(R.drawable.image)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return baseResponse.size
    }

    class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
}