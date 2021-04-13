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

class ProductAdapter: RecyclerView.Adapter<BestSellingProducts.ViewHolder>() {

    private var productList = emptyList<Product>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellingProducts.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.products_item,
            parent,
            false
        )
        return BestSellingProducts.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellingProducts.ViewHolder, position: Int) {

        Picasso.get().load(productList[position].thumb_image_url).into(holder.image)
        holder.title.text = productList[position].title
        holder.category.text = productList[position].category_name
        holder.price.text = productList[position].price
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.product_image)
        val title: TextView = itemView.findViewById(R.id.product_title)
        val category: TextView = itemView.findViewById(R.id.product_category)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val add: Button = itemView.findViewById(R.id.product_add)

    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }
}