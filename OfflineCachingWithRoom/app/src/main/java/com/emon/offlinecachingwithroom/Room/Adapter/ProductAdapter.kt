package com.emon.offlinecachingwithroom.Room.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.emon.offlinecachingwithroom.R
import com.emon.offlinecachingwithroom.Room.Model.Product
import com.emon.offlinecachingwithroom.Room.ViewModel.ProductViewModel
import com.emon.offlinecachingwithroom.ShowAllActivity
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productViewModel: ProductViewModel,
    private val showAllActivity: ShowAllActivity,
    private val total: TextView
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

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
        holder.price.text = productList[position].sub_price
        holder.number.text = productList[position].quantity
        val price = productList[position].price.toString().toInt()

        holder.add.setOnClickListener {
            var temp = holder.number.text.toString().toInt()
            temp += 1
            val temp2 = price * temp

            holder.number.text = temp.toString()
            holder.price.text = temp2.toString()

            val product = Product(
                productList[position].product_id.toString().toInt(),
                productList[position].thumb_image_url!!,
                productList[position].category_name!!,
                productList[position].price,
                temp2.toString(),
                temp.toString(),
                productList[position].title!!,
            )

            productViewModel.addProduct(product)
        }

        holder.minus.setOnClickListener {

            var temp = holder.number.text.toString().toInt()

            if (temp != 1){

                temp -= 1
                val temp2 = price * temp

                holder.number.text = temp.toString()
                holder.price.text = temp2.toString()

                val product = Product(
                    productList[position].product_id.toString().toInt(),
                    productList[position].thumb_image_url!!,
                    productList[position].category_name!!,
                    productList[position].price,
                    temp2.toString(),
                    temp.toString(),
                    productList[position].title!!,
                )
                productViewModel.addProduct(product)
            }
        }

        holder.delete.setOnClickListener {

            val builder = AlertDialog.Builder(showAllActivity)
            //set title for alert dialog
            builder.setTitle("Delete Item?")
            //set message for alert dialog
            builder.setMessage("Are You Sure?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(showAllActivity.applicationContext,"clicked yes",Toast.LENGTH_LONG).show()

                val product = Product(
                    productList[position].product_id.toString().toInt(),
                    productList[position].thumb_image_url!!,
                    productList[position].category_name!!,
                    productList[position].price,
                    productList[position].sub_price,
                    productList[position].quantity,
                    productList[position].title!!
                )
                productViewModel.deleteProduct(product)
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(showAllActivity.applicationContext,"clicked cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(showAllActivity.applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

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
        val delete: ImageView = itemView.findViewById(R.id.show_all_delete)

    }

    fun setData(product: List<Product>){
        this.productList = product
        notifyDataSetChanged()
    }
}