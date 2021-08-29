package com.emon.CoroutinesRetrofitViewModel

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.CoroutinesRetrofitViewModel.Adapter.BestSellingProducts
import com.emon.CoroutinesRetrofitViewModel.ViewModel.BestSellingViewModel
import dmax.dialog.SpotsDialog

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var dialog: AlertDialog
    lateinit var layoutManager: GridLayoutManager
    lateinit var bestSellingViewModel: BestSellingViewModel

    private var bestSellingProducts: BestSellingProducts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        bestSellingViewModel = ViewModelProvider(this).get(BestSellingViewModel::class.java)

        bestSellingViewModel.products.observe(this, Observer {

            bestSellingProducts = BestSellingProducts(this@MainActivity, it)
            recyclerView.adapter = bestSellingProducts
            dialog.dismiss()

        })
    }

}