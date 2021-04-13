package com.emon.offlinecachingwithroom.Adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bdtask.limarket.ModelClass.ProductModel.ProductData
import com.emon.offlinecachingwithroom.R
import com.emon.offlinecachingwithroom.Room.Model.Product
import com.emon.offlinecachingwithroom.Room.ViewModel.ProductViewModel
import com.squareup.picasso.Picasso


class BestSellingProducts(
    private val activity: FragmentActivity?,
    private val productList: ArrayList<ProductData?>,
    private val productViewModel: ProductViewModel
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

        holder.add.setOnClickListener {

            val product = Product(
               productList[position]?.product_id.toString().toInt(),
                productList[position]?.thumb_image_url!!,
                productList[position]?.title!!,
                productList[position]?.category_name!!,
                productList[position]?.price!!,
            )

            productViewModel.addProduct(product)
            Toast.makeText(activity?.applicationContext,"Successfully Added", Toast.LENGTH_SHORT).show()

        }


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
        val add: Button = itemView.findViewById(R.id.product_add)

    }

}