package com.emon.mvvmretrofit.Adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.emon.mvvmretrofit.Model.RandomProductsModel.RandomProductsData
import com.emon.mvvmretrofit.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class RandomProductsAdapter(
    private val activity: FragmentActivity?,
    private val productList: ArrayList<RandomProductsData?>
) : RecyclerView.Adapter<RandomProductsAdapter.ViewHolder>() {
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
        Picasso.get().load(productList[position]?.thumb_image_url).resize(90,90).centerInside().into(holder.image)
        holder.title.text = productList[position]?.title
        holder.category.text = productList[position]?.category_name

        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH)
        val df = nf as DecimalFormat
        df.applyPattern("###,###,##0.00")


        if (productList[position]?.offer_price.equals("0") || productList[position]?.offer_price.equals("0.0") || productList[position]?.offer_price.equals("0.00") || productList[position]?.offer_price == null){
            val formattedPrice = df.format(productList[position]?.price?.toDouble())
            holder.price.text = formattedPrice
            holder.offerPrice.visibility = View.GONE
            holder.currency.visibility = View.GONE
        }else{
            val formattedPrice = df.format(productList[position]?.offer_price?.toDouble())
            holder.price.text = formattedPrice
        }

        val strikeFormattedPrice = df.format(productList[position]?.price?.toDouble())
        holder.offerPrice.text = strikeFormattedPrice
        holder.offerPrice.paintFlags = holder.offerPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


    }


    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.product_image)
        val title: TextView = itemView.findViewById(R.id.product_title)
        val category: TextView = itemView.findViewById(R.id.product_category)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val offerPrice: TextView = itemView.findViewById(R.id.product_old_price)
        val wish: ImageView = itemView.findViewById(R.id.product_wish)
        val currency: TextView = itemView.findViewById(R.id.product_old_price_currency)

    }
}