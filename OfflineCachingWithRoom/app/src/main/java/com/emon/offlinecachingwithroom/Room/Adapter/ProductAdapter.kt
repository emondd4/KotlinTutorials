package com.emon.offlinecachingwithroom.Room.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.emon.offlinecachingwithroom.Adapter.BestSellingProducts
import com.emon.offlinecachingwithroom.R
import com.emon.offlinecachingwithroom.Room.Model.Product
import com.squareup.picasso.Picasso

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productList = emptyList<Product>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.show_all_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(productList[position].thumb_image_url).into(holder.image)
        holder.title.text = productList[position].title
        holder.price.text = productList[position].price

        holder.add.setOnClickListener {
            var temp = holder.number.text.toString().toInt()
            var temp2 = holder.price.text.toString().toInt()

            temp += 1
            temp2 *= temp

            holder.number.text = temp.toString()
            holder.price.text = temp2.toString()
        }

        holder.minus.setOnClickListener {

            var temp = holder.number.text.toString().toInt()
            var temp2 = holder.price.text.toString().toInt()

            if (temp != 0){

                temp -= 1
                temp2 *= temp

                holder.number.text = temp.toString()
                holder.price.text = temp2.toString()
            }
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.show_all_image)
        val title: TextView = itemView.findViewById(R.id.show_all_title)
        val price: TextView = itemView.findViewById(R.id.show_all_price)
        val add: Button = itemView.findViewById(R.id.show_all_add)
        val minus: Button = itemView.findViewById(R.id.show_all_minus)
        val number: TextView = itemView.findViewById(R.id.show_all_item_freq)

    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }
}