package com.example.recyclerviewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbinding.databinding.RowLayoutBinding

class MyAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.binding.button.setOnClickListener {
            Toast.makeText(mainActivity,""+holder.layoutPosition,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root)
}