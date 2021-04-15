package com.emon.offlinecachingwithroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.offlinecachingwithroom.Room.Adapter.ProductAdapter
import com.emon.offlinecachingwithroom.Room.ViewModel.ProductViewModel

class ShowAllActivity : AppCompatActivity() {

    lateinit var  deleteBtn: Button
    lateinit var total: TextView
    lateinit var recyclerView: RecyclerView

    lateinit var layoutManager: LinearLayoutManager
    lateinit var productViewModel: ProductViewModel

    private var totalAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val adapter = ProductAdapter()

        total = findViewById(R.id.show_all_total_amount)
        deleteBtn = findViewById(R.id.removeAll_Btn)
        recyclerView = findViewById(R.id.show_all_recycler)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        productViewModel.readAllData.observe(this,{ product ->

            adapter.setData(product)

            for (i in product.indices){
                totalAmount += product[i].price.toString().toInt()
            }
            total.text = totalAmount.toString()

        })

        deleteBtn.setOnClickListener {
            productViewModel.deleteAllProducts()
            Toast.makeText(
                this,
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }
}