package com.emon.CoroutinesRetrofitViewModel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emon.CoroutinesRetrofitViewModel.MainActivity
import com.emon.CoroutinesRetrofitViewModel.ModelClass.ProductData
import com.emon.CoroutinesRetrofitViewModel.R
import com.squareup.picasso.Picasso


class BestSellingProducts(
    private val activity: MainActivity?,
    private val productList: ArrayList<ProductData?>
) : RecyclerView.Adapter<BestSellingProducts.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(activity).inflate(
            R.layout.products_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        Picasso.get().load(productList[position]?.thumb_image_url).into(holder.image)
        holder.title.text = productList[position]?.title
        holder.category.text = productList[position]?.category_name
        holder.price.text = productList[position]?.price


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.product_image)
        val title: TextView = itemView.findViewById(R.id.product_title)
        val category: TextView = itemView.findViewById(R.id.product_category)
        val price: TextView = itemView.findViewById(R.id.product_price)

    }

}