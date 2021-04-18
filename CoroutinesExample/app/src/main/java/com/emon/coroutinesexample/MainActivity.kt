package com.emon.coroutinesexample

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.coroutinesexample.Adapter.BestSellingProducts
import com.emon.coroutinesexample.ModelClass.ProductData
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var dialog: AlertDialog
    lateinit var layoutManager: GridLayoutManager

    private var bestSellingProducts: BestSellingProducts? = null
    private var dataRequestInterface: DataRequestInterface? = null
    private val TAG = "GetProductData REQUEST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRequestInterface = ApiClient.getRetrofit(this)
            .create(DataRequestInterface::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        MainScope().launch {
            bestSellingProducts = BestSellingProducts(this@MainActivity, getProductInfo()!!)
            recyclerView.adapter = bestSellingProducts
            dialog.dismiss()
        }
    }

    private suspend fun getProductInfo(): ArrayList<ProductData?>? {

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        val response = dataRequestInterface?.getProductList(this.resources?.getString(R.string.key)!!,
            "235",
            1,
            "2",
            "",
            "",
            "",
            "",
            "")

        return withContext(Dispatchers.IO){
            response?.body()?.product_list!!
        }

    }
}