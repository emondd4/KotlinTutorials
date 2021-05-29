package com.emon.chipskotlin


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bdtask.limarket.ModelClass.SearchProductModel.SearchProductModel
import com.emon.chipskotlin.SearchProductModel.SearchProductCategory
import com.emon.chipskotlin.Utils.ApiClient
import com.emon.chipskotlin.Utils.DataRequestInterface
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var dialog: android.app.AlertDialog
    private var dataRequestInterface: DataRequestInterface? = null
    lateinit var GroupChip: ChipGroup
    private val TAG = "Product Searching"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRequestInterface = ApiClient.getRetrofit(this)
            .create(DataRequestInterface::class.java)

        GroupChip = findViewById(R.id.chipGroup)

        GroupChip.setOnCheckedChangeListener { group, checkedId ->
            // get the checked chip instance from chip group
            (findViewById<Chip>(checkedId))?.let {
                // Show the checked chip text on toast view
                Toast.makeText(this,"Checked + ${it.text}",Toast.LENGTH_SHORT).show()
            }
        }

        getSearchProduct()
    }

    private fun getSearchProduct() {

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        val call: Call<SearchProductModel?>? = dataRequestInterface?.searchProductList(
            "shirt",
            this.resources?.getString(R.string.key)!!,
            "230",
            1,
            "",
            "",
            "asc"
        )

        call?.enqueue(object : Callback<SearchProductModel?> {
            override fun onResponse(call: Call<SearchProductModel?>, response: Response<SearchProductModel?>) {
                Log.wtf(TAG, Gson().toJson(response.body()))

                val categoryData: ArrayList<SearchProductCategory?> = response.body()?.category_list!!

                for (i in categoryData.indices){
                    //println(categoryData[i]?.category_name!!)
                    if (categoryData[i]?.category_name != null){
                        //addChip(categoryData[i]?.category_name!!,chipGroup)
                        GroupChip.addChip(this@MainActivity,categoryData[i]?.category_name!!,categoryData[i]?.category_id!!)
                    }
                }

                dialog.dismiss()
            }

            override fun onFailure(
                call: Call<SearchProductModel?>, t: Throwable
            ) {
                Log.wtf(TAG, "onFailure: SearchProduct Data: ", t)
            }
        })
    }

    fun ChipGroup.addChip(context: Context, label: String, categoryId: String) {

        Chip(context).apply {
            id = View.generateViewId()
            text = label
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                chipBackgroundColor = resources.getColorStateList(R.color.chip_bg,this@MainActivity.theme)
            } else {
                chipBackgroundColor = resources.getColorStateList(R.color.chip_bg)
            }
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = false
            isFocusable = true
            addView(this)

            this.setOnClickListener {

                Toast.makeText(this@MainActivity,""+addId(categoryId),Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var temp: String?  = ""

        fun addId(id: String?): String {
            if (temp != ""){
                temp = "$temp--$id"
            }else{
                temp += id
            }
            return temp as String
        }
    }
}