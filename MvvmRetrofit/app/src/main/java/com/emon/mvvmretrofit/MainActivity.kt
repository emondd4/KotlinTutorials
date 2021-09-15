package com.emon.mvvmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.emon.mvvmretrofit.Adapters.RandomProductsAdapter
import com.emon.mvvmretrofit.ViewModel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.await


class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager:GridLayoutManager
    private lateinit var productViewModel: ProductViewModel

    private lateinit var randomProductsAdapter: RandomProductsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        layoutManager = GridLayoutManager(this,3)



        GlobalScope.launch(Dispatchers.IO){
            val productsData = productViewModel.getRandomProducts.await()?.data?.random_product_list!!

            Log.d("OnResponse", "RandomProductList: $productsData")

            productViewModel.setRandomProducts(productsData.toMutableList())
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            randomProductsAdapter = RandomProductsAdapter(this@MainActivity, productViewModel.getRandomProducts()!!)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = randomProductsAdapter
        }
    }


}