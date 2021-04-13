package com.emon.offlinecachingwithroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.offlinecachingwithroom.Room.Adapter.ProductAdapter
import com.emon.offlinecachingwithroom.Room.ViewModel.ProductViewModel

class ShowAllActivity : AppCompatActivity() {

    lateinit var  deleteBtn: Button
    lateinit var recyclerView: RecyclerView

    lateinit var layoutManager: GridLayoutManager
    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val adapter = ProductAdapter()

        deleteBtn = findViewById(R.id.removeAll_Btn)
        recyclerView = findViewById(R.id.show_all_recycler)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        productViewModel.readAllData.observe(this,{ product ->

            adapter.setData(product)

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