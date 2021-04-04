package com.emon.adaptiverecycler.Adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bdtask.limarket.ModelClass.ProductModel.ProductData
import com.emon.adaptiverecycler.R
import com.squareup.picasso.Picasso


class BestSellingProducts(
    private val activity: FragmentActivity?,
    private val productList: ArrayList<ProductData?>
) : RecyclerView.Adapter<BestSellingProducts.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellingProducts.ViewHolder {
        val view: View = LayoutInflater.from(activity).inflate(
            R.layout.products_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellingProducts.ViewHolder, position: Int) {


        Picasso.get().load(productList[position]?.thumb_image_url).into(holder.image)
        holder.title.text = productList[position]?.title
        holder.category.text = productList[position]?.category_name
        holder.price.text = productList[position]?.price


//        holder.itemView.setOnClickListener {
//
//            val id: String? = productList[position]?.product_id
//            println("ProductId : $id")
//            val seller: String? = productList[position]?.seller_id
//            println("SellerId : $seller")
//
//            val bundle = Bundle()
//            bundle.putString("productId",id)
//            bundle.putString("sellerId",seller)
//
//            val productDetailsFragment = ProductDeatilsFragment()
//            productDetailsFragment.arguments = bundle
//
//            val fr = activity?.supportFragmentManager?.beginTransaction()
//            fr?.replace(R.id.HomeFrameLayout, productDetailsFragment)
//            fr?.commit()
//
//        }


    }

    fun addToList(allContact: List<ProductData?>?) {
        if (allContact != null) {
            productList.addAll(allContact)
        }
        if (allContact != null) {
            productList.addAll(allContact)
        }
        Handler(Looper.getMainLooper()).post { notifyItemInserted(productList.size) }
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