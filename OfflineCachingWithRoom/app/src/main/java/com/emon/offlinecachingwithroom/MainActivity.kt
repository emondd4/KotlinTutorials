package com.emon.offlinecachingwithroom

import android.app.AlertDialog
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bdtask.limarket.ModelClass.ProductModel.ProductData
import com.bdtask.limarket.ModelClass.ProductModel.ProductModel
import com.emon.offlinecachingwithroom.Adapter.BestSellingProducts
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var dialog: AlertDialog
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: GridLayoutManager

    private var bestSellingProducts: BestSellingProducts? = null
    private var dataRequestInterface: DataRequestInterface? = null
    private val TAG = "GetProductData REQUEST"
    private var page = 1
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRequestInterface = ApiClient.getRetrofit(this)
            .create(DataRequestInterface::class.java)

        recyclerView = findViewById(R.id.recycler)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        getProductData()
    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                    if (!flag){
                        page += 1
                        println("Page Number: $page")
                        getProductData2()
                        flag = true
                    }
                }

            }
        }

    private fun getProductData() {

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        val call: Call<ProductModel?>? = dataRequestInterface?.getProductList(
            this.resources?.getString(R.string.key)!!,
            "50",
            page,
            "2",
            "",
            "",
            "",
            "",
            ""
        )

        call?.enqueue(object : Callback<ProductModel?> {
            override fun onResponse(call: Call<ProductModel?>, response: Response<ProductModel?>) {
                Log.wtf(TAG, Gson().toJson(response.body()))

                val productList: ArrayList<ProductData?> = response.body()?.product_list!!
                bestSellingProducts = BestSellingProducts(this@MainActivity, productList)
                recyclerView.adapter = bestSellingProducts

                flag = false
                dialog.dismiss()

            }

            override fun onFailure(
                call: Call<ProductModel?>, t: Throwable
            ) {
                Log.wtf(TAG, "onFailure: Country Data: ", t)
            }
        })

    }

    private fun getProductData2() {

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        val call: Call<ProductModel?>? = dataRequestInterface?.getProductList(
            this.resources?.getString(R.string.key)!!,
            "50",
            page,
            "2",
            "",
            "",
            "",
            "",
            ""
        )

        call?.enqueue(object : Callback<ProductModel?> {
            override fun onResponse(call: Call<ProductModel?>, response: Response<ProductModel?>) {
                Log.wtf(TAG, Gson().toJson(response.body()))

                val productList: ArrayList<ProductData?> = response.body()?.product_list!!

                bestSellingProducts?.addToList(productList)
                //recyclerView.adapter = bestSellingProducts

                flag = false
                dialog.dismiss()

            }

            override fun onFailure(
                call: Call<ProductModel?>, t: Throwable
            ) {
                Log.wtf(TAG, "onFailure: Country Data: ", t)
            }
        })

    }
}